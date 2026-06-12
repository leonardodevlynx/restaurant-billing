package com.facturation.restaurant.domain.port.in;

import com.facturation.restaurant.domain.model.Producto;
import java.util.List;
import java.util.UUID;

public interface GestionProductoUseCase {
    Producto crearProducto(Producto producto);
    Producto obtenerProductoPorId(UUID id);
    List<Producto> listarProductos();
    List<Producto> listarProductosDisponibles();
    Producto actualizarProducto(UUID id, Producto producto);
    void eliminarProducto(UUID id);
}