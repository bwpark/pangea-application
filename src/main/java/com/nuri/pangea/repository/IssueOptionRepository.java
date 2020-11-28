package com.nuri.pangea.repository;

import com.nuri.pangea.domain.IssueOption;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the IssueOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueOptionRepository extends JpaRepository<IssueOption, Long> {
}
