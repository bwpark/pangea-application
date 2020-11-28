package com.nuri.pangea.repository;

import com.nuri.pangea.domain.TOS;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TOS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TOSRepository extends JpaRepository<TOS, Long> {
}
