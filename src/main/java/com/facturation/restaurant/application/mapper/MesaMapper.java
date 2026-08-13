package com.facturation.restaurant.application.mapper;

import com.facturation.restaurant.application.dto.request.CrearMesaRequest;
import com.facturation.restaurant.application.dto.response.MesaResponse;
import com.facturation.restaurant.domain.model.Mesa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MesaMapper {

    // Request → Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "DISPONIBLE")
    Mesa toDomain(CrearMesaRequest request);

    // Domain → Response
    @Mapping(target = "estado", expression = "java(mesa.getEstado().name())")
    MesaResponse toResponse(Mesa mesa);
}