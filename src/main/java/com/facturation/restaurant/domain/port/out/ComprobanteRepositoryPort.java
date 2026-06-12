package com.facturation.restaurant.domain.port.out;

import com.facturation.restaurant.domain.model.Comprobante;
import com.facturation.restaurant.domain.model.EstadoComprobante;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComprobanteRepositoryPort {
    Comprobante guardar(Comprobante comprobante);
    Optional<Comprobante> buscarPorId(UUID id);
    List<Comprobante> buscarPorEstado(EstadoComprobante estado);
    Optional<Comprobante> buscarPorSerieYNumero(String serie, Integer numero);
    Integer obtenerUltimoNumero(String serie);
}