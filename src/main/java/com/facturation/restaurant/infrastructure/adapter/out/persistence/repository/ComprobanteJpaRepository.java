package com.facturation.restaurant.infrastructure.adapter.out.persistence.repository;

import com.facturation.restaurant.infrastructure.adapter.out.persistence.entity.ComprobanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComprobanteJpaRepository extends JpaRepository<ComprobanteEntity, UUID> {
    List<ComprobanteEntity> findByEstado(String estado);
    Optional<ComprobanteEntity> findBySerieAndNumero(String serie, Integer numero);

    @Query("SELECT COALESCE(MAX(c.numero), 0) FROM ComprobanteEntity c WHERE c.serie = :serie")
    Integer findMaxNumeroBySerie(@Param("serie") String serie);
}