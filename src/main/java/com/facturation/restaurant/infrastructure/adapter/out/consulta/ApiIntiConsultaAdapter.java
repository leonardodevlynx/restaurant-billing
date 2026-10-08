package com.facturation.restaurant.infrastructure.adapter.out.consulta;

import com.facturation.restaurant.domain.exception.DomainException;
import com.facturation.restaurant.domain.model.DatosCliente;
import com.facturation.restaurant.domain.port.out.ConsultaDocumentoPort;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;
import java.util.Optional;

@Component
public class ApiIntiConsultaAdapter implements ConsultaDocumentoPort {

    private final RestClient restClient;

    public ApiIntiConsultaAdapter(
            @Value("${consulta-documento.base-url}") String baseUrl,
            @Value("${consulta-documento.token:}") String token) {

        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofSeconds(5));   // si el proveedor se cuelga, no congela la caja

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    @Override
    public Optional<DatosCliente> consultarDni(String dni) {
        return llamar("/dni/{numero}", dni, DniResponse.class)
                .filter(r -> r.success() && r.data() != null)
                .map(r -> new DatosCliente(r.data().dni(), r.data().nombreCompleto(), null));
    }

    @Override
    public Optional<DatosCliente> consultarRuc(String ruc) {
        return llamar("/ruc/{numero}", ruc, RucResponse.class)
                .filter(r -> r.success() && r.data() != null)
                .map(r -> new DatosCliente(r.data().ruc(), r.data().razonSocial(), r.data().direccion()));
    }

    private <R> Optional<R> llamar(String ruta, String numero, Class<R> clase) {
        try {
            return Optional.ofNullable(
                    restClient.get().uri(ruta, numero).retrieve().body(clase));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (HttpClientErrorException.Unauthorized | HttpClientErrorException.Forbidden e) {
            throw new DomainException("CONSULTA_TOKEN_INVALIDO",
                    "El token del servicio de consulta es inválido o no está configurado (APIINTI_TOKEN)");
        } catch (HttpClientErrorException.TooManyRequests e) {
            throw new DomainException("CONSULTA_LIMITE_EXCEDIDO",
                    "Se agotó el límite mensual de consultas. Ingresa los datos manualmente");
        } catch (RestClientException e) {
            throw new DomainException("CONSULTA_NO_DISPONIBLE",
                    "El servicio de consulta no está disponible. Ingresa los datos manualmente");
        }
    }

    // Formato de ApiInti: solo declaramos los campos que usamos. Todo lo del proveedor vive aquí.
    @JsonIgnoreProperties(ignoreUnknown = true)
    record DniResponse(boolean success, DniData data) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record DniData(String dni, String nombreCompleto) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record RucResponse(boolean success, RucData data) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    record RucData(String ruc, String razonSocial, String direccion) {}
}