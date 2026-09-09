package com.facturation.restaurant.infrastructure.adapter.in.rest.controller;

import com.facturation.restaurant.application.dto.request.CrearProductoRequest;
import com.facturation.restaurant.application.dto.response.ProductoResponse;
import com.facturation.restaurant.application.mapper.ProductoMapper;
import com.facturation.restaurant.domain.model.Producto;
import com.facturation.restaurant.domain.port.in.GestionProductoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final GestionProductoUseCase gestionProductoUseCase;
    private final ProductoMapper productoMapper;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody CrearProductoRequest request) {
        Producto producto = productoMapper.toDomain(request);
        Producto creado = gestionProductoUseCase.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoMapper.toResponse(creado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable UUID id) {
        Producto producto = gestionProductoUseCase.obtenerProductoPorId(id);
        return ResponseEntity.ok(productoMapper.toResponse(producto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        List<ProductoResponse> response = gestionProductoUseCase.listarProductos().stream()
                .map(productoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ProductoResponse>> listarProductosDisponibles() {
        List<ProductoResponse> response = gestionProductoUseCase.listarProductosDisponibles().stream()
                .map(productoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable UUID id,
            @Valid @RequestBody CrearProductoRequest request) {
        Producto producto = productoMapper.toDomain(request);
        Producto actualizado = gestionProductoUseCase.actualizarProducto(id, producto);
        return ResponseEntity.ok(productoMapper.toResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable UUID id) {
        gestionProductoUseCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}