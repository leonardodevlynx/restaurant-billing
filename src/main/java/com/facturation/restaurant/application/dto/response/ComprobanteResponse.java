package com.facturation.restaurant.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ComprobanteResponse {
    private UUID id;
    private String tipo;
    private String serie;
    private Integer numero;
    private String rucCliente;
    private String razonSocialCliente;
    private String dniCliente;
    private BigDecimal valorVenta;
    private BigDecimal igv;
    private BigDecimal total;
    private String estado;
    private LocalDateTime fechaEmision;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getRucCliente() { return rucCliente; }
    public void setRucCliente(String rucCliente) { this.rucCliente = rucCliente; }
    public String getRazonSocialCliente() { return razonSocialCliente; }
    public void setRazonSocialCliente(String r) { this.razonSocialCliente = r; }
    public String getDniCliente() { return dniCliente; }
    public void setDniCliente(String dniCliente) { this.dniCliente = dniCliente; }
    public BigDecimal getValorVenta() { return valorVenta; }
    public void setValorVenta(BigDecimal valorVenta) { this.valorVenta = valorVenta; }
    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime f) { this.fechaEmision = f; }
}