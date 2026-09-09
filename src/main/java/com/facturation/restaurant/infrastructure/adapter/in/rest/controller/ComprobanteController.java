package com.facturation.restaurant.infrastructure.adapter.in.rest.controller;

import com.facturation.restaurant.application.dto.request.EmitirComprobanteRequest;
import com.facturation.restaurant.application.dto.response.ComprobanteResponse;
import com.facturation.restaurant.application.mapper.ComprobanteMapper;
import com.facturation.restaurant.domain.model.Comprobante;
import com.facturation.restaurant.domain.port.in.EmitirComprobanteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {

    private final EmitirComprobanteUseCase emitirComprobanteUseCase;
    private final ComprobanteMapper comprobanteMapper;

    @PostMapping
    public ResponseEntity<ComprobanteResponse> emitirComprobante(
            @Valid @RequestBody EmitirComprobanteRequest request) {
        Comprobante comprobante = emitirComprobanteUseCase.emitirComprobante(
                request.getPedidoId(),
                request.getTipo(),
                request.getRucCliente(),
                request.getRazonSocialCliente(),
                request.getDniCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(comprobanteMapper.toResponse(comprobante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteResponse> obtenerComprobantePorId(@PathVariable UUID id) {
        Comprobante comprobante = emitirComprobanteUseCase.obtenerComprobantePorId(id);
        return ResponseEntity.ok(comprobanteMapper.toResponse(comprobante));
    }

    @PostMapping("/{id}/reenviar")
    public ResponseEntity<ComprobanteResponse> reenviarComprobante(@PathVariable UUID id) {
        Comprobante comprobante = emitirComprobanteUseCase.reenviarComprobante(id);
        return ResponseEntity.ok(comprobanteMapper.toResponse(comprobante));
    }
}