package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Chemistry;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Chemistry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChemistryRepository extends JpaRepository<Chemistry, Long> {
}
