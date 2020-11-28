package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Avatar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Avatar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    @Query("select avatar from Avatar avatar where avatar.user.login = ?#{principal.username}")
    List<Avatar> findByUserIsCurrentUser();
}
