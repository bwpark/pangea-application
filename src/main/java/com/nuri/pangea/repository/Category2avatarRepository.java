package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Category2avatar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Category2avatar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Category2avatarRepository extends JpaRepository<Category2avatar, Long> {
}
