package com.facturation.restaurant.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class Comprobante {

    private static final BigDecimal IGV_FACTOR = new BigDecimal("1.18");
    private static final BigDecimal IGV_TASA = new BigDecimal("0.18");

    private UUID id;
    private Pedido pedido;
    private TipoComprobante tipo;
    private String serie;
    private Integer numero;
    private String rucCliente;
    private String razonSocialCliente;
    private String dniCliente;
    private BigDecimal valorVenta;   // sin IGV
    private BigDecimal igv;
    private BigDecimal total;        // con IGV
    private EstadoComprobante estado;
    private LocalDateTime fechaEmision;
    private String hashCdr;

    public Comprobante() {}

    // Regla de negocio: calcula IGV a partir del total
    public void calcularMontos(BigDecimal totalConIgv) {
        this.total = totalConIgv.setScale(2, RoundingMode.HALF_UP);
        this.valorVenta = totalConIgv
                .divide(IGV_FACTOR, 2, RoundingMode.HALF_UP);
        this.igv = this.total.subtract(this.valorVenta);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public TipoComprobante getTipo() { return tipo; }
    public void setTipo(TipoComprobante tipo) { this.tipo = tipo; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getRucCliente() { return rucCliente; }
    public void setRucCliente(String rucCliente) { this.rucCliente = rucCliente; }
    public String getRazonSocialCliente() { return razonSocialCliente; }
    public void setRazonSocialCliente(String razonSocialCliente) { this.razonSocialCliente = razonSocialCliente; }
    public String getDniCliente() { return dniCliente; }
    public void setDniCliente(String dniCliente) { this.dniCliente = dniCliente; }
    public BigDecimal getValorVenta() { return valorVenta; }
    public void setValorVenta(BigDecimal valorVenta) { this.valorVenta = valorVenta; }
    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public EstadoComprobante getEstado() { return estado; }
    public void setEstado(EstadoComprobante estado) { this.estado = estado; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getHashCdr() { return hashCdr; }
    public void setHashCdr(String hashCdr) { this.hashCdr = hashCdr; }
}