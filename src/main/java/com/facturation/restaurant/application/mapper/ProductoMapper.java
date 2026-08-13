package com.facturation.restaurant.application.mapper;

import com.facturation.restaurant.application.dto.request.CrearProductoRequest;
import com.facturation.restaurant.application.dto.response.ProductoResponse;
import com.facturation.restaurant.domain.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductoMapper {

    // Request → Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "disponible", constant = "true")
    Producto toDomain(CrearProductoRequest request);

    // Domain → Response
    ProductoResponse toResponse(Producto producto);
}