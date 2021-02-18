package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TraceFile.
 */
@Entity
@Table(name = "trace_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TraceFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dir_phd")
    private String dirPhd;

    @Column(name = "dir_ab")
    private String dirAb;

    @OneToOne
    @JoinColumn(unique = true)
    private GerenciaId idSample;

    @OneToOne
    @JoinColumn(unique = true)
    private Sequenciamento idSequenciamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirPhd() {
        return dirPhd;
    }

    public TraceFile dirPhd(String dirPhd) {
        this.dirPhd = dirPhd;
        return this;
    }

    public void setDirPhd(String dirPhd) {
        this.dirPhd = dirPhd;
    }

    public String getDirAb() {
        return dirAb;
    }

    public TraceFile dirAb(String dirAb) {
        this.dirAb = dirAb;
        return this;
    }

    public void setDirAb(String dirAb) {
        this.dirAb = dirAb;
    }

    public GerenciaId getIdSample() {
        return idSample;
    }

    public TraceFile idSample(GerenciaId gerenciaId) {
        this.idSample = gerenciaId;
        return this;
    }

    public void setIdSample(GerenciaId gerenciaId) {
        this.idSample = gerenciaId;
    }

    public Sequenciamento getIdSequenciamento() {
        return idSequenciamento;
    }

    public TraceFile idSequenciamento(Sequenciamento sequenciamento) {
        this.idSequenciamento = sequenciamento;
        return this;
    }

    public void setIdSequenciamento(Sequenciamento sequenciamento) {
        this.idSequenciamento = sequenciamento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TraceFile)) {
            return false;
        }
        return id != null && id.equals(((TraceFile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TraceFile{" +
            "id=" + getId() +
            ", dirPhd='" + getDirPhd() + "'" +
            ", dirAb='" + getDirAb() + "'" +
            "}";
    }
}
