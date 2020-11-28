package com.nuri.pangea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nuri.pangea.PangeaApplicationApp;
import com.nuri.pangea.domain.Category2avatar;
import com.nuri.pangea.domain.enumeration.Category2avatarStatus;
import com.nuri.pangea.repository.Category2avatarRepository;
import com.nuri.pangea.service.Category2avatarService;
import com.nuri.pangea.service.dto.Category2avatarLiteDTO;
import com.nuri.pangea.service.mapper.Category2avatarMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link Category2avatarResource} REST controller.
 */
@SpringBootTest(classes = PangeaApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Category2avatarResourceIT {
    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Category2avatarStatus DEFAULT_STATUS = Category2avatarStatus.ORIGINATE;
    private static final Category2avatarStatus UPDATED_STATUS = Category2avatarStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private Category2avatarRepository category2avatarRepository;

    @Autowired
    private Category2avatarMapper category2avatarMapper;

    @Autowired
    private Category2avatarService category2avatarService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategory2avatarMockMvc;

    private Category2avatar category2avatar;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category2avatar createEntity(EntityManager em) {
        Category2avatar category2avatar = new Category2avatar()
            .icon(DEFAULT_ICON)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return category2avatar;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category2avatar createUpdatedEntity(EntityManager em) {
        Category2avatar category2avatar = new Category2avatar()
            .icon(UPDATED_ICON)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return category2avatar;
    }

    @BeforeEach
    public void initTest() {
        category2avatar = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory2avatar() throws Exception {
        int databaseSizeBeforeCreate = category2avatarRepository.findAll().size();
        // Create the Category2avatar
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);
        restCategory2avatarMockMvc
            .perform(
                post("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Category2avatar in the database
        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeCreate + 1);
        Category2avatar testCategory2avatar = category2avatarList.get(category2avatarList.size() - 1);
        assertThat(testCategory2avatar.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testCategory2avatar.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCategory2avatar.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCategory2avatar.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCategory2avatar.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testCategory2avatar.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    @Transactional
    public void createCategory2avatarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = category2avatarRepository.findAll().size();

        // Create the Category2avatar with an existing ID
        category2avatar.setId(1L);
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategory2avatarMockMvc
            .perform(
                post("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Category2avatar in the database
        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2avatarRepository.findAll().size();
        // set the field null
        category2avatar.setName(null);

        // Create the Category2avatar, which fails.
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);

        restCategory2avatarMockMvc
            .perform(
                post("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2avatarRepository.findAll().size();
        // set the field null
        category2avatar.setStatus(null);

        // Create the Category2avatar, which fails.
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);

        restCategory2avatarMockMvc
            .perform(
                post("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2avatarRepository.findAll().size();
        // set the field null
        category2avatar.setCreated(null);

        // Create the Category2avatar, which fails.
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);

        restCategory2avatarMockMvc
            .perform(
                post("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2avatarRepository.findAll().size();
        // set the field null
        category2avatar.setModified(null);

        // Create the Category2avatar, which fails.
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);

        restCategory2avatarMockMvc
            .perform(
                post("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategory2avatars() throws Exception {
        // Initialize the database
        category2avatarRepository.saveAndFlush(category2avatar);

        // Get all the category2avatarList
        restCategory2avatarMockMvc
            .perform(get("/api/category-2-avatars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category2avatar.getId().intValue())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }

    @Test
    @Transactional
    public void getCategory2avatar() throws Exception {
        // Initialize the database
        category2avatarRepository.saveAndFlush(category2avatar);

        // Get the category2avatar
        restCategory2avatarMockMvc
            .perform(get("/api/category-2-avatars/{id}", category2avatar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(category2avatar.getId().intValue()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategory2avatar() throws Exception {
        // Get the category2avatar
        restCategory2avatarMockMvc.perform(get("/api/category-2-avatars/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategory2avatar() throws Exception {
        // Initialize the database
        category2avatarRepository.saveAndFlush(category2avatar);

        int databaseSizeBeforeUpdate = category2avatarRepository.findAll().size();

        // Update the category2avatar
        Category2avatar updatedCategory2avatar = category2avatarRepository.findById(category2avatar.getId()).get();
        // Disconnect from session so that the updates on updatedCategory2avatar are not directly saved in db
        em.detach(updatedCategory2avatar);
        updatedCategory2avatar
            .icon(UPDATED_ICON)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(updatedCategory2avatar);

        restCategory2avatarMockMvc
            .perform(
                put("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isOk());

        // Validate the Category2avatar in the database
        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeUpdate);
        Category2avatar testCategory2avatar = category2avatarList.get(category2avatarList.size() - 1);
        assertThat(testCategory2avatar.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testCategory2avatar.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCategory2avatar.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCategory2avatar.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCategory2avatar.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCategory2avatar.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingCategory2avatar() throws Exception {
        int databaseSizeBeforeUpdate = category2avatarRepository.findAll().size();

        // Create the Category2avatar
        Category2avatarLiteDTO category2avatarDTO = category2avatarMapper.toDto(category2avatar);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategory2avatarMockMvc
            .perform(
                put("/api/category-2-avatars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2avatarDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Category2avatar in the database
        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategory2avatar() throws Exception {
        // Initialize the database
        category2avatarRepository.saveAndFlush(category2avatar);

        int databaseSizeBeforeDelete = category2avatarRepository.findAll().size();

        // Delete the category2avatar
        restCategory2avatarMockMvc
            .perform(delete("/api/category-2-avatars/{id}", category2avatar.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Category2avatar> category2avatarList = category2avatarRepository.findAll();
        assertThat(category2avatarList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
