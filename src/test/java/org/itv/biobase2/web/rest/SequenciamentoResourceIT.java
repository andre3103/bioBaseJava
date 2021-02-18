package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.Sequenciamento;
import org.itv.biobase2.repository.SequenciamentoRepository;
import org.itv.biobase2.service.SequenciamentoService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SequenciamentoResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SequenciamentoResourceIT {

    private static final String DEFAULT_POSIAO = "AAAAAAAAAA";
    private static final String UPDATED_POSIAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Autowired
    private SequenciamentoRepository sequenciamentoRepository;

    @Autowired
    private SequenciamentoService sequenciamentoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSequenciamentoMockMvc;

    private Sequenciamento sequenciamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sequenciamento createEntity(EntityManager em) {
        Sequenciamento sequenciamento = new Sequenciamento()
            .posiao(DEFAULT_POSIAO)
            .status(DEFAULT_STATUS);
        return sequenciamento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sequenciamento createUpdatedEntity(EntityManager em) {
        Sequenciamento sequenciamento = new Sequenciamento()
            .posiao(UPDATED_POSIAO)
            .status(UPDATED_STATUS);
        return sequenciamento;
    }

    @BeforeEach
    public void initTest() {
        sequenciamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createSequenciamento() throws Exception {
        int databaseSizeBeforeCreate = sequenciamentoRepository.findAll().size();
        // Create the Sequenciamento
        restSequenciamentoMockMvc.perform(post("/api/sequenciamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sequenciamento)))
            .andExpect(status().isCreated());

        // Validate the Sequenciamento in the database
        List<Sequenciamento> sequenciamentoList = sequenciamentoRepository.findAll();
        assertThat(sequenciamentoList).hasSize(databaseSizeBeforeCreate + 1);
        Sequenciamento testSequenciamento = sequenciamentoList.get(sequenciamentoList.size() - 1);
        assertThat(testSequenciamento.getPosiao()).isEqualTo(DEFAULT_POSIAO);
        assertThat(testSequenciamento.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSequenciamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sequenciamentoRepository.findAll().size();

        // Create the Sequenciamento with an existing ID
        sequenciamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSequenciamentoMockMvc.perform(post("/api/sequenciamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sequenciamento)))
            .andExpect(status().isBadRequest());

        // Validate the Sequenciamento in the database
        List<Sequenciamento> sequenciamentoList = sequenciamentoRepository.findAll();
        assertThat(sequenciamentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSequenciamentos() throws Exception {
        // Initialize the database
        sequenciamentoRepository.saveAndFlush(sequenciamento);

        // Get all the sequenciamentoList
        restSequenciamentoMockMvc.perform(get("/api/sequenciamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sequenciamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].posiao").value(hasItem(DEFAULT_POSIAO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getSequenciamento() throws Exception {
        // Initialize the database
        sequenciamentoRepository.saveAndFlush(sequenciamento);

        // Get the sequenciamento
        restSequenciamentoMockMvc.perform(get("/api/sequenciamentos/{id}", sequenciamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sequenciamento.getId().intValue()))
            .andExpect(jsonPath("$.posiao").value(DEFAULT_POSIAO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingSequenciamento() throws Exception {
        // Get the sequenciamento
        restSequenciamentoMockMvc.perform(get("/api/sequenciamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSequenciamento() throws Exception {
        // Initialize the database
        sequenciamentoService.save(sequenciamento);

        int databaseSizeBeforeUpdate = sequenciamentoRepository.findAll().size();

        // Update the sequenciamento
        Sequenciamento updatedSequenciamento = sequenciamentoRepository.findById(sequenciamento.getId()).get();
        // Disconnect from session so that the updates on updatedSequenciamento are not directly saved in db
        em.detach(updatedSequenciamento);
        updatedSequenciamento
            .posiao(UPDATED_POSIAO)
            .status(UPDATED_STATUS);

        restSequenciamentoMockMvc.perform(put("/api/sequenciamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSequenciamento)))
            .andExpect(status().isOk());

        // Validate the Sequenciamento in the database
        List<Sequenciamento> sequenciamentoList = sequenciamentoRepository.findAll();
        assertThat(sequenciamentoList).hasSize(databaseSizeBeforeUpdate);
        Sequenciamento testSequenciamento = sequenciamentoList.get(sequenciamentoList.size() - 1);
        assertThat(testSequenciamento.getPosiao()).isEqualTo(UPDATED_POSIAO);
        assertThat(testSequenciamento.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSequenciamento() throws Exception {
        int databaseSizeBeforeUpdate = sequenciamentoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSequenciamentoMockMvc.perform(put("/api/sequenciamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sequenciamento)))
            .andExpect(status().isBadRequest());

        // Validate the Sequenciamento in the database
        List<Sequenciamento> sequenciamentoList = sequenciamentoRepository.findAll();
        assertThat(sequenciamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSequenciamento() throws Exception {
        // Initialize the database
        sequenciamentoService.save(sequenciamento);

        int databaseSizeBeforeDelete = sequenciamentoRepository.findAll().size();

        // Delete the sequenciamento
        restSequenciamentoMockMvc.perform(delete("/api/sequenciamentos/{id}", sequenciamento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sequenciamento> sequenciamentoList = sequenciamentoRepository.findAll();
        assertThat(sequenciamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
