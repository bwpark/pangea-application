package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.enumeration.AvatarStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Avatar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    @Query(
        "select avatar from Avatar avatar where avatar.user.login = ?#{principal.username} and status = com.nuri.pangea.domain.enumeration.AvatarStatus.ACTIVATED "
    )
    Optional<Avatar> findOneByUserIsCurrentUser();

    @Query("select avatar from Avatar avatar where avatar.user.login = ?#{principal.username}")
    List<Avatar> findAllByUserIsCurrentUser();
}
