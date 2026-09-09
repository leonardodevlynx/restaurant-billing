package com.facturation.restaurant.infrastructure.adapter.out.persistence.adapter;

import com.facturation.restaurant.domain.model.Producto;
import com.facturation.restaurant.domain.port.out.ProductoRepositoryPort;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.mapper.ProductoPersistenceMapper;
import com.facturation.restaurant.infrastructure.adapter.out.persistence.repository.ProductoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductoPersistenceAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository productoJpaRepository;
    private final ProductoPersistenceMapper productoPersistenceMapper;

    @Override
    public Producto guardar(Producto producto) {
        ProductoEntity entity = productoPersistenceMapper.toEntity(producto);
        ProductoEntity guardada = productoJpaRepository.save(entity);
        return productoPersistenceMapper.toDomain(guardada);
    }

    @Override
    public Optional<Producto> buscarPorId(UUID id) {
        return productoJpaRepository.findById(id)
                .map(productoPersistenceMapper::toDomain);
    }

    @Override
    public List<Producto> buscarTodos() {
        return productoJpaRepository.findAll().stream()
                .map(productoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Producto> buscarDisponibles() {
        return productoJpaRepository.findByDisponibleTrue().stream()
                .map(productoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminarPorId(UUID id) {
        productoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return productoJpaRepository.existsByNombre(nombre);
    }
}