package com.facturation.restaurant.infrastructure.adapter.out.persistence.repository;

import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, UUID> {
    List<ProductoEntity> findByDisponibleTrue();
    boolean existsByNombre(String nombre);
}