package com.facturation.restaurant.application.mapper;

import com.facturation.restaurant.application.dto.response.DatosClienteResponse;
import com.facturation.restaurant.domain.model.DatosCliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DatosClienteMapper {
    DatosClienteResponse toResponse(DatosCliente datos);
}