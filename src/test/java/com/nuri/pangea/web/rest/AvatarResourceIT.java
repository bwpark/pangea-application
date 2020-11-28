package com.nuri.pangea.web.rest;

import com.nuri.pangea.PangeaApplicationApp;
import com.nuri.pangea.domain.Avatar;
import com.nuri.pangea.repository.AvatarRepository;
import com.nuri.pangea.service.AvatarService;
import com.nuri.pangea.service.dto.AvatarDTO;
import com.nuri.pangea.service.mapper.AvatarMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nuri.pangea.domain.enumeration.AvatarStatus;
/**
 * Integration tests for the {@link AvatarResource} REST controller.
 */
@SpringBootTest(classes = PangeaApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AvatarResourceIT {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BANNER = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BANNER = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BANNER_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BANNER_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final Integer DEFAULT_LIKE = 1;
    private static final Integer UPDATED_LIKE = 2;

    private static final Integer DEFAULT_DISLIKE = 1;
    private static final Integer UPDATED_DISLIKE = 2;

    private static final Integer DEFAULT_GRADE = 1;
    private static final Integer UPDATED_GRADE = 2;

    private static final Integer DEFAULT_CREDIT = 1;
    private static final Integer UPDATED_CREDIT = 2;

    private static final Integer DEFAULT_VIEWS = 1;
    private static final Integer UPDATED_VIEWS = 2;

    private static final Integer DEFAULT_COMMENTS = 1;
    private static final Integer UPDATED_COMMENTS = 2;

    private static final AvatarStatus DEFAULT_STATUS = AvatarStatus.ACTIVATED;
    private static final AvatarStatus UPDATED_STATUS = AvatarStatus.VALID;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private AvatarMapper avatarMapper;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAvatarMockMvc;

    private Avatar avatar;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avatar createEntity(EntityManager em) {
        Avatar avatar = new Avatar()
            .categoryName(DEFAULT_CATEGORY_NAME)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .text(DEFAULT_TEXT)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE)
            .banner(DEFAULT_BANNER)
            .bannerContentType(DEFAULT_BANNER_CONTENT_TYPE)
            .coin(DEFAULT_COIN)
            .point(DEFAULT_POINT)
            .like(DEFAULT_LIKE)
            .dislike(DEFAULT_DISLIKE)
            .grade(DEFAULT_GRADE)
            .credit(DEFAULT_CREDIT)
            .views(DEFAULT_VIEWS)
            .comments(DEFAULT_COMMENTS)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return avatar;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Avatar createUpdatedEntity(EntityManager em) {
        Avatar avatar = new Avatar()
            .categoryName(UPDATED_CATEGORY_NAME)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .like(UPDATED_LIKE)
            .dislike(UPDATED_DISLIKE)
            .grade(UPDATED_GRADE)
            .credit(UPDATED_CREDIT)
            .views(UPDATED_VIEWS)
            .comments(UPDATED_COMMENTS)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return avatar;
    }

    @BeforeEach
    public void initTest() {
        avatar = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvatar() throws Exception {
        int databaseSizeBeforeCreate = avatarRepository.findAll().size();
        // Create the Avatar
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);
        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isCreated());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeCreate + 1);
        Avatar testAvatar = avatarList.get(avatarList.size() - 1);
        assertThat(testAvatar.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testAvatar.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAvatar.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAvatar.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testAvatar.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testAvatar.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
        assertThat(testAvatar.getBanner()).isEqualTo(DEFAULT_BANNER);
        assertThat(testAvatar.getBannerContentType()).isEqualTo(DEFAULT_BANNER_CONTENT_TYPE);
        assertThat(testAvatar.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testAvatar.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testAvatar.getLike()).isEqualTo(DEFAULT_LIKE);
        assertThat(testAvatar.getDislike()).isEqualTo(DEFAULT_DISLIKE);
        assertThat(testAvatar.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testAvatar.getCredit()).isEqualTo(DEFAULT_CREDIT);
        assertThat(testAvatar.getViews()).isEqualTo(DEFAULT_VIEWS);
        assertThat(testAvatar.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testAvatar.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAvatar.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testAvatar.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    @Transactional
    public void createAvatarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avatarRepository.findAll().size();

        // Create the Avatar with an existing ID
        avatar.setId(1L);
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setName(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setCoin(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setPoint(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLikeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setLike(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDislikeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setDislike(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setGrade(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreditIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setCredit(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkViewsIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setViews(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setComments(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setStatus(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setCreated(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarRepository.findAll().size();
        // set the field null
        avatar.setModified(null);

        // Create the Avatar, which fails.
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);


        restAvatarMockMvc.perform(post("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvatars() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);

        // Get all the avatarList
        restAvatarMockMvc.perform(get("/api/avatars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avatar.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].bannerContentType").value(hasItem(DEFAULT_BANNER_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].banner").value(hasItem(Base64Utils.encodeToString(DEFAULT_BANNER))))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].like").value(hasItem(DEFAULT_LIKE)))
            .andExpect(jsonPath("$.[*].dislike").value(hasItem(DEFAULT_DISLIKE)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].credit").value(hasItem(DEFAULT_CREDIT)))
            .andExpect(jsonPath("$.[*].views").value(hasItem(DEFAULT_VIEWS)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    @Transactional
    public void getAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);

        // Get the avatar
        restAvatarMockMvc.perform(get("/api/avatars/{id}", avatar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(avatar.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.bannerContentType").value(DEFAULT_BANNER_CONTENT_TYPE))
            .andExpect(jsonPath("$.banner").value(Base64Utils.encodeToString(DEFAULT_BANNER)))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.like").value(DEFAULT_LIKE))
            .andExpect(jsonPath("$.dislike").value(DEFAULT_DISLIKE))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.credit").value(DEFAULT_CREDIT))
            .andExpect(jsonPath("$.views").value(DEFAULT_VIEWS))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAvatar() throws Exception {
        // Get the avatar
        restAvatarMockMvc.perform(get("/api/avatars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);

        int databaseSizeBeforeUpdate = avatarRepository.findAll().size();

        // Update the avatar
        Avatar updatedAvatar = avatarRepository.findById(avatar.getId()).get();
        // Disconnect from session so that the updates on updatedAvatar are not directly saved in db
        em.detach(updatedAvatar);
        updatedAvatar
            .categoryName(UPDATED_CATEGORY_NAME)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .banner(UPDATED_BANNER)
            .bannerContentType(UPDATED_BANNER_CONTENT_TYPE)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .like(UPDATED_LIKE)
            .dislike(UPDATED_DISLIKE)
            .grade(UPDATED_GRADE)
            .credit(UPDATED_CREDIT)
            .views(UPDATED_VIEWS)
            .comments(UPDATED_COMMENTS)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        AvatarDTO avatarDTO = avatarMapper.toDto(updatedAvatar);

        restAvatarMockMvc.perform(put("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isOk());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeUpdate);
        Avatar testAvatar = avatarList.get(avatarList.size() - 1);
        assertThat(testAvatar.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testAvatar.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAvatar.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAvatar.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testAvatar.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testAvatar.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
        assertThat(testAvatar.getBanner()).isEqualTo(UPDATED_BANNER);
        assertThat(testAvatar.getBannerContentType()).isEqualTo(UPDATED_BANNER_CONTENT_TYPE);
        assertThat(testAvatar.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testAvatar.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testAvatar.getLike()).isEqualTo(UPDATED_LIKE);
        assertThat(testAvatar.getDislike()).isEqualTo(UPDATED_DISLIKE);
        assertThat(testAvatar.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testAvatar.getCredit()).isEqualTo(UPDATED_CREDIT);
        assertThat(testAvatar.getViews()).isEqualTo(UPDATED_VIEWS);
        assertThat(testAvatar.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testAvatar.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAvatar.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testAvatar.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingAvatar() throws Exception {
        int databaseSizeBeforeUpdate = avatarRepository.findAll().size();

        // Create the Avatar
        AvatarDTO avatarDTO = avatarMapper.toDto(avatar);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvatarMockMvc.perform(put("/api/avatars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Avatar in the database
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvatar() throws Exception {
        // Initialize the database
        avatarRepository.saveAndFlush(avatar);

        int databaseSizeBeforeDelete = avatarRepository.findAll().size();

        // Delete the avatar
        restAvatarMockMvc.perform(delete("/api/avatars/{id}", avatar.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Avatar> avatarList = avatarRepository.findAll();
        assertThat(avatarList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
