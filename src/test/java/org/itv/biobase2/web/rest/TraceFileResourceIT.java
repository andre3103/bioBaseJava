package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.TraceFile;
import org.itv.biobase2.repository.TraceFileRepository;
import org.itv.biobase2.service.TraceFileService;

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
 * Integration tests for the {@link TraceFileResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TraceFileResourceIT {

    private static final String DEFAULT_DIR_PHD = "AAAAAAAAAA";
    private static final String UPDATED_DIR_PHD = "BBBBBBBBBB";

    private static final String DEFAULT_DIR_AB = "AAAAAAAAAA";
    private static final String UPDATED_DIR_AB = "BBBBBBBBBB";

    @Autowired
    private TraceFileRepository traceFileRepository;

    @Autowired
    private TraceFileService traceFileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTraceFileMockMvc;

    private TraceFile traceFile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TraceFile createEntity(EntityManager em) {
        TraceFile traceFile = new TraceFile()
            .dirPhd(DEFAULT_DIR_PHD)
            .dirAb(DEFAULT_DIR_AB);
        return traceFile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TraceFile createUpdatedEntity(EntityManager em) {
        TraceFile traceFile = new TraceFile()
            .dirPhd(UPDATED_DIR_PHD)
            .dirAb(UPDATED_DIR_AB);
        return traceFile;
    }

    @BeforeEach
    public void initTest() {
        traceFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createTraceFile() throws Exception {
        int databaseSizeBeforeCreate = traceFileRepository.findAll().size();
        // Create the TraceFile
        restTraceFileMockMvc.perform(post("/api/trace-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traceFile)))
            .andExpect(status().isCreated());

        // Validate the TraceFile in the database
        List<TraceFile> traceFileList = traceFileRepository.findAll();
        assertThat(traceFileList).hasSize(databaseSizeBeforeCreate + 1);
        TraceFile testTraceFile = traceFileList.get(traceFileList.size() - 1);
        assertThat(testTraceFile.getDirPhd()).isEqualTo(DEFAULT_DIR_PHD);
        assertThat(testTraceFile.getDirAb()).isEqualTo(DEFAULT_DIR_AB);
    }

    @Test
    @Transactional
    public void createTraceFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = traceFileRepository.findAll().size();

        // Create the TraceFile with an existing ID
        traceFile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraceFileMockMvc.perform(post("/api/trace-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traceFile)))
            .andExpect(status().isBadRequest());

        // Validate the TraceFile in the database
        List<TraceFile> traceFileList = traceFileRepository.findAll();
        assertThat(traceFileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTraceFiles() throws Exception {
        // Initialize the database
        traceFileRepository.saveAndFlush(traceFile);

        // Get all the traceFileList
        restTraceFileMockMvc.perform(get("/api/trace-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(traceFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].dirPhd").value(hasItem(DEFAULT_DIR_PHD)))
            .andExpect(jsonPath("$.[*].dirAb").value(hasItem(DEFAULT_DIR_AB)));
    }
    
    @Test
    @Transactional
    public void getTraceFile() throws Exception {
        // Initialize the database
        traceFileRepository.saveAndFlush(traceFile);

        // Get the traceFile
        restTraceFileMockMvc.perform(get("/api/trace-files/{id}", traceFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(traceFile.getId().intValue()))
            .andExpect(jsonPath("$.dirPhd").value(DEFAULT_DIR_PHD))
            .andExpect(jsonPath("$.dirAb").value(DEFAULT_DIR_AB));
    }
    @Test
    @Transactional
    public void getNonExistingTraceFile() throws Exception {
        // Get the traceFile
        restTraceFileMockMvc.perform(get("/api/trace-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTraceFile() throws Exception {
        // Initialize the database
        traceFileService.save(traceFile);

        int databaseSizeBeforeUpdate = traceFileRepository.findAll().size();

        // Update the traceFile
        TraceFile updatedTraceFile = traceFileRepository.findById(traceFile.getId()).get();
        // Disconnect from session so that the updates on updatedTraceFile are not directly saved in db
        em.detach(updatedTraceFile);
        updatedTraceFile
            .dirPhd(UPDATED_DIR_PHD)
            .dirAb(UPDATED_DIR_AB);

        restTraceFileMockMvc.perform(put("/api/trace-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTraceFile)))
            .andExpect(status().isOk());

        // Validate the TraceFile in the database
        List<TraceFile> traceFileList = traceFileRepository.findAll();
        assertThat(traceFileList).hasSize(databaseSizeBeforeUpdate);
        TraceFile testTraceFile = traceFileList.get(traceFileList.size() - 1);
        assertThat(testTraceFile.getDirPhd()).isEqualTo(UPDATED_DIR_PHD);
        assertThat(testTraceFile.getDirAb()).isEqualTo(UPDATED_DIR_AB);
    }

    @Test
    @Transactional
    public void updateNonExistingTraceFile() throws Exception {
        int databaseSizeBeforeUpdate = traceFileRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraceFileMockMvc.perform(put("/api/trace-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traceFile)))
            .andExpect(status().isBadRequest());

        // Validate the TraceFile in the database
        List<TraceFile> traceFileList = traceFileRepository.findAll();
        assertThat(traceFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTraceFile() throws Exception {
        // Initialize the database
        traceFileService.save(traceFile);

        int databaseSizeBeforeDelete = traceFileRepository.findAll().size();

        // Delete the traceFile
        restTraceFileMockMvc.perform(delete("/api/trace-files/{id}", traceFile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TraceFile> traceFileList = traceFileRepository.findAll();
        assertThat(traceFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
