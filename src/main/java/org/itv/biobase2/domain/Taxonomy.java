package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Taxonomy.
 */
@Entity
@Table(name = "taxonomy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Taxonomy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "phylum")
    private String phylum;

    @Column(name = "tclass")
    private String tclass;

    @Column(name = "torder")
    private String torder;

    @Column(name = "family")
    private String family;

    @Column(name = "subfamily")
    private String subfamily;

    @Column(name = "genus")
    private String genus;

    @Column(name = "specie")
    private String specie;

    @Column(name = "identif")
    private String identif;

    @Column(name = "identifier_email")
    private String identifierEmail;

    @Column(name = "identifier_institution")
    private String identifierInstitution;

    @Column(name = "identification_method")
    private String identificationMethod;

    @Column(name = "taxonomy_notes")
    private String taxonomyNotes;

    @OneToOne
    @JoinColumn(unique = true)
    private VoucherInfo voucherInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhylum() {
        return phylum;
    }

    public Taxonomy phylum(String phylum) {
        this.phylum = phylum;
        return this;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getTclass() {
        return tclass;
    }

    public Taxonomy tclass(String tclass) {
        this.tclass = tclass;
        return this;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public String getTorder() {
        return torder;
    }

    public Taxonomy torder(String torder) {
        this.torder = torder;
        return this;
    }

    public void setTorder(String torder) {
        this.torder = torder;
    }

    public String getFamily() {
        return family;
    }

    public Taxonomy family(String family) {
        this.family = family;
        return this;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSubfamily() {
        return subfamily;
    }

    public Taxonomy subfamily(String subfamily) {
        this.subfamily = subfamily;
        return this;
    }

    public void setSubfamily(String subfamily) {
        this.subfamily = subfamily;
    }

    public String getGenus() {
        return genus;
    }

    public Taxonomy genus(String genus) {
        this.genus = genus;
        return this;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecie() {
        return specie;
    }

    public Taxonomy specie(String specie) {
        this.specie = specie;
        return this;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getIdentif() {
        return identif;
    }

    public Taxonomy identif(String identif) {
        this.identif = identif;
        return this;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public String getIdentifierEmail() {
        return identifierEmail;
    }

    public Taxonomy identifierEmail(String identifierEmail) {
        this.identifierEmail = identifierEmail;
        return this;
    }

    public void setIdentifierEmail(String identifierEmail) {
        this.identifierEmail = identifierEmail;
    }

    public String getIdentifierInstitution() {
        return identifierInstitution;
    }

    public Taxonomy identifierInstitution(String identifierInstitution) {
        this.identifierInstitution = identifierInstitution;
        return this;
    }

    public void setIdentifierInstitution(String identifierInstitution) {
        this.identifierInstitution = identifierInstitution;
    }

    public String getIdentificationMethod() {
        return identificationMethod;
    }

    public Taxonomy identificationMethod(String identificationMethod) {
        this.identificationMethod = identificationMethod;
        return this;
    }

    public void setIdentificationMethod(String identificationMethod) {
        this.identificationMethod = identificationMethod;
    }

    public String getTaxonomyNotes() {
        return taxonomyNotes;
    }

    public Taxonomy taxonomyNotes(String taxonomyNotes) {
        this.taxonomyNotes = taxonomyNotes;
        return this;
    }

    public void setTaxonomyNotes(String taxonomyNotes) {
        this.taxonomyNotes = taxonomyNotes;
    }

    public VoucherInfo getVoucherInfo() {
        return voucherInfo;
    }

    public Taxonomy voucherInfo(VoucherInfo voucherInfo) {
        this.voucherInfo = voucherInfo;
        return this;
    }

    public void setVoucherInfo(VoucherInfo voucherInfo) {
        this.voucherInfo = voucherInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Taxonomy)) {
            return false;
        }
        return id != null && id.equals(((Taxonomy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Taxonomy{" +
            "id=" + getId() +
            ", phylum='" + getPhylum() + "'" +
            ", tclass='" + getTclass() + "'" +
            ", torder='" + getTorder() + "'" +
            ", family='" + getFamily() + "'" +
            ", subfamily='" + getSubfamily() + "'" +
            ", genus='" + getGenus() + "'" +
            ", specie='" + getSpecie() + "'" +
            ", identif='" + getIdentif() + "'" +
            ", identifierEmail='" + getIdentifierEmail() + "'" +
            ", identifierInstitution='" + getIdentifierInstitution() + "'" +
            ", identificationMethod='" + getIdentificationMethod() + "'" +
            ", taxonomyNotes='" + getTaxonomyNotes() + "'" +
            "}";
    }
}
