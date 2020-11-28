package com.nuri.pangea.service;

import com.nuri.pangea.domain.Emotion;
import com.nuri.pangea.repository.EmotionRepository;
import com.nuri.pangea.service.dto.EmotionDTO;
import com.nuri.pangea.service.mapper.EmotionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Emotion}.
 */
@Service
@Transactional
public class EmotionService {

    private final Logger log = LoggerFactory.getLogger(EmotionService.class);

    private final EmotionRepository emotionRepository;

    private final EmotionMapper emotionMapper;

    public EmotionService(EmotionRepository emotionRepository, EmotionMapper emotionMapper) {
        this.emotionRepository = emotionRepository;
        this.emotionMapper = emotionMapper;
    }

    /**
     * Save a emotion.
     *
     * @param emotionDTO the entity to save.
     * @return the persisted entity.
     */
    public EmotionDTO save(EmotionDTO emotionDTO) {
        log.debug("Request to save Emotion : {}", emotionDTO);
        Emotion emotion = emotionMapper.toEntity(emotionDTO);
        emotion = emotionRepository.save(emotion);
        return emotionMapper.toDto(emotion);
    }

    /**
     * Get all the emotions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EmotionDTO> findAll() {
        log.debug("Request to get all Emotions");
        return emotionRepository.findAll().stream()
            .map(emotionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one emotion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmotionDTO> findOne(Long id) {
        log.debug("Request to get Emotion : {}", id);
        return emotionRepository.findById(id)
            .map(emotionMapper::toDto);
    }

    /**
     * Delete the emotion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Emotion : {}", id);
        emotionRepository.deleteById(id);
    }
}
