package com.facturation.restaurant.domain.model;

import java.util.UUID;

public class Mesa {
    private UUID id;
    private Integer numero;
    private Integer capacidad;
    private EstadoMesa estado;

    public Mesa() {}

    public Mesa(UUID id, Integer numero, Integer capacidad, EstadoMesa estado) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public EstadoMesa getEstado() { return estado; }
    public void setEstado(EstadoMesa estado) { this.estado = estado; }
}