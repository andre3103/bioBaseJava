package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.Recebimento;
import org.itv.biobase2.repository.RecebimentoRepository;
import org.itv.biobase2.service.RecebimentoService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RecebimentoResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RecebimentoResourceIT {

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RecebimentoRepository recebimentoRepository;

    @Autowired
    private RecebimentoService recebimentoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRecebimentoMockMvc;

    private Recebimento recebimento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recebimento createEntity(EntityManager em) {
        Recebimento recebimento = new Recebimento()
            .loginName(DEFAULT_LOGIN_NAME)
            .data(DEFAULT_DATA);
        return recebimento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Recebimento createUpdatedEntity(EntityManager em) {
        Recebimento recebimento = new Recebimento()
            .loginName(UPDATED_LOGIN_NAME)
            .data(UPDATED_DATA);
        return recebimento;
    }

    @BeforeEach
    public void initTest() {
        recebimento = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecebimento() throws Exception {
        int databaseSizeBeforeCreate = recebimentoRepository.findAll().size();
        // Create the Recebimento
        restRecebimentoMockMvc.perform(post("/api/recebimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recebimento)))
            .andExpect(status().isCreated());

        // Validate the Recebimento in the database
        List<Recebimento> recebimentoList = recebimentoRepository.findAll();
        assertThat(recebimentoList).hasSize(databaseSizeBeforeCreate + 1);
        Recebimento testRecebimento = recebimentoList.get(recebimentoList.size() - 1);
        assertThat(testRecebimento.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testRecebimento.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createRecebimentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recebimentoRepository.findAll().size();

        // Create the Recebimento with an existing ID
        recebimento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecebimentoMockMvc.perform(post("/api/recebimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recebimento)))
            .andExpect(status().isBadRequest());

        // Validate the Recebimento in the database
        List<Recebimento> recebimentoList = recebimentoRepository.findAll();
        assertThat(recebimentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRecebimentos() throws Exception {
        // Initialize the database
        recebimentoRepository.saveAndFlush(recebimento);

        // Get all the recebimentoList
        restRecebimentoMockMvc.perform(get("/api/recebimentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recebimento.getId().intValue())))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getRecebimento() throws Exception {
        // Initialize the database
        recebimentoRepository.saveAndFlush(recebimento);

        // Get the recebimento
        restRecebimentoMockMvc.perform(get("/api/recebimentos/{id}", recebimento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(recebimento.getId().intValue()))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRecebimento() throws Exception {
        // Get the recebimento
        restRecebimentoMockMvc.perform(get("/api/recebimentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecebimento() throws Exception {
        // Initialize the database
        recebimentoService.save(recebimento);

        int databaseSizeBeforeUpdate = recebimentoRepository.findAll().size();

        // Update the recebimento
        Recebimento updatedRecebimento = recebimentoRepository.findById(recebimento.getId()).get();
        // Disconnect from session so that the updates on updatedRecebimento are not directly saved in db
        em.detach(updatedRecebimento);
        updatedRecebimento
            .loginName(UPDATED_LOGIN_NAME)
            .data(UPDATED_DATA);

        restRecebimentoMockMvc.perform(put("/api/recebimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecebimento)))
            .andExpect(status().isOk());

        // Validate the Recebimento in the database
        List<Recebimento> recebimentoList = recebimentoRepository.findAll();
        assertThat(recebimentoList).hasSize(databaseSizeBeforeUpdate);
        Recebimento testRecebimento = recebimentoList.get(recebimentoList.size() - 1);
        assertThat(testRecebimento.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testRecebimento.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingRecebimento() throws Exception {
        int databaseSizeBeforeUpdate = recebimentoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecebimentoMockMvc.perform(put("/api/recebimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recebimento)))
            .andExpect(status().isBadRequest());

        // Validate the Recebimento in the database
        List<Recebimento> recebimentoList = recebimentoRepository.findAll();
        assertThat(recebimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecebimento() throws Exception {
        // Initialize the database
        recebimentoService.save(recebimento);

        int databaseSizeBeforeDelete = recebimentoRepository.findAll().size();

        // Delete the recebimento
        restRecebimentoMockMvc.perform(delete("/api/recebimentos/{id}", recebimento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Recebimento> recebimentoList = recebimentoRepository.findAll();
        assertThat(recebimentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
