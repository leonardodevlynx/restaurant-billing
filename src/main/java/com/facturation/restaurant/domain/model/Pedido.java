package com.facturation.restaurant.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private UUID id;
    private Mesa mesa;
    private List<DetallePedido> detalles = new ArrayList<>();
    private EstadoPedido estado;
    private LocalDateTime fechaCreacion;
    private BigDecimal total;
    private String observacion;

    public Pedido() {}

    public Pedido(UUID id, Mesa mesa, EstadoPedido estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.mesa = mesa;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.total = BigDecimal.ZERO;
    }

    // Regla de negocio: calcula el total sumando subtotales
    public void calcularTotal() {
        this.total = detalles.stream()
                .map(DetallePedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }
    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}