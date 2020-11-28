package com.nuri.pangea.repository;

import com.nuri.pangea.domain.Emotion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Emotion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
