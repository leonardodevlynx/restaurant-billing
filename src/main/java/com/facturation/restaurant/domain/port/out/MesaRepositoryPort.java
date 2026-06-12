package com.facturation.restaurant.domain.port.out;

import com.facturation.restaurant.domain.model.Mesa;
import com.facturation.restaurant.domain.model.EstadoMesa;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MesaRepositoryPort {
    Mesa guardar(Mesa mesa);
    Optional<Mesa> buscarPorId(UUID id);
    List<Mesa> buscarTodas();
    List<Mesa> buscarPorEstado(EstadoMesa estado);
    void eliminarPorId(UUID id);
    boolean existePorNumero(Integer numero);
}