package com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper;

import com.facturation.restaurant.domain.model.Mesa;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.MesaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MesaPersistenceMapper {

    Mesa toDomain(MesaEntity entity);

    MesaEntity toEntity(Mesa domain);
}