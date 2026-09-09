package com.facturation.restaurant.infrastructure.adapter.out.persistence.adapter;

import com.facturation.restaurant.domain.model.Pedido;
import com.facturation.restaurant.domain.model.EstadoPedido;
import com.facturation.restaurant.domain.port.out.PedidoRepositoryPort;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper.PedidoPersistenceMapper;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.repository.PedidoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PedidoPersistenceAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoPersistenceMapper pedidoPersistenceMapper;

    @Override
    public Pedido guardar(Pedido pedido) {
        PedidoEntity entity = pedidoPersistenceMapper.toEntity(pedido);

        // Reconectamos cada detalle con su pedido padre (en el mapper dejamos en null a propósito para cortar el ciclo infinito de referencia al padre)
        entity.getDetalles().forEach(detalle -> detalle.setPedido(entity));

        PedidoEntity guardado = pedidoJpaRepository.save(entity);
        return pedidoPersistenceMapper.toDomain(guardado);
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return pedidoJpaRepository.findById(id)
                .map(pedidoPersistenceMapper::toDomain);
    }

    @Override
    public List<Pedido> buscarPorEstado(EstadoPedido estado) {
        return pedidoJpaRepository.findByEstado(estado.name()).stream()
                .map(pedidoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pedido> buscarActivos() {
        return pedidoJpaRepository.findAllActivos().stream()
                .map(pedidoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminarPorId(UUID id) {
        pedidoJpaRepository.deleteById(id);
    }
}