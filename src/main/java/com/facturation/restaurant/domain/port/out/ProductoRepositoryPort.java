package com.facturation.restaurant.domain.port.out;

import com.facturation.restaurant.domain.model.Producto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepositoryPort {
    Producto guardar(Producto producto);
    Optional<Producto> buscarPorId(UUID id);
    List<Producto> buscarTodos();
    List<Producto> buscarDisponibles();
    void eliminarPorId(UUID id);
    boolean existePorNombre(String nombre);
}