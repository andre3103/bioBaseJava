package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Placa.
 */
@Entity
@Table(name = "placa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Placa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "data_montagem")
    private LocalDate dataMontagem;

    @Column(name = "marcador")
    private String marcador;

    @Column(name = "sequencia")
    private String sequencia;

    @Column(name = "data")
    private LocalDate data;

    @Max(value = 1)
    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataMontagem() {
        return dataMontagem;
    }

    public Placa dataMontagem(LocalDate dataMontagem) {
        this.dataMontagem = dataMontagem;
        return this;
    }

    public void setDataMontagem(LocalDate dataMontagem) {
        this.dataMontagem = dataMontagem;
    }

    public String getMarcador() {
        return marcador;
    }

    public Placa marcador(String marcador) {
        this.marcador = marcador;
        return this;
    }

    public void setMarcador(String marcador) {
        this.marcador = marcador;
    }

    public String getSequencia() {
        return sequencia;
    }

    public Placa sequencia(String sequencia) {
        this.sequencia = sequencia;
        return this;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public LocalDate getData() {
        return data;
    }

    public Placa data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public Placa status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Placa)) {
            return false;
        }
        return id != null && id.equals(((Placa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Placa{" +
            "id=" + getId() +
            ", dataMontagem='" + getDataMontagem() + "'" +
            ", marcador='" + getMarcador() + "'" +
            ", sequencia='" + getSequencia() + "'" +
            ", data='" + getData() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
