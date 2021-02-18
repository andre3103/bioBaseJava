package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.GerenciaId;
import org.itv.biobase2.repository.GerenciaIdRepository;
import org.itv.biobase2.service.GerenciaIdService;

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

/**
 * Integration tests for the {@link GerenciaIdResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GerenciaIdResourceIT {

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_GERACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_GERACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private GerenciaIdRepository gerenciaIdRepository;

    @Autowired
    private GerenciaIdService gerenciaIdService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGerenciaIdMockMvc;

    private GerenciaId gerenciaId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GerenciaId createEntity(EntityManager em) {
        GerenciaId gerenciaId = new GerenciaId()
            .loginName(DEFAULT_LOGIN_NAME)
            .dataGeracao(DEFAULT_DATA_GERACAO)
            .status(DEFAULT_STATUS);
        return gerenciaId;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GerenciaId createUpdatedEntity(EntityManager em) {
        GerenciaId gerenciaId = new GerenciaId()
            .loginName(UPDATED_LOGIN_NAME)
            .dataGeracao(UPDATED_DATA_GERACAO)
            .status(UPDATED_STATUS);
        return gerenciaId;
    }

    @BeforeEach
    public void initTest() {
        gerenciaId = createEntity(em);
    }

    @Test
    @Transactional
    public void createGerenciaId() throws Exception {
        int databaseSizeBeforeCreate = gerenciaIdRepository.findAll().size();
        // Create the GerenciaId
        restGerenciaIdMockMvc.perform(post("/api/gerencia-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerenciaId)))
            .andExpect(status().isCreated());

        // Validate the GerenciaId in the database
        List<GerenciaId> gerenciaIdList = gerenciaIdRepository.findAll();
        assertThat(gerenciaIdList).hasSize(databaseSizeBeforeCreate + 1);
        GerenciaId testGerenciaId = gerenciaIdList.get(gerenciaIdList.size() - 1);
        assertThat(testGerenciaId.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testGerenciaId.getDataGeracao()).isEqualTo(DEFAULT_DATA_GERACAO);
        assertThat(testGerenciaId.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createGerenciaIdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gerenciaIdRepository.findAll().size();

        // Create the GerenciaId with an existing ID
        gerenciaId.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGerenciaIdMockMvc.perform(post("/api/gerencia-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerenciaId)))
            .andExpect(status().isBadRequest());

        // Validate the GerenciaId in the database
        List<GerenciaId> gerenciaIdList = gerenciaIdRepository.findAll();
        assertThat(gerenciaIdList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGerenciaIds() throws Exception {
        // Initialize the database
        gerenciaIdRepository.saveAndFlush(gerenciaId);

        // Get all the gerenciaIdList
        restGerenciaIdMockMvc.perform(get("/api/gerencia-ids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gerenciaId.getId().intValue())))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME)))
            .andExpect(jsonPath("$.[*].dataGeracao").value(hasItem(DEFAULT_DATA_GERACAO.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getGerenciaId() throws Exception {
        // Initialize the database
        gerenciaIdRepository.saveAndFlush(gerenciaId);

        // Get the gerenciaId
        restGerenciaIdMockMvc.perform(get("/api/gerencia-ids/{id}", gerenciaId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gerenciaId.getId().intValue()))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME))
            .andExpect(jsonPath("$.dataGeracao").value(DEFAULT_DATA_GERACAO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingGerenciaId() throws Exception {
        // Get the gerenciaId
        restGerenciaIdMockMvc.perform(get("/api/gerencia-ids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGerenciaId() throws Exception {
        // Initialize the database
        gerenciaIdService.save(gerenciaId);

        int databaseSizeBeforeUpdate = gerenciaIdRepository.findAll().size();

        // Update the gerenciaId
        GerenciaId updatedGerenciaId = gerenciaIdRepository.findById(gerenciaId.getId()).get();
        // Disconnect from session so that the updates on updatedGerenciaId are not directly saved in db
        em.detach(updatedGerenciaId);
        updatedGerenciaId
            .loginName(UPDATED_LOGIN_NAME)
            .dataGeracao(UPDATED_DATA_GERACAO)
            .status(UPDATED_STATUS);

        restGerenciaIdMockMvc.perform(put("/api/gerencia-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGerenciaId)))
            .andExpect(status().isOk());

        // Validate the GerenciaId in the database
        List<GerenciaId> gerenciaIdList = gerenciaIdRepository.findAll();
        assertThat(gerenciaIdList).hasSize(databaseSizeBeforeUpdate);
        GerenciaId testGerenciaId = gerenciaIdList.get(gerenciaIdList.size() - 1);
        assertThat(testGerenciaId.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testGerenciaId.getDataGeracao()).isEqualTo(UPDATED_DATA_GERACAO);
        assertThat(testGerenciaId.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingGerenciaId() throws Exception {
        int databaseSizeBeforeUpdate = gerenciaIdRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGerenciaIdMockMvc.perform(put("/api/gerencia-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerenciaId)))
            .andExpect(status().isBadRequest());

        // Validate the GerenciaId in the database
        List<GerenciaId> gerenciaIdList = gerenciaIdRepository.findAll();
        assertThat(gerenciaIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGerenciaId() throws Exception {
        // Initialize the database
        gerenciaIdService.save(gerenciaId);

        int databaseSizeBeforeDelete = gerenciaIdRepository.findAll().size();

        // Delete the gerenciaId
        restGerenciaIdMockMvc.perform(delete("/api/gerencia-ids/{id}", gerenciaId.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GerenciaId> gerenciaIdList = gerenciaIdRepository.findAll();
        assertThat(gerenciaIdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
