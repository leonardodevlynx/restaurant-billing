package com.facturation.restaurant.application.dto.response;

public class DatosClienteResponse {
    private String documento;
    private String nombre;
    private String direccion;   // puede ser null

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}