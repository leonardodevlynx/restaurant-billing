package com.facturation.restaurant.domain.model;

public class DatosCliente {
    private String documento;
    private String nombre;      // nombre completo (DNI) o razón social (RUC)
    private String direccion;   // puede ser null

    public DatosCliente() {}

    public DatosCliente(String documento, String nombre, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}