package com.facturation.restaurant.application.mapper;

import com.facturation.restaurant.application.dto.response.ComprobanteResponse;
import com.facturation.restaurant.domain.model.Comprobante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ComprobanteMapper {

    // Domain → Response
    @Mapping(target = "tipo", expression = "java(comprobante.getTipo().name())")
    @Mapping(target = "estado", expression = "java(comprobante.getEstado().name())")
    ComprobanteResponse toResponse(Comprobante comprobante);
}