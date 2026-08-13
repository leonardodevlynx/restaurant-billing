package com.facturation.restaurant.application.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public class DetallePedidoResponse {
    private UUID id;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String observacion;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String n) { this.nombreProducto = n; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal p) { this.precioUnitario = p; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}