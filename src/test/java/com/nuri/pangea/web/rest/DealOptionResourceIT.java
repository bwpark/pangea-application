package com.nuri.pangea.web.rest;

import com.nuri.pangea.PangeaApplicationApp;
import com.nuri.pangea.domain.DealOption;
import com.nuri.pangea.repository.DealOptionRepository;
import com.nuri.pangea.service.DealOptionService;
import com.nuri.pangea.service.dto.DealOptionDTO;
import com.nuri.pangea.service.mapper.DealOptionMapper;

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

import com.nuri.pangea.domain.enumeration.DealOptionStatus;
/**
 * Integration tests for the {@link DealOptionResource} REST controller.
 */
@SpringBootTest(classes = PangeaApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealOptionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final DealOptionStatus DEFAULT_STATUS = DealOptionStatus.ORIGINATE;
    private static final DealOptionStatus UPDATED_STATUS = DealOptionStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DealOptionRepository dealOptionRepository;

    @Autowired
    private DealOptionMapper dealOptionMapper;

    @Autowired
    private DealOptionService dealOptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealOptionMockMvc;

    private DealOption dealOption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealOption createEntity(EntityManager em) {
        DealOption dealOption = new DealOption()
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return dealOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealOption createUpdatedEntity(EntityManager em) {
        DealOption dealOption = new DealOption()
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return dealOption;
    }

    @BeforeEach
    public void initTest() {
        dealOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealOption() throws Exception {
        int databaseSizeBeforeCreate = dealOptionRepository.findAll().size();
        // Create the DealOption
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);
        restDealOptionMockMvc.perform(post("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isCreated());

        // Validate the DealOption in the database
        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeCreate + 1);
        DealOption testDealOption = dealOptionList.get(dealOptionList.size() - 1);
        assertThat(testDealOption.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDealOption.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDealOption.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testDealOption.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    @Transactional
    public void createDealOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealOptionRepository.findAll().size();

        // Create the DealOption with an existing ID
        dealOption.setId(1L);
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealOptionMockMvc.perform(post("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealOption in the database
        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealOptionRepository.findAll().size();
        // set the field null
        dealOption.setName(null);

        // Create the DealOption, which fails.
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);


        restDealOptionMockMvc.perform(post("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isBadRequest());

        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealOptionRepository.findAll().size();
        // set the field null
        dealOption.setStatus(null);

        // Create the DealOption, which fails.
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);


        restDealOptionMockMvc.perform(post("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isBadRequest());

        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealOptionRepository.findAll().size();
        // set the field null
        dealOption.setCreated(null);

        // Create the DealOption, which fails.
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);


        restDealOptionMockMvc.perform(post("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isBadRequest());

        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealOptionRepository.findAll().size();
        // set the field null
        dealOption.setModified(null);

        // Create the DealOption, which fails.
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);


        restDealOptionMockMvc.perform(post("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isBadRequest());

        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealOptions() throws Exception {
        // Initialize the database
        dealOptionRepository.saveAndFlush(dealOption);

        // Get all the dealOptionList
        restDealOptionMockMvc.perform(get("/api/deal-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    @Transactional
    public void getDealOption() throws Exception {
        // Initialize the database
        dealOptionRepository.saveAndFlush(dealOption);

        // Get the dealOption
        restDealOptionMockMvc.perform(get("/api/deal-options/{id}", dealOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dealOption.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDealOption() throws Exception {
        // Get the dealOption
        restDealOptionMockMvc.perform(get("/api/deal-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealOption() throws Exception {
        // Initialize the database
        dealOptionRepository.saveAndFlush(dealOption);

        int databaseSizeBeforeUpdate = dealOptionRepository.findAll().size();

        // Update the dealOption
        DealOption updatedDealOption = dealOptionRepository.findById(dealOption.getId()).get();
        // Disconnect from session so that the updates on updatedDealOption are not directly saved in db
        em.detach(updatedDealOption);
        updatedDealOption
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(updatedDealOption);

        restDealOptionMockMvc.perform(put("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isOk());

        // Validate the DealOption in the database
        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeUpdate);
        DealOption testDealOption = dealOptionList.get(dealOptionList.size() - 1);
        assertThat(testDealOption.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDealOption.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDealOption.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testDealOption.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingDealOption() throws Exception {
        int databaseSizeBeforeUpdate = dealOptionRepository.findAll().size();

        // Create the DealOption
        DealOptionDTO dealOptionDTO = dealOptionMapper.toDto(dealOption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealOptionMockMvc.perform(put("/api/deal-options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealOption in the database
        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealOption() throws Exception {
        // Initialize the database
        dealOptionRepository.saveAndFlush(dealOption);

        int databaseSizeBeforeDelete = dealOptionRepository.findAll().size();

        // Delete the dealOption
        restDealOptionMockMvc.perform(delete("/api/deal-options/{id}", dealOption.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealOption> dealOptionList = dealOptionRepository.findAll();
        assertThat(dealOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
