package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Interact;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Interact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InteractRepository extends JpaRepository<Interact, Long> {
}
