package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Regular;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Regular entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegularRepository extends JpaRepository<Regular, Long> {
}
