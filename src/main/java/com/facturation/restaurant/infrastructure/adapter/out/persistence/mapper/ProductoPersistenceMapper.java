package com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper;

import com.facturation.restaurant.domain.model.Producto;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoPersistenceMapper {

    Producto toDomain(ProductoEntity entity);

    ProductoEntity toEntity(Producto domain);
}