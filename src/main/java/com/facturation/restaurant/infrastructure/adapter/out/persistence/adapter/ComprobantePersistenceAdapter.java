package com.facturation.restaurant.infrastructure.adapter.out.persistence.adapter;

import com.facturation.restaurant.domain.model.Comprobante;
import com.facturation.restaurant.domain.model.EstadoComprobante;
import com.facturation.restaurant.domain.port.out.ComprobanteRepositoryPort;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.ComprobanteEntity;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper.ComprobantePersistenceMapper;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.repository.ComprobanteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ComprobantePersistenceAdapter implements ComprobanteRepositoryPort {

    private final ComprobanteJpaRepository comprobanteJpaRepository;
    private final ComprobantePersistenceMapper comprobantePersistenceMapper;

    @Override
    public Comprobante guardar(Comprobante comprobante) {
        ComprobanteEntity entity = comprobantePersistenceMapper.toEntity(comprobante);
        ComprobanteEntity guardado = comprobanteJpaRepository.save(entity);
        return comprobantePersistenceMapper.toDomain(guardado);
    }

    @Override
    public Optional<Comprobante> buscarPorId(UUID id) {
        return comprobanteJpaRepository.findById(id)
                .map(comprobantePersistenceMapper::toDomain);
    }

    @Override
    public List<Comprobante> buscarPorEstado(EstadoComprobante estado) {
        return comprobanteJpaRepository.findByEstado(estado.name()).stream()
                .map(comprobantePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Comprobante> buscarPorSerieYNumero(String serie, Integer numero) {
        return comprobanteJpaRepository.findBySerieAndNumero(serie, numero)
                .map(comprobantePersistenceMapper::toDomain);
    }

    @Override
    public Integer obtenerUltimoNumero(String serie) {
        return comprobanteJpaRepository.findMaxNumeroBySerie(serie);
    }
}