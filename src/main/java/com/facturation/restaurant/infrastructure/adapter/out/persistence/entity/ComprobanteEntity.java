package com.facturation.restaurant.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comprobantes")
public class ComprobanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private PedidoEntity pedido;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "serie", nullable = false)
    private String serie;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "ruc_cliente")
    private String rucCliente;

    @Column(name = "razon_social_cliente")
    private String razonSocialCliente;

    @Column(name = "dni_cliente")
    private String dniCliente;

    @Column(name = "valor_venta", nullable = false)
    private BigDecimal valorVenta;

    @Column(name = "igv", nullable = false)
    private BigDecimal igv;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "hash_cdr")
    private String hashCdr;

    public ComprobanteEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public PedidoEntity getPedido() { return pedido; }
    public void setPedido(PedidoEntity pedido) { this.pedido = pedido; }
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
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
    public String getHashCdr() { return hashCdr; }
    public void setHashCdr(String hashCdr) { this.hashCdr = hashCdr; }
}