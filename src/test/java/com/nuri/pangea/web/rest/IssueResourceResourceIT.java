package com.nuri.pangea.web.rest;

import com.nuri.pangea.PangeaApplicationApp;
import com.nuri.pangea.domain.IssueResource;
import com.nuri.pangea.repository.IssueResourceRepository;
import com.nuri.pangea.service.IssueResourceService;
import com.nuri.pangea.service.dto.IssueResourceDTO;
import com.nuri.pangea.service.mapper.IssueResourceMapper;

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

import com.nuri.pangea.domain.enumeration.ResourceType;
/**
 * Integration tests for the {@link IssueResourceResource} REST controller.
 */
@SpringBootTest(classes = PangeaApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class IssueResourceResourceIT {

    private static final ResourceType DEFAULT_TYPE = ResourceType.LINK;
    private static final ResourceType UPDATED_TYPE = ResourceType.IMAGE;

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private IssueResourceRepository issueResourceRepository;

    @Autowired
    private IssueResourceMapper issueResourceMapper;

    @Autowired
    private IssueResourceService issueResourceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIssueResourceMockMvc;

    private IssueResource issueResource;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IssueResource createEntity(EntityManager em) {
        IssueResource issueResource = new IssueResource()
            .type(DEFAULT_TYPE)
            .link(DEFAULT_LINK)
            .created(DEFAULT_CREATED);
        return issueResource;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IssueResource createUpdatedEntity(EntityManager em) {
        IssueResource issueResource = new IssueResource()
            .type(UPDATED_TYPE)
            .link(UPDATED_LINK)
            .created(UPDATED_CREATED);
        return issueResource;
    }

    @BeforeEach
    public void initTest() {
        issueResource = createEntity(em);
    }

    @Test
    @Transactional
    public void createIssueResource() throws Exception {
        int databaseSizeBeforeCreate = issueResourceRepository.findAll().size();
        // Create the IssueResource
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(issueResource);
        restIssueResourceMockMvc.perform(post("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isCreated());

        // Validate the IssueResource in the database
        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeCreate + 1);
        IssueResource testIssueResource = issueResourceList.get(issueResourceList.size() - 1);
        assertThat(testIssueResource.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testIssueResource.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testIssueResource.getCreated()).isEqualTo(DEFAULT_CREATED);
    }

    @Test
    @Transactional
    public void createIssueResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = issueResourceRepository.findAll().size();

        // Create the IssueResource with an existing ID
        issueResource.setId(1L);
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(issueResource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIssueResourceMockMvc.perform(post("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IssueResource in the database
        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueResourceRepository.findAll().size();
        // set the field null
        issueResource.setType(null);

        // Create the IssueResource, which fails.
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(issueResource);


        restIssueResourceMockMvc.perform(post("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isBadRequest());

        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueResourceRepository.findAll().size();
        // set the field null
        issueResource.setLink(null);

        // Create the IssueResource, which fails.
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(issueResource);


        restIssueResourceMockMvc.perform(post("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isBadRequest());

        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueResourceRepository.findAll().size();
        // set the field null
        issueResource.setCreated(null);

        // Create the IssueResource, which fails.
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(issueResource);


        restIssueResourceMockMvc.perform(post("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isBadRequest());

        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIssueResources() throws Exception {
        // Initialize the database
        issueResourceRepository.saveAndFlush(issueResource);

        // Get all the issueResourceList
        restIssueResourceMockMvc.perform(get("/api/issue-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(issueResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())));
    }
    
    @Test
    @Transactional
    public void getIssueResource() throws Exception {
        // Initialize the database
        issueResourceRepository.saveAndFlush(issueResource);

        // Get the issueResource
        restIssueResourceMockMvc.perform(get("/api/issue-resources/{id}", issueResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(issueResource.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingIssueResource() throws Exception {
        // Get the issueResource
        restIssueResourceMockMvc.perform(get("/api/issue-resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIssueResource() throws Exception {
        // Initialize the database
        issueResourceRepository.saveAndFlush(issueResource);

        int databaseSizeBeforeUpdate = issueResourceRepository.findAll().size();

        // Update the issueResource
        IssueResource updatedIssueResource = issueResourceRepository.findById(issueResource.getId()).get();
        // Disconnect from session so that the updates on updatedIssueResource are not directly saved in db
        em.detach(updatedIssueResource);
        updatedIssueResource
            .type(UPDATED_TYPE)
            .link(UPDATED_LINK)
            .created(UPDATED_CREATED);
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(updatedIssueResource);

        restIssueResourceMockMvc.perform(put("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isOk());

        // Validate the IssueResource in the database
        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeUpdate);
        IssueResource testIssueResource = issueResourceList.get(issueResourceList.size() - 1);
        assertThat(testIssueResource.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testIssueResource.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testIssueResource.getCreated()).isEqualTo(UPDATED_CREATED);
    }

    @Test
    @Transactional
    public void updateNonExistingIssueResource() throws Exception {
        int databaseSizeBeforeUpdate = issueResourceRepository.findAll().size();

        // Create the IssueResource
        IssueResourceDTO issueResourceDTO = issueResourceMapper.toDto(issueResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIssueResourceMockMvc.perform(put("/api/issue-resources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IssueResource in the database
        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIssueResource() throws Exception {
        // Initialize the database
        issueResourceRepository.saveAndFlush(issueResource);

        int databaseSizeBeforeDelete = issueResourceRepository.findAll().size();

        // Delete the issueResource
        restIssueResourceMockMvc.perform(delete("/api/issue-resources/{id}", issueResource.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IssueResource> issueResourceList = issueResourceRepository.findAll();
        assertThat(issueResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
