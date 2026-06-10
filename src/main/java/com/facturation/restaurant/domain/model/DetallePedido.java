package com.facturation.restaurant.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class DetallePedido {
    private UUID id;
    private Producto producto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String observacion;

    public DetallePedido() {}

    public DetallePedido(UUID id, Producto producto, Integer cantidad,
                         BigDecimal precioUnitario, String observacion) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.observacion = observacion;
        this.subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}