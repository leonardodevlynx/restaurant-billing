package com.facturation.restaurant.domain.port.in;

import com.facturation.restaurant.domain.model.Mesa;
import java.util.List;
import java.util.UUID;

public interface GestionMesaUseCase {
    Mesa crearMesa(Mesa mesa);
    Mesa obtenerMesaPorId(UUID id);
    List<Mesa> listarMesas();
    List<Mesa> listarMesasDisponibles();
    Mesa actualizarEstadoMesa(UUID id, String nuevoEstado);
}