package com.facturation.restaurant.application.mapper;

import com.facturation.restaurant.application.dto.response.DetallePedidoResponse;
import com.facturation.restaurant.application.dto.response.PedidoResponse;
import com.facturation.restaurant.domain.model.DetallePedido;
import com.facturation.restaurant.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PedidoMapper {

    // Domain → Response
    @Mapping(target = "numeroMesa", source = "mesa.numero")
    @Mapping(target = "estado", expression = "java(pedido.getEstado().name())")
    PedidoResponse toResponse(Pedido pedido);

    // DetallePedido → DetallePedidoResponse
    @Mapping(target = "nombreProducto", source = "producto.nombre")
    DetallePedidoResponse toDetalleResponse(DetallePedido detalle);
}