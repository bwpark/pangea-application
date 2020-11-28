package com.nuri.pangea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nuri.pangea.PangeaApplicationApp;
import com.nuri.pangea.domain.Category2Issue;
import com.nuri.pangea.domain.enumeration.Category2IssueStatus;
import com.nuri.pangea.repository.Category2IssueRepository;
import com.nuri.pangea.service.Category2IssueService;
import com.nuri.pangea.service.dto.Category2IssueLiteDTO;
import com.nuri.pangea.service.mapper.Category2IssueMapper;
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
 * Integration tests for the {@link Category2IssueResource} REST controller.
 */
@SpringBootTest(classes = PangeaApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Category2IssueResourceIT {
    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Category2IssueStatus DEFAULT_STATUS = Category2IssueStatus.ORIGINATE;
    private static final Category2IssueStatus UPDATED_STATUS = Category2IssueStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private Category2IssueRepository category2IssueRepository;

    @Autowired
    private Category2IssueMapper category2IssueMapper;

    @Autowired
    private Category2IssueService category2IssueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategory2IssueMockMvc;

    private Category2Issue category2Issue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category2Issue createEntity(EntityManager em) {
        Category2Issue category2Issue = new Category2Issue()
            .icon(DEFAULT_ICON)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return category2Issue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Category2Issue createUpdatedEntity(EntityManager em) {
        Category2Issue category2Issue = new Category2Issue()
            .icon(UPDATED_ICON)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return category2Issue;
    }

    @BeforeEach
    public void initTest() {
        category2Issue = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory2Issue() throws Exception {
        int databaseSizeBeforeCreate = category2IssueRepository.findAll().size();
        // Create the Category2Issue
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);
        restCategory2IssueMockMvc
            .perform(
                post("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Category2Issue in the database
        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeCreate + 1);
        Category2Issue testCategory2Issue = category2IssueList.get(category2IssueList.size() - 1);
        assertThat(testCategory2Issue.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testCategory2Issue.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCategory2Issue.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCategory2Issue.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCategory2Issue.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testCategory2Issue.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    @Transactional
    public void createCategory2IssueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = category2IssueRepository.findAll().size();

        // Create the Category2Issue with an existing ID
        category2Issue.setId(1L);
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategory2IssueMockMvc
            .perform(
                post("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Category2Issue in the database
        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2IssueRepository.findAll().size();
        // set the field null
        category2Issue.setName(null);

        // Create the Category2Issue, which fails.
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);

        restCategory2IssueMockMvc
            .perform(
                post("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2IssueRepository.findAll().size();
        // set the field null
        category2Issue.setStatus(null);

        // Create the Category2Issue, which fails.
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);

        restCategory2IssueMockMvc
            .perform(
                post("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2IssueRepository.findAll().size();
        // set the field null
        category2Issue.setCreated(null);

        // Create the Category2Issue, which fails.
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);

        restCategory2IssueMockMvc
            .perform(
                post("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = category2IssueRepository.findAll().size();
        // set the field null
        category2Issue.setModified(null);

        // Create the Category2Issue, which fails.
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);

        restCategory2IssueMockMvc
            .perform(
                post("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isBadRequest());

        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategory2Issues() throws Exception {
        // Initialize the database
        category2IssueRepository.saveAndFlush(category2Issue);

        // Get all the category2IssueList
        restCategory2IssueMockMvc
            .perform(get("/api/category-2-issues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category2Issue.getId().intValue())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }

    @Test
    @Transactional
    public void getCategory2Issue() throws Exception {
        // Initialize the database
        category2IssueRepository.saveAndFlush(category2Issue);

        // Get the category2Issue
        restCategory2IssueMockMvc
            .perform(get("/api/category-2-issues/{id}", category2Issue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(category2Issue.getId().intValue()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategory2Issue() throws Exception {
        // Get the category2Issue
        restCategory2IssueMockMvc.perform(get("/api/category-2-issues/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategory2Issue() throws Exception {
        // Initialize the database
        category2IssueRepository.saveAndFlush(category2Issue);

        int databaseSizeBeforeUpdate = category2IssueRepository.findAll().size();

        // Update the category2Issue
        Category2Issue updatedCategory2Issue = category2IssueRepository.findById(category2Issue.getId()).get();
        // Disconnect from session so that the updates on updatedCategory2Issue are not directly saved in db
        em.detach(updatedCategory2Issue);
        updatedCategory2Issue
            .icon(UPDATED_ICON)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(updatedCategory2Issue);

        restCategory2IssueMockMvc
            .perform(
                put("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isOk());

        // Validate the Category2Issue in the database
        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeUpdate);
        Category2Issue testCategory2Issue = category2IssueList.get(category2IssueList.size() - 1);
        assertThat(testCategory2Issue.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testCategory2Issue.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCategory2Issue.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCategory2Issue.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCategory2Issue.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCategory2Issue.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingCategory2Issue() throws Exception {
        int databaseSizeBeforeUpdate = category2IssueRepository.findAll().size();

        // Create the Category2Issue
        Category2IssueLiteDTO category2IssueDTO = category2IssueMapper.toDto(category2Issue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategory2IssueMockMvc
            .perform(
                put("/api/category-2-issues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(category2IssueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Category2Issue in the database
        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategory2Issue() throws Exception {
        // Initialize the database
        category2IssueRepository.saveAndFlush(category2Issue);

        int databaseSizeBeforeDelete = category2IssueRepository.findAll().size();

        // Delete the category2Issue
        restCategory2IssueMockMvc
            .perform(delete("/api/category-2-issues/{id}", category2Issue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Category2Issue> category2IssueList = category2IssueRepository.findAll();
        assertThat(category2IssueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
