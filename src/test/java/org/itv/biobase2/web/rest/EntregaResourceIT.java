package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.Entrega;
import org.itv.biobase2.repository.EntregaRepository;
import org.itv.biobase2.service.EntregaService;

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
 * Integration tests for the {@link EntregaResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntregaResourceIT {

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntregaMockMvc;

    private Entrega entrega;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entrega createEntity(EntityManager em) {
        Entrega entrega = new Entrega()
            .loginName(DEFAULT_LOGIN_NAME)
            .data(DEFAULT_DATA);
        return entrega;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entrega createUpdatedEntity(EntityManager em) {
        Entrega entrega = new Entrega()
            .loginName(UPDATED_LOGIN_NAME)
            .data(UPDATED_DATA);
        return entrega;
    }

    @BeforeEach
    public void initTest() {
        entrega = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntrega() throws Exception {
        int databaseSizeBeforeCreate = entregaRepository.findAll().size();
        // Create the Entrega
        restEntregaMockMvc.perform(post("/api/entregas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrega)))
            .andExpect(status().isCreated());

        // Validate the Entrega in the database
        List<Entrega> entregaList = entregaRepository.findAll();
        assertThat(entregaList).hasSize(databaseSizeBeforeCreate + 1);
        Entrega testEntrega = entregaList.get(entregaList.size() - 1);
        assertThat(testEntrega.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testEntrega.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createEntregaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entregaRepository.findAll().size();

        // Create the Entrega with an existing ID
        entrega.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntregaMockMvc.perform(post("/api/entregas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrega)))
            .andExpect(status().isBadRequest());

        // Validate the Entrega in the database
        List<Entrega> entregaList = entregaRepository.findAll();
        assertThat(entregaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntregas() throws Exception {
        // Initialize the database
        entregaRepository.saveAndFlush(entrega);

        // Get all the entregaList
        restEntregaMockMvc.perform(get("/api/entregas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entrega.getId().intValue())))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getEntrega() throws Exception {
        // Initialize the database
        entregaRepository.saveAndFlush(entrega);

        // Get the entrega
        restEntregaMockMvc.perform(get("/api/entregas/{id}", entrega.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entrega.getId().intValue()))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEntrega() throws Exception {
        // Get the entrega
        restEntregaMockMvc.perform(get("/api/entregas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntrega() throws Exception {
        // Initialize the database
        entregaService.save(entrega);

        int databaseSizeBeforeUpdate = entregaRepository.findAll().size();

        // Update the entrega
        Entrega updatedEntrega = entregaRepository.findById(entrega.getId()).get();
        // Disconnect from session so that the updates on updatedEntrega are not directly saved in db
        em.detach(updatedEntrega);
        updatedEntrega
            .loginName(UPDATED_LOGIN_NAME)
            .data(UPDATED_DATA);

        restEntregaMockMvc.perform(put("/api/entregas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntrega)))
            .andExpect(status().isOk());

        // Validate the Entrega in the database
        List<Entrega> entregaList = entregaRepository.findAll();
        assertThat(entregaList).hasSize(databaseSizeBeforeUpdate);
        Entrega testEntrega = entregaList.get(entregaList.size() - 1);
        assertThat(testEntrega.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testEntrega.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingEntrega() throws Exception {
        int databaseSizeBeforeUpdate = entregaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntregaMockMvc.perform(put("/api/entregas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entrega)))
            .andExpect(status().isBadRequest());

        // Validate the Entrega in the database
        List<Entrega> entregaList = entregaRepository.findAll();
        assertThat(entregaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntrega() throws Exception {
        // Initialize the database
        entregaService.save(entrega);

        int databaseSizeBeforeDelete = entregaRepository.findAll().size();

        // Delete the entrega
        restEntregaMockMvc.perform(delete("/api/entregas/{id}", entrega.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entrega> entregaList = entregaRepository.findAll();
        assertThat(entregaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
