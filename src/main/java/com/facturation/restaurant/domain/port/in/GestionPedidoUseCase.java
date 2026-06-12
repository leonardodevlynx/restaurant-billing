package com.facturation.restaurant.domain.port.in;

import com.facturation.restaurant.domain.model.Pedido;
import java.util.List;
import java.util.UUID;

public interface GestionPedidoUseCase {
    Pedido crearPedido(UUID mesaId, String observacion);
    Pedido obtenerPedidoPorId(UUID id);
    List<Pedido> listarPedidosActivos();
    Pedido agregarDetalle(UUID pedidoId, UUID productoId, Integer cantidad, String observacion);
    Pedido avanzarEstado(UUID pedidoId);
    void cancelarPedido(UUID pedidoId);
}