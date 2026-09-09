package com.facturation.restaurant.infrastructure.adapter.in.rest.controller;

import com.facturation.restaurant.application.dto.request.AgregarDetalleRequest;
import com.facturation.restaurant.application.dto.request.CrearPedidoRequest;
import com.facturation.restaurant.application.dto.response.PedidoResponse;
import com.facturation.restaurant.application.mapper.PedidoMapper;
import com.facturation.restaurant.domain.model.Pedido;
import com.facturation.restaurant.domain.port.in.GestionPedidoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final GestionPedidoUseCase gestionPedidoUseCase;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(@Valid @RequestBody CrearPedidoRequest request) {
        Pedido pedido = gestionPedidoUseCase.crearPedido(request.getMesaId(), request.getObservacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoMapper.toResponse(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> obtenerPedidoPorId(@PathVariable UUID id) {
        Pedido pedido = gestionPedidoUseCase.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedidoMapper.toResponse(pedido));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PedidoResponse>> listarPedidosActivos() {
        List<PedidoResponse> response = gestionPedidoUseCase.listarPedidosActivos().stream()
                .map(pedidoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/detalles")
    public ResponseEntity<PedidoResponse> agregarDetalle(
            @PathVariable UUID id,
            @Valid @RequestBody AgregarDetalleRequest request) {
        Pedido pedido = gestionPedidoUseCase.agregarDetalle(
                id, request.getProductoId(), request.getCantidad(), request.getObservacion());
        return ResponseEntity.ok(pedidoMapper.toResponse(pedido));
    }

    @PatchMapping("/{id}/avanzar-estado")
    public ResponseEntity<PedidoResponse> avanzarEstado(@PathVariable UUID id) {
        Pedido pedido = gestionPedidoUseCase.avanzarEstado(id);
        return ResponseEntity.ok(pedidoMapper.toResponse(pedido));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable UUID id) {
        gestionPedidoUseCase.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}