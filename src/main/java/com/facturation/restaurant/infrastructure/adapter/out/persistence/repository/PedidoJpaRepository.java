package com.facturation.restaurant.infrastructure.adapter.out.persistence.repository;

import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, UUID> {
    List<PedidoEntity> findByEstado(String estado);

    @Query("SELECT p FROM PedidoEntity p WHERE p.estado NOT IN ('FACTURADO')")
    List<PedidoEntity> findAllActivos();
}