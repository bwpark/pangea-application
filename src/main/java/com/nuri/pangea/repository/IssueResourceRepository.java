package com.nuri.pangea.repository;

import com.nuri.pangea.domain.IssueResource;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the IssueResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueResourceRepository extends JpaRepository<IssueResource, Long> {
}
