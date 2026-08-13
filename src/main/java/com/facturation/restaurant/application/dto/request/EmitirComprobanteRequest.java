package com.facturation.restaurant.application.dto.request;

import com.facturation.restaurant.domain.model.TipoComprobante;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class EmitirComprobanteRequest {

    @NotNull(message = "El ID del pedido es obligatorio")
    private UUID pedidoId;

    @NotNull(message = "El tipo de comprobante es obligatorio")
    private TipoComprobante tipo;

    // Solo para FACTURA
    private String rucCliente;
    private String razonSocialCliente;

    // Solo para BOLETA
    private String dniCliente;

    public UUID getPedidoId() { return pedidoId; }
    public void setPedidoId(UUID pedidoId) { this.pedidoId = pedidoId; }
    public TipoComprobante getTipo() { return tipo; }
    public void setTipo(TipoComprobante tipo) { this.tipo = tipo; }
    public String getRucCliente() { return rucCliente; }
    public void setRucCliente(String rucCliente) { this.rucCliente = rucCliente; }
    public String getRazonSocialCliente() { return razonSocialCliente; }
    public void setRazonSocialCliente(String v) { this.razonSocialCliente = v; }
    public String getDniCliente() { return dniCliente; }
    public void setDniCliente(String dniCliente) { this.dniCliente = dniCliente; }
}