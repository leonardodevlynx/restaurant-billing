package com.facturation.restaurant.application.service;

import com.facturation.restaurant.application.dto.request.CrearMesaRequest;
import com.facturation.restaurant.application.dto.response.MesaResponse;
import com.facturation.restaurant.application.mapper.MesaMapper;
import com.facturation.restaurant.domain.exception.DomainException;
import com.facturation.restaurant.domain.exception.PedidoNotFoundException;
import com.facturation.restaurant.domain.model.EstadoMesa;
import com.facturation.restaurant.domain.model.Mesa;
import com.facturation.restaurant.domain.port.in.GestionMesaUseCase;
import com.facturation.restaurant.domain.port.out.MesaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GestionMesaService implements GestionMesaUseCase {

    private final MesaRepositoryPort mesaRepository;
    private final MesaMapper mesaMapper;

    public GestionMesaService(MesaRepositoryPort mesaRepository,
                              MesaMapper mesaMapper) {
        this.mesaRepository = mesaRepository;
        this.mesaMapper = mesaMapper;
    }

    @Override
    public Mesa crearMesa(Mesa mesa) {
        if (mesaRepository.existePorNumero(mesa.getNumero())) {
            throw new DomainException("MESA_DUPLICADA",
                    "Ya existe una mesa con el número: " + mesa.getNumero());
        }
        mesa.setEstado(EstadoMesa.DISPONIBLE);
        return mesaRepository.guardar(mesa);
    }

    @Override
    public Mesa obtenerMesaPorId(UUID id) {
        return mesaRepository.buscarPorId(id)
                .orElseThrow(() -> new DomainException("MESA_NOT_FOUND",
                        "No se encontró la mesa con ID: " + id));
    }

    @Override
    public List<Mesa> listarMesas() {
        return mesaRepository.buscarTodas();
    }

    @Override
    public List<Mesa> listarMesasDisponibles() {
        return mesaRepository.buscarPorEstado(EstadoMesa.DISPONIBLE);
    }

    @Override
    public Mesa actualizarEstadoMesa(UUID id, String nuevoEstado) {
        Mesa mesa = obtenerMesaPorId(id);
        try {
            mesa.setEstado(EstadoMesa.valueOf(nuevoEstado));
        } catch (IllegalArgumentException e) {
            throw new DomainException("ESTADO_INVALIDO",
                    "Estado de mesa inválido: " + nuevoEstado);
        }
        return mesaRepository.guardar(mesa);
    }
}