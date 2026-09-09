package com.facturation.restaurant.infrastructure.adapter.out.persistence.adapter;

import com.facturation.restaurant.domain.model.Mesa;
import com.facturation.restaurant.domain.model.EstadoMesa;
import com.facturation.restaurant.domain.port.out.MesaRepositoryPort;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.MesaEntity;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper.MesaPersistenceMapper;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.repository.MesaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MesaPersistenceAdapter implements MesaRepositoryPort {

    private final MesaJpaRepository mesaJpaRepository;
    private final MesaPersistenceMapper mesaPersistenceMapper;

    @Override
    public Mesa guardar(Mesa mesa) {
        MesaEntity entity = mesaPersistenceMapper.toEntity(mesa);
        MesaEntity guardada = mesaJpaRepository.save(entity);
        return mesaPersistenceMapper.toDomain(guardada);
    }

    @Override
    public Optional<Mesa> buscarPorId(UUID id) {
        return mesaJpaRepository.findById(id)
                .map(mesaPersistenceMapper::toDomain);
    }

    @Override
    public List<Mesa> buscarTodas() {
        return mesaJpaRepository.findAll().stream()
                .map(mesaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Mesa> buscarPorEstado(EstadoMesa estado) {
        return mesaJpaRepository.findByEstado(estado.name()).stream()
                .map(mesaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminarPorId(UUID id) {
        mesaJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNumero(Integer numero) {
        return mesaJpaRepository.existsByNumero(numero);
    }
}