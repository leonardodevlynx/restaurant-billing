package com.facturation.restaurant.domain.port.in;

import com.facturation.restaurant.domain.model.Comprobante;
import com.facturation.restaurant.domain.model.TipoComprobante;
import java.util.UUID;

public interface EmitirComprobanteUseCase {
    Comprobante emitirComprobante(UUID pedidoId, TipoComprobante tipo,
                                  String rucCliente, String razonSocial,
                                  String dniCliente);
    Comprobante obtenerComprobantePorId(UUID id);
    Comprobante reenviarComprobante(UUID id);
}