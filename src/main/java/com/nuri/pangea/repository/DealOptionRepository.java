package com.nuri.pangea.repository;

import com.nuri.pangea.domain.DealOption;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DealOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealOptionRepository extends JpaRepository<DealOption, Long> {
}
