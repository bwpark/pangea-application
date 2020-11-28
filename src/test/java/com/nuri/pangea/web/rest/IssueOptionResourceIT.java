package com.nuri.pangea.web.rest;

import com.nuri.pangea.PangeaApplicationApp;
import com.nuri.pangea.domain.IssueOption;
import com.nuri.pangea.repository.IssueOptionRepository;
import com.nuri.pangea.service.IssueOptionService;
import com.nuri.pangea.service.dto.IssueOptionDTO;
import com.nuri.pangea.service.mapper.IssueOptionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nuri.pangea.domain.enumeration.IssueOptionStatus;
/**
 * Integration tests for the {@link IssueOptionResource} REST controller.
 */
@SpringBootTest(classes = PangeaApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class IssueOptionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final IssueOptionStatus DEFAULT_STATUS = IssueOptionStatus.ORIGINATE;
    private static final IssueOptionStatus UPDATED_STATUS = IssueOptionStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private IssueOptionRepository issueOptionRepository;

    @Autowired
    private IssueOptionMapper issueOptionMapper;

    @Autowired
    private IssueOptionService issueOptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIssueOptionMockMvc;

    private IssueOption issueOption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IssueOption createEntity(EntityManager em) {
        IssueOption issueOption = new IssueOption()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .coin(DEFAULT_COIN)
            .point(DEFAULT_POINT)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return issueOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IssueOption createUpdatedEntity(EntityManager em) {
        IssueOption issueOption = new IssueOption()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return issueOption;
    }

    @BeforeEach
    public void initTest() {
        issueOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createIssueOption() throws Exception {
        int databaseSizeBeforeCreate = issueOptionRepository.findAll().size();
        // Create the IssueOption
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);
        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isCreated());

        // Validate the IssueOption in the database
        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeCreate + 1);
        IssueOption testIssueOption = issueOptionList.get(issueOptionList.size() - 1);
        assertThat(testIssueOption.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testIssueOption.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIssueOption.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testIssueOption.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testIssueOption.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testIssueOption.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testIssueOption.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    @Transactional
    public void createIssueOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = issueOptionRepository.findAll().size();

        // Create the IssueOption with an existing ID
        issueOption.setId(1L);
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IssueOption in the database
        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueOptionRepository.findAll().size();
        // set the field null
        issueOption.setName(null);

        // Create the IssueOption, which fails.
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);


        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueOptionRepository.findAll().size();
        // set the field null
        issueOption.setCoin(null);

        // Create the IssueOption, which fails.
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);


        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueOptionRepository.findAll().size();
        // set the field null
        issueOption.setPoint(null);

        // Create the IssueOption, which fails.
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);


        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueOptionRepository.findAll().size();
        // set the field null
        issueOption.setStatus(null);

        // Create the IssueOption, which fails.
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);


        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueOptionRepository.findAll().size();
        // set the field null
        issueOption.setCreated(null);

        // Create the IssueOption, which fails.
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);


        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueOptionRepository.findAll().size();
        // set the field null
        issueOption.setModified(null);

        // Create the IssueOption, which fails.
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);


        restIssueOptionMockMvc.perform(post("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIssueOptions() throws Exception {
        // Initialize the database
        issueOptionRepository.saveAndFlush(issueOption);

        // Get all the issueOptionList
        restIssueOptionMockMvc.perform(get("/api/issue-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(issueOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    @Transactional
    public void getIssueOption() throws Exception {
        // Initialize the database
        issueOptionRepository.saveAndFlush(issueOption);

        // Get the issueOption
        restIssueOptionMockMvc.perform(get("/api/issue-options/{id}", issueOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(issueOption.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingIssueOption() throws Exception {
        // Get the issueOption
        restIssueOptionMockMvc.perform(get("/api/issue-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIssueOption() throws Exception {
        // Initialize the database
        issueOptionRepository.saveAndFlush(issueOption);

        int databaseSizeBeforeUpdate = issueOptionRepository.findAll().size();

        // Update the issueOption
        IssueOption updatedIssueOption = issueOptionRepository.findById(issueOption.getId()).get();
        // Disconnect from session so that the updates on updatedIssueOption are not directly saved in db
        em.detach(updatedIssueOption);
        updatedIssueOption
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(updatedIssueOption);

        restIssueOptionMockMvc.perform(put("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isOk());

        // Validate the IssueOption in the database
        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeUpdate);
        IssueOption testIssueOption = issueOptionList.get(issueOptionList.size() - 1);
        assertThat(testIssueOption.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testIssueOption.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIssueOption.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testIssueOption.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testIssueOption.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testIssueOption.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testIssueOption.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingIssueOption() throws Exception {
        int databaseSizeBeforeUpdate = issueOptionRepository.findAll().size();

        // Create the IssueOption
        IssueOptionDTO issueOptionDTO = issueOptionMapper.toDto(issueOption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIssueOptionMockMvc.perform(put("/api/issue-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IssueOption in the database
        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIssueOption() throws Exception {
        // Initialize the database
        issueOptionRepository.saveAndFlush(issueOption);

        int databaseSizeBeforeDelete = issueOptionRepository.findAll().size();

        // Delete the issueOption
        restIssueOptionMockMvc.perform(delete("/api/issue-options/{id}", issueOption.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IssueOption> issueOptionList = issueOptionRepository.findAll();
        assertThat(issueOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
