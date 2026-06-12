package com.facturation.restaurant.domain.port.out;

import com.facturation.restaurant.domain.model.Pedido;
import com.facturation.restaurant.domain.model.EstadoPedido;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepositoryPort {
    Pedido guardar(Pedido pedido);
    Optional<Pedido> buscarPorId(UUID id);
    List<Pedido> buscarPorEstado(EstadoPedido estado);
    List<Pedido> buscarActivos();
    void eliminarPorId(UUID id);
}