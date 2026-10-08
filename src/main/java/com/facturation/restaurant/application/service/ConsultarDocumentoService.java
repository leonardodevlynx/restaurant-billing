package com.facturation.restaurant.application.service;

import com.facturation.restaurant.domain.exception.DomainException;
import com.facturation.restaurant.domain.model.DatosCliente;
import com.facturation.restaurant.domain.port.in.ConsultarDocumentoUseCase;
import com.facturation.restaurant.domain.port.out.ConsultaDocumentoPort;
import org.springframework.stereotype.Service;

@Service
public class ConsultarDocumentoService implements ConsultarDocumentoUseCase {

    private final ConsultaDocumentoPort consultaDocumentoPort;

    public ConsultarDocumentoService(ConsultaDocumentoPort consultaDocumentoPort) {
        this.consultaDocumentoPort = consultaDocumentoPort;
    }

    @Override
    public DatosCliente consultarDni(String dni) {
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new DomainException("DNI_INVALIDO", "El DNI debe tener 8 dígitos");
        }
        return consultaDocumentoPort.consultarDni(dni)
                .orElseThrow(() -> new DomainException("DNI_NO_ENCONTRADO",
                        "No se encontraron datos para el DNI: " + dni));
    }

    @Override
    public DatosCliente consultarRuc(String ruc) {
        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new DomainException("RUC_INVALIDO", "El RUC debe tener 11 dígitos");
        }
        return consultaDocumentoPort.consultarRuc(ruc)
                .orElseThrow(() -> new DomainException("RUC_NO_ENCONTRADO",
                        "No se encontraron datos para el RUC: " + ruc));
    }
}