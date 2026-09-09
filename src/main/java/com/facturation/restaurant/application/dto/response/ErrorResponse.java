package com.facturation.restaurant.application.dto.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String codigo;
    private String mensaje;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String codigo, String mensaje, int status) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getCodigo() { return codigo; }
    public String getMensaje() { return mensaje; }
    public int getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }
}