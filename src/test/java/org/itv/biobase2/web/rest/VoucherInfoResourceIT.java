package org.itv.biobase2.web.rest;

import org.itv.biobase2.BioBaseApplicationApp;
import org.itv.biobase2.domain.VoucherInfo;
import org.itv.biobase2.repository.VoucherInfoRepository;
import org.itv.biobase2.service.VoucherInfoService;

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
 * Integration tests for the {@link VoucherInfoResource} REST controller.
 */
@SpringBootTest(classes = BioBaseApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VoucherInfoResourceIT {

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private VoucherInfoRepository voucherInfoRepository;

    @Autowired
    private VoucherInfoService voucherInfoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVoucherInfoMockMvc;

    private VoucherInfo voucherInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VoucherInfo createEntity(EntityManager em) {
        VoucherInfo voucherInfo = new VoucherInfo()
            .loginName(DEFAULT_LOGIN_NAME)
            .data(DEFAULT_DATA);
        return voucherInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VoucherInfo createUpdatedEntity(EntityManager em) {
        VoucherInfo voucherInfo = new VoucherInfo()
            .loginName(UPDATED_LOGIN_NAME)
            .data(UPDATED_DATA);
        return voucherInfo;
    }

    @BeforeEach
    public void initTest() {
        voucherInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoucherInfo() throws Exception {
        int databaseSizeBeforeCreate = voucherInfoRepository.findAll().size();
        // Create the VoucherInfo
        restVoucherInfoMockMvc.perform(post("/api/voucher-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherInfo)))
            .andExpect(status().isCreated());

        // Validate the VoucherInfo in the database
        List<VoucherInfo> voucherInfoList = voucherInfoRepository.findAll();
        assertThat(voucherInfoList).hasSize(databaseSizeBeforeCreate + 1);
        VoucherInfo testVoucherInfo = voucherInfoList.get(voucherInfoList.size() - 1);
        assertThat(testVoucherInfo.getLoginName()).isEqualTo(DEFAULT_LOGIN_NAME);
        assertThat(testVoucherInfo.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createVoucherInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voucherInfoRepository.findAll().size();

        // Create the VoucherInfo with an existing ID
        voucherInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoucherInfoMockMvc.perform(post("/api/voucher-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherInfo)))
            .andExpect(status().isBadRequest());

        // Validate the VoucherInfo in the database
        List<VoucherInfo> voucherInfoList = voucherInfoRepository.findAll();
        assertThat(voucherInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVoucherInfos() throws Exception {
        // Initialize the database
        voucherInfoRepository.saveAndFlush(voucherInfo);

        // Get all the voucherInfoList
        restVoucherInfoMockMvc.perform(get("/api/voucher-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voucherInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getVoucherInfo() throws Exception {
        // Initialize the database
        voucherInfoRepository.saveAndFlush(voucherInfo);

        // Get the voucherInfo
        restVoucherInfoMockMvc.perform(get("/api/voucher-infos/{id}", voucherInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(voucherInfo.getId().intValue()))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVoucherInfo() throws Exception {
        // Get the voucherInfo
        restVoucherInfoMockMvc.perform(get("/api/voucher-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoucherInfo() throws Exception {
        // Initialize the database
        voucherInfoService.save(voucherInfo);

        int databaseSizeBeforeUpdate = voucherInfoRepository.findAll().size();

        // Update the voucherInfo
        VoucherInfo updatedVoucherInfo = voucherInfoRepository.findById(voucherInfo.getId()).get();
        // Disconnect from session so that the updates on updatedVoucherInfo are not directly saved in db
        em.detach(updatedVoucherInfo);
        updatedVoucherInfo
            .loginName(UPDATED_LOGIN_NAME)
            .data(UPDATED_DATA);

        restVoucherInfoMockMvc.perform(put("/api/voucher-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVoucherInfo)))
            .andExpect(status().isOk());

        // Validate the VoucherInfo in the database
        List<VoucherInfo> voucherInfoList = voucherInfoRepository.findAll();
        assertThat(voucherInfoList).hasSize(databaseSizeBeforeUpdate);
        VoucherInfo testVoucherInfo = voucherInfoList.get(voucherInfoList.size() - 1);
        assertThat(testVoucherInfo.getLoginName()).isEqualTo(UPDATED_LOGIN_NAME);
        assertThat(testVoucherInfo.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingVoucherInfo() throws Exception {
        int databaseSizeBeforeUpdate = voucherInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVoucherInfoMockMvc.perform(put("/api/voucher-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherInfo)))
            .andExpect(status().isBadRequest());

        // Validate the VoucherInfo in the database
        List<VoucherInfo> voucherInfoList = voucherInfoRepository.findAll();
        assertThat(voucherInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoucherInfo() throws Exception {
        // Initialize the database
        voucherInfoService.save(voucherInfo);

        int databaseSizeBeforeDelete = voucherInfoRepository.findAll().size();

        // Delete the voucherInfo
        restVoucherInfoMockMvc.perform(delete("/api/voucher-infos/{id}", voucherInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VoucherInfo> voucherInfoList = voucherInfoRepository.findAll();
        assertThat(voucherInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
