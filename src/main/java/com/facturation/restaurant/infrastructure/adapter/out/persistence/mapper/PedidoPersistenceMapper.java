package com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper;

import com.facturation.restaurant.domain.model.Pedido;
import com.facturation.restaurant.domain.model.DetallePedido;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.DetallePedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MesaPersistenceMapper.class, ProductoPersistenceMapper.class })
public interface PedidoPersistenceMapper {

    Pedido toDomain(PedidoEntity entity);

    PedidoEntity toEntity(Pedido domain);

    DetallePedido detalleToDomain(DetallePedidoEntity entity);

    @Mapping(target = "pedido", ignore = true)
    DetallePedidoEntity detalleToEntity(DetallePedido domain);
}