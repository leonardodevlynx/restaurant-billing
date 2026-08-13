package com.facturation.restaurant.application.service;

import com.facturation.restaurant.domain.exception.DomainException;
import com.facturation.restaurant.domain.model.*;
import com.facturation.restaurant.domain.port.in.EmitirComprobanteUseCase;
import com.facturation.restaurant.domain.port.out.ComprobanteRepositoryPort;
import com.facturation.restaurant.domain.port.out.PedidoRepositoryPort;
import com.facturation.restaurant.domain.port.out.MesaRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmitirComprobanteService implements EmitirComprobanteUseCase {

    private final ComprobanteRepositoryPort comprobanteRepository;
    private final PedidoRepositoryPort pedidoRepository;
    private final MesaRepositoryPort mesaRepository;

    public EmitirComprobanteService(ComprobanteRepositoryPort comprobanteRepository,
                                    PedidoRepositoryPort pedidoRepository,
                                    MesaRepositoryPort mesaRepository) {
        this.comprobanteRepository = comprobanteRepository;
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Comprobante emitirComprobante(UUID pedidoId, TipoComprobante tipo,
                                         String rucCliente, String razonSocial,
                                         String dniCliente) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId)
                .orElseThrow(() -> new DomainException("PEDIDO_NOT_FOUND",
                        "No se encontró el pedido con ID: " + pedidoId));

        if (pedido.getEstado() != EstadoPedido.POR_PAGAR) {
            throw new DomainException("PEDIDO_NO_COBRABLE",
                    "Solo se pueden facturar pedidos en estado POR_PAGAR");
        }

        if (tipo == TipoComprobante.FACTURA && rucCliente == null) {
            throw new DomainException("RUC_REQUERIDO",
                    "La factura requiere el RUC del cliente");
        }

        // Serie según tipo de comprobante
        String serie = tipo == TipoComprobante.FACTURA ? "F001" : "B001";
        Integer ultimoNumero = comprobanteRepository.obtenerUltimoNumero(serie);
        Integer nuevoNumero = ultimoNumero + 1;

        Comprobante comprobante = new Comprobante();
        comprobante.setId(UUID.randomUUID());
        comprobante.setPedido(pedido);
        comprobante.setTipo(tipo);
        comprobante.setSerie(serie);
        comprobante.setNumero(nuevoNumero);
        comprobante.setRucCliente(rucCliente);
        comprobante.setRazonSocialCliente(razonSocial);
        comprobante.setDniCliente(dniCliente);
        comprobante.calcularMontos(pedido.getTotal());
        comprobante.setEstado(EstadoComprobante.PENDIENTE_ENVIO);
        comprobante.setFechaEmision(LocalDateTime.now());

        // Actualiza estado del pedido y libera la mesa
        pedido.setEstado(EstadoPedido.FACTURADO);
        pedidoRepository.guardar(pedido);

        Mesa mesa = pedido.getMesa();
        mesa.setEstado(EstadoMesa.DISPONIBLE);
        mesaRepository.guardar(mesa);

        return comprobanteRepository.guardar(comprobante);
    }

    @Override
    public Comprobante obtenerComprobantePorId(UUID id) {
        return comprobanteRepository.buscarPorId(id)
                .orElseThrow(() -> new DomainException("COMPROBANTE_NOT_FOUND",
                        "No se encontró el comprobante con ID: " + id));
    }

    @Override
    public Comprobante reenviarComprobante(UUID id) {
        Comprobante comprobante = obtenerComprobantePorId(id);
        if (comprobante.getEstado() == EstadoComprobante.ACEPTADO) {
            throw new DomainException("COMPROBANTE_YA_ACEPTADO",
                    "El comprobante ya fue aceptado por SUNAT");
        }
        comprobante.setEstado(EstadoComprobante.PENDIENTE_ENVIO);
        return comprobanteRepository.guardar(comprobante);
    }
}