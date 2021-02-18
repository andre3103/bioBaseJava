package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Sequenciamento.
 */
@Entity
@Table(name = "sequenciamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sequenciamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "posiao")
    private String posiao;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(unique = true)
    private GerenciaId idSample;

    @OneToOne
    @JoinColumn(unique = true)
    private Placa idPlaca;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosiao() {
        return posiao;
    }

    public Sequenciamento posiao(String posiao) {
        this.posiao = posiao;
        return this;
    }

    public void setPosiao(String posiao) {
        this.posiao = posiao;
    }

    public Integer getStatus() {
        return status;
    }

    public Sequenciamento status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GerenciaId getIdSample() {
        return idSample;
    }

    public Sequenciamento idSample(GerenciaId gerenciaId) {
        this.idSample = gerenciaId;
        return this;
    }

    public void setIdSample(GerenciaId gerenciaId) {
        this.idSample = gerenciaId;
    }

    public Placa getIdPlaca() {
        return idPlaca;
    }

    public Sequenciamento idPlaca(Placa placa) {
        this.idPlaca = placa;
        return this;
    }

    public void setIdPlaca(Placa placa) {
        this.idPlaca = placa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sequenciamento)) {
            return false;
        }
        return id != null && id.equals(((Sequenciamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sequenciamento{" +
            "id=" + getId() +
            ", posiao='" + getPosiao() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
