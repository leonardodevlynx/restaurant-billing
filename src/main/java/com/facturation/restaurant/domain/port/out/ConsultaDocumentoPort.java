package com.facturation.restaurant.domain.port.out;

import com.facturation.restaurant.domain.model.DatosCliente;

import java.util.Optional;

public interface ConsultaDocumentoPort {
    Optional<DatosCliente> consultarDni(String dni);
    Optional<DatosCliente> consultarRuc(String ruc);
}