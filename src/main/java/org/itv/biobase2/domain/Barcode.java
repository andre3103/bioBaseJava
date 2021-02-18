package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Barcode.
 */
@Entity
@Table(name = "barcode")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Barcode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "marcador")
    private String marcador;

    @Column(name = "sequencia")
    private String sequencia;

    @Column(name = "data")
    private LocalDate data;

    @OneToOne
    @JoinColumn(unique = true)
    private GerenciaId idSample;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarcador() {
        return marcador;
    }

    public Barcode marcador(String marcador) {
        this.marcador = marcador;
        return this;
    }

    public void setMarcador(String marcador) {
        this.marcador = marcador;
    }

    public String getSequencia() {
        return sequencia;
    }

    public Barcode sequencia(String sequencia) {
        this.sequencia = sequencia;
        return this;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public LocalDate getData() {
        return data;
    }

    public Barcode data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public GerenciaId getIdSample() {
        return idSample;
    }

    public Barcode idSample(GerenciaId gerenciaId) {
        this.idSample = gerenciaId;
        return this;
    }

    public void setIdSample(GerenciaId gerenciaId) {
        this.idSample = gerenciaId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Barcode)) {
            return false;
        }
        return id != null && id.equals(((Barcode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Barcode{" +
            "id=" + getId() +
            ", marcador='" + getMarcador() + "'" +
            ", sequencia='" + getSequencia() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
