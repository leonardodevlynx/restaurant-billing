package com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper;

import com.facturation.restaurant.domain.model.Comprobante;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.ComprobanteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { PedidoPersistenceMapper.class })
public interface ComprobantePersistenceMapper {

    Comprobante toDomain(ComprobanteEntity entity);

    ComprobanteEntity toEntity(Comprobante domain);
}