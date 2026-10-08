package com.facturation.restaurant.domain.port.in;

import com.facturation.restaurant.domain.model.DatosCliente;

public interface ConsultarDocumentoUseCase {
    DatosCliente consultarDni(String dni);
    DatosCliente consultarRuc(String ruc);
}