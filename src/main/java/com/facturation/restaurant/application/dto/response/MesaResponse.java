package com.facturation.restaurant.application.dto.response;

import java.util.UUID;

public class MesaResponse {
    private UUID id;
    private Integer numero;
    private Integer capacidad;
    private String estado;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}