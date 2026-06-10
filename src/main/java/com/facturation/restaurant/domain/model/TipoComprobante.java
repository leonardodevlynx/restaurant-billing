package com.facturation.restaurant.domain.model;

public enum TipoComprobante {
    FACTURA("01"),
    BOLETA("03"),
    NOTA_CREDITO("07"),
    NOTA_DEBITO("08");

    private final String codigoSunat;

    TipoComprobante(String codigoSunat) {
        this.codigoSunat = codigoSunat;
    }

    public String getCodigoSunat() {
        return codigoSunat;
    }
}