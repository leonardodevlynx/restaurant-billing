package com.facturation.restaurant.application.service;

import com.facturation.restaurant.domain.exception.DomainException;
import com.facturation.restaurant.domain.exception.TransicionEstadoInvalidaException;
import com.facturation.restaurant.domain.model.*;
import com.facturation.restaurant.domain.port.in.GestionPedidoUseCase;
import com.facturation.restaurant.domain.port.out.MesaRepositoryPort;
import com.facturation.restaurant.domain.port.out.PedidoRepositoryPort;
import com.facturation.restaurant.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;
import com.facturation.restaurant.domain.exception.PedidoNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestionPedidoService implements GestionPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final MesaRepositoryPort mesaRepository;
    private final ProductoRepositoryPort productoRepository;

    public GestionPedidoService(PedidoRepositoryPort pedidoRepository,
                                MesaRepositoryPort mesaRepository,
                                ProductoRepositoryPort productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Pedido crearPedido(UUID mesaId, String observacion) {
        Mesa mesa = mesaRepository.buscarPorId(mesaId)
                .orElseThrow(() -> new DomainException("MESA_NOT_FOUND",
                        "No se encontró la mesa con ID: " + mesaId));

        if (mesa.getEstado() == EstadoMesa.OCUPADA) {
            throw new DomainException("MESA_OCUPADA",
                    "La mesa " + mesa.getNumero() + " ya está ocupada");
        }

        Pedido pedido = new Pedido(
                null, mesa,
                EstadoPedido.PENDIENTE, LocalDateTime.now()
        );
        pedido.setObservacion(observacion);

        mesa.setEstado(EstadoMesa.OCUPADA);
        mesaRepository.guardar(mesa);

        return pedidoRepository.guardar(pedido);
    }

    @Override
    public Pedido obtenerPedidoPorId(UUID id) {
        return pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }

    @Override
    public List<Pedido> listarPedidosActivos() {
        return pedidoRepository.buscarActivos();
    }

    @Override
    public Pedido agregarDetalle(UUID pedidoId, UUID productoId,
                                 Integer cantidad, String observacion) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new DomainException("PEDIDO_NO_EDITABLE",
                    "Solo se pueden agregar productos a pedidos en estado PENDIENTE");
        }

        Producto producto = productoRepository.buscarPorId(productoId)
                .orElseThrow(() -> new DomainException("PRODUCTO_NOT_FOUND",
                        "No se encontró el producto con ID: " + productoId));

        if (!producto.getDisponible()) {
            throw new DomainException("PRODUCTO_NO_DISPONIBLE",
                    "El producto " + producto.getNombre() + " no está disponible");
        }

        DetallePedido detalle = new DetallePedido(
                null, producto,
                cantidad, producto.getPrecio(), observacion
        );

        pedido.getDetalles().add(detalle);
        pedido.calcularTotal();

        return pedidoRepository.guardar(pedido);
    }

    @Override
    public Pedido avanzarEstado(UUID pedidoId) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);
        EstadoPedido estadoActual = pedido.getEstado();

        if (estadoActual == EstadoPedido.PENDIENTE && pedido.getDetalles().isEmpty()) {
            throw new DomainException("PEDIDO_VACIO",
                    "No se puede avanzar un pedido sin productos");
        }

        EstadoPedido siguienteEstado = switch (estadoActual) {
            case PENDIENTE   -> EstadoPedido.PREPARANDO;
            case PREPARANDO  -> EstadoPedido.ENTREGADO;
            case ENTREGADO   -> EstadoPedido.POR_PAGAR;
            default -> throw new TransicionEstadoInvalidaException(
                    estadoActual, EstadoPedido.FACTURADO);
        };

        pedido.setEstado(siguienteEstado);
        return pedidoRepository.guardar(pedido);
    }

    @Override
    public void cancelarPedido(UUID pedidoId) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            throw new DomainException("PEDIDO_NO_CANCELABLE",
                    "Solo se pueden cancelar pedidos en estado PENDIENTE");
        }

        Mesa mesa = pedido.getMesa();
        mesa.setEstado(EstadoMesa.DISPONIBLE);
        mesaRepository.guardar(mesa);

        pedidoRepository.eliminarPorId(pedidoId);
    }
}