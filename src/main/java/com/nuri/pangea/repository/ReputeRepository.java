package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Repute;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Repute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReputeRepository extends JpaRepository<Repute, Long> {
}
