package com.facturation.restaurant.domain.exception;

import com.facturation.restaurant.domain.model.EstadoPedido;

public class TransicionEstadoInvalidaException extends DomainException {

    public TransicionEstadoInvalidaException(EstadoPedido estadoActual,
                                             EstadoPedido estadoDestino) {
        super("TRANSICION_INVALIDA",
                String.format("No se puede pasar de %s a %s",
                        estadoActual, estadoDestino));
    }
}