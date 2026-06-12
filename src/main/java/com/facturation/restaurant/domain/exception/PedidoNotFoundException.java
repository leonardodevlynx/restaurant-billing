package com.facturation.restaurant.domain.exception;

import java.util.UUID;

public class PedidoNotFoundException extends DomainException {

    public PedidoNotFoundException(UUID pedidoId) {
        super("PEDIDO_NOT_FOUND",
                "No se encontró el pedido con ID: " + pedidoId);
    }
}