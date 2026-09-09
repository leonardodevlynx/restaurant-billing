package com.facturation.restaurant.infrastructure.adapter.in.rest.controller;

import com.facturation.restaurant.application.dto.request.CrearMesaRequest;
import com.facturation.restaurant.application.dto.response.MesaResponse;
import com.facturation.restaurant.application.mapper.MesaMapper;
import com.facturation.restaurant.domain.model.Mesa;
import com.facturation.restaurant.domain.port.in.GestionMesaUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final GestionMesaUseCase gestionMesaUseCase;
    private final MesaMapper mesaMapper;

    @PostMapping
    public ResponseEntity<MesaResponse> crearMesa(@Valid @RequestBody CrearMesaRequest request) {
        Mesa mesa = mesaMapper.toDomain(request);
        Mesa creada = gestionMesaUseCase.crearMesa(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaMapper.toResponse(creada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaResponse> obtenerMesaPorId(@PathVariable UUID id) {
        Mesa mesa = gestionMesaUseCase.obtenerMesaPorId(id);
        return ResponseEntity.ok(mesaMapper.toResponse(mesa));
    }

    @GetMapping
    public ResponseEntity<List<MesaResponse>> listarMesas() {
        List<MesaResponse> response = gestionMesaUseCase.listarMesas().stream()
                .map(mesaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<MesaResponse>> listarMesasDisponibles() {
        List<MesaResponse> response = gestionMesaUseCase.listarMesasDisponibles().stream()
                .map(mesaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<MesaResponse> actualizarEstadoMesa(
            @PathVariable UUID id,
            @RequestParam String estado) {
        Mesa actualizada = gestionMesaUseCase.actualizarEstadoMesa(id, estado);
        return ResponseEntity.ok(mesaMapper.toResponse(actualizada));
    }
}