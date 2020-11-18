package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Category2Issue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Category2Issue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Category2IssueRepository extends JpaRepository<Category2Issue, Long> {
}
