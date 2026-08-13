package com.facturation.restaurant.application.service;

import com.facturation.restaurant.domain.exception.DomainException;
import com.facturation.restaurant.domain.model.Producto;
import com.facturation.restaurant.domain.port.in.GestionProductoUseCase;
import com.facturation.restaurant.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GestionProductoService implements GestionProductoUseCase {

    private final ProductoRepositoryPort productoRepository;

    public GestionProductoService(ProductoRepositoryPort productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        if (productoRepository.existePorNombre(producto.getNombre())) {
            throw new DomainException("PRODUCTO_DUPLICADO",
                    "Ya existe un producto con el nombre: " + producto.getNombre());
        }
        producto.setDisponible(true);
        return productoRepository.guardar(producto);
    }

    @Override
    public Producto obtenerProductoPorId(UUID id) {
        return productoRepository.buscarPorId(id)
                .orElseThrow(() -> new DomainException("PRODUCTO_NOT_FOUND",
                        "No se encontró el producto con ID: " + id));
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.buscarTodos();
    }

    @Override
    public List<Producto> listarProductosDisponibles() {
        return productoRepository.buscarDisponibles();
    }

    @Override
    public Producto actualizarProducto(UUID id, Producto producto) {
        Producto existente = obtenerProductoPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setCategoria(producto.getCategoria());
        existente.setDisponible(producto.getDisponible());
        return productoRepository.guardar(existente);
    }

    @Override
    public void eliminarProducto(UUID id) {
        obtenerProductoPorId(id);
        productoRepository.eliminarPorId(id);
    }
}