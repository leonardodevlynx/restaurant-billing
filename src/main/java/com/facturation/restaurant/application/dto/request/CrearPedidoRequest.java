package com.facturation.restaurant.application.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class CrearPedidoRequest {

    @NotNull(message = "El ID de mesa es obligatorio")
    private UUID mesaId;

    private String observacion;

    public UUID getMesaId() { return mesaId; }
    public void setMesaId(UUID mesaId) { this.mesaId = mesaId; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}