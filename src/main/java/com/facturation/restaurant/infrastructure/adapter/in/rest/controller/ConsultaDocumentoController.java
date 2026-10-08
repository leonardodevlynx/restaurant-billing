package com.facturation.restaurant.infrastructure.adapter.in.rest.controller;

import com.facturation.restaurant.application.dto.response.DatosClienteResponse;
import com.facturation.restaurant.application.mapper.DatosClienteMapper;
import com.facturation.restaurant.domain.port.in.ConsultarDocumentoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaDocumentoController {

    private final ConsultarDocumentoUseCase consultarDocumentoUseCase;
    private final DatosClienteMapper datosClienteMapper;

    public ConsultaDocumentoController(ConsultarDocumentoUseCase consultarDocumentoUseCase,
                                       DatosClienteMapper datosClienteMapper) {
        this.consultarDocumentoUseCase = consultarDocumentoUseCase;
        this.datosClienteMapper = datosClienteMapper;
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<DatosClienteResponse> consultarDni(@PathVariable String dni) {
        return ResponseEntity.ok(
                datosClienteMapper.toResponse(consultarDocumentoUseCase.consultarDni(dni)));
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<DatosClienteResponse> consultarRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(
                datosClienteMapper.toResponse(consultarDocumentoUseCase.consultarRuc(ruc)));
    }
}