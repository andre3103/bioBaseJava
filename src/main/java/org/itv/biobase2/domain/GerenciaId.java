package org.itv.biobase2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GerenciaId.
 */
@Entity
@Table(name = "gerencia_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GerenciaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "data_geracao")
    private Instant dataGeracao;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(unique = true)
    private VoucherInfo voucherInfo;

    @ManyToOne
    @JsonIgnoreProperties(value = "gerenciaIds", allowSetters = true)
    private Entrega entrega;

    @ManyToOne
    @JsonIgnoreProperties(value = "gerenciaIds", allowSetters = true)
    private Recebimento recebimento;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public GerenciaId loginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Instant getDataGeracao() {
        return dataGeracao;
    }

    public GerenciaId dataGeracao(Instant dataGeracao) {
        this.dataGeracao = dataGeracao;
        return this;
    }

    public void setDataGeracao(Instant dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getStatus() {
        return status;
    }

    public GerenciaId status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VoucherInfo getVoucherInfo() {
        return voucherInfo;
    }

    public GerenciaId voucherInfo(VoucherInfo voucherInfo) {
        this.voucherInfo = voucherInfo;
        return this;
    }

    public void setVoucherInfo(VoucherInfo voucherInfo) {
        this.voucherInfo = voucherInfo;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public GerenciaId entrega(Entrega entrega) {
        this.entrega = entrega;
        return this;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public GerenciaId recebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
        return this;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GerenciaId)) {
            return false;
        }
        return id != null && id.equals(((GerenciaId) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GerenciaId{" +
            "id=" + getId() +
            ", loginName='" + getLoginName() + "'" +
            ", dataGeracao='" + getDataGeracao() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
