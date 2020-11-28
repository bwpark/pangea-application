package com.nuri.pangea.service;

import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.domain.Emotion;
import com.nuri.pangea.domain.Interact;
import com.nuri.pangea.domain.Issue;
import com.nuri.pangea.domain.enumeration.EmotionStatus;
import com.nuri.pangea.repository.AvatarRepository;
import com.nuri.pangea.repository.EmotionRepository;
import com.nuri.pangea.repository.InteractRepository;
import com.nuri.pangea.repository.IssueRepository;
import com.nuri.pangea.security.SecurityUtils;
import com.nuri.pangea.service.dto.CommunityDTO;
import com.nuri.pangea.service.dto.CommunityLiteDTO;
import com.nuri.pangea.service.dto.InteractDTO;
import com.nuri.pangea.service.mapper.CommunityMapper;
import com.nuri.pangea.service.mapper.InteractMapper;
import java.time.Instant;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Issue}.
 */
@Service
@Transactional
public class CommunityService {
    private final Logger log = LoggerFactory.getLogger(CommunityService.class);

    private final AvatarRepository avatarRepository;
    private final IssueRepository issueRepository;
    private final EmotionRepository emotionRepository;
    private final InteractRepository interactRepository;

    private final CommunityMapper communityMapper;
    private final InteractMapper interactMapper;

    public CommunityService(
        IssueRepository issueRepository,
        AvatarRepository avatarRepository,
        EmotionRepository emotionRepository,
        CommunityMapper communityMapper,
        InteractRepository interactRepository,
        InteractMapper interactMapper
    ) {
        this.issueRepository = issueRepository;
        this.interactRepository = interactRepository;
        this.emotionRepository = emotionRepository;
        this.avatarRepository = avatarRepository;

        this.communityMapper = communityMapper;
        this.interactMapper = interactMapper;
    }

    /**
     * Save a issue.
     *
     * @param issueDTO the entity to save.
     * @return the persisted entity.
     */
    public CommunityDTO save(CommunityDTO communityDTO) {
        log.debug("Request to save Issue : {}", communityDTO);
        Issue issue = communityMapper.toEntity(communityDTO);
        issue = issueRepository.save(issue);
        return communityMapper.toDto(issue);
    }

    /**
     * Get all the issues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommunityLiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Issues");
        return issueRepository.findAll(pageable).map(communityMapper::toCommunityLiteDTO);
    }

    /**
     * Get one issue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = false)
    public Optional<CommunityDTO> findOne(Long id) {
        log.debug("Request to get Issue : {}", id);
        Optional<Issue> _issue = issueRepository.findById(id);
        _issue.ifPresent(i -> i.setViews(i.getViews() + 1));
        Optional<Issue> issue = Optional.of(issueRepository.save(_issue.get()));
        return issue.map(communityMapper::toDto);
    }

    /**
     * Delete the issue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Issue : {}", id);
        issueRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public CommunityDTO like(Long id) throws Exception {
        log.debug("\n\n Request to like Issue : {}", id);

        Avatar avatar = avatarRepository
            .findOneByUserIsCurrentUser()
            .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFound"));
        Issue issue = issueRepository
            .findById(id)
            .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFound"));

        Emotion other = new Emotion();
        other.setIssue(issue);
        other.setYou(issue.getMe());
        other.setMe(avatar);
        other.setStatus(EmotionStatus.NONE);
        Emotion emotion = emotionRepository.findOneByIssueIdAndMeId(id, avatar.getId()).orElse(other);
        log.debug(emotion.toString());
        if (emotion.getStatus() == EmotionStatus.RESPECT) {
            issue.setLike(issue.getLike() - 1);
            emotion.setStatus(EmotionStatus.NONE);
        } else if (emotion.getStatus() == EmotionStatus.DISS) {
            issue.setLike(issue.getLike() + 1);
            issue.setDislike(issue.getDislike() - 1);
            emotion.setStatus(EmotionStatus.RESPECT);
        } else {
            issue.setLike(issue.getLike() + 1);
            emotion.setStatus(EmotionStatus.RESPECT);
        }
        emotionRepository.save(emotion);
        return communityMapper.toDto(issueRepository.save(issue));
    }

    @Transactional(readOnly = false)
    public CommunityDTO dislike(Long id) throws Exception {
        log.debug("Request to like Issue : {}", id);

        Avatar avatar = avatarRepository
            .findOneByUserIsCurrentUser()
            .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFound"));
        Issue issue = issueRepository
            .findById(id)
            .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFound"));

        Emotion other = new Emotion();
        other.setIssue(issue);
        other.setYou(issue.getMe());
        other.setMe(avatar);
        other.setStatus(EmotionStatus.NONE);
        Emotion emotion = emotionRepository.findOneByIssueIdAndMeId(id, avatar.getId()).orElse(other);
        if (emotion.getStatus() == EmotionStatus.RESPECT) {
            issue.setLike(issue.getLike() - 1);
            issue.setDislike(issue.getDislike() + 1);
            emotion.setStatus(EmotionStatus.DISS);
        } else if (emotion.getStatus() == EmotionStatus.DISS) {
            issue.setDislike(issue.getDislike() - 1);
            emotion.setStatus(EmotionStatus.NONE);
        } else {
            issue.setDislike(issue.getDislike() + 1);
            emotion.setStatus(EmotionStatus.DISS);
        }
        emotionRepository.save(emotion);
        return communityMapper.toDto(issueRepository.save(issue));
    }

    public CommunityDTO save(InteractDTO interactDTO) {
        log.debug("Request to save Interact : {}", interactDTO);
        Interact interact = interactMapper.toEntity(interactDTO);
        interact = interactRepository.save(interact);

        return communityMapper.toDto(interact.getIssue());
    }
}
