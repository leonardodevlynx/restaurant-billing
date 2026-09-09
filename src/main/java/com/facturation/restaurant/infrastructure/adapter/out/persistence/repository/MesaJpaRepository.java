package com.facturation.restaurant.infrastructure.adapter.out.persistence.repository;

import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MesaJpaRepository extends JpaRepository<MesaEntity, UUID> {
    List<MesaEntity> findByEstado(String estado);
    boolean existsByNumero(Integer numero);
}