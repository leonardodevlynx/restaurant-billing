package com.facturation.restaurant.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoResponse {
    private UUID id;
    private Integer numeroMesa;
    private List<DetallePedidoResponse> detalles;
    private String estado;
    private LocalDateTime fechaCreacion;
    private BigDecimal total;
    private String observacion;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Integer getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(Integer numeroMesa) { this.numeroMesa = numeroMesa; }
    public List<DetallePedidoResponse> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedidoResponse> detalles) { this.detalles = detalles; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime f) { this.fechaCreacion = f; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}