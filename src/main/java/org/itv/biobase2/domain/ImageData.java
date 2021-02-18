package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImageData.
 */
@Entity
@Table(name = "image_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImageData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name_image")
    private String nameImage;

    @Column(name = "image_file")
    private String imageFile;

    @Column(name = "original_specimen")
    private String originalSpecimen;

    @Column(name = "view_metadata")
    private String viewMetadata;

    @Column(name = "caption")
    private String caption;

    @Column(name = "measurement")
    private String measurement;

    @Column(name = "measurement_type")
    private String measurementType;

    @Column(name = "process_id")
    private String processId;

    @Column(name = "license_holder")
    private String licenseHolder;

    @Column(name = "license")
    private String license;

    @Column(name = "license_year")
    private String licenseYear;

    @Column(name = "license_institution")
    private String licenseInstitution;

    @Column(name = "license_contact")
    private String licenseContact;

    @Column(name = "photographer")
    private String photographer;

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

    public String getNameImage() {
        return nameImage;
    }

    public ImageData nameImage(String nameImage) {
        this.nameImage = nameImage;
        return this;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getImageFile() {
        return imageFile;
    }

    public ImageData imageFile(String imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getOriginalSpecimen() {
        return originalSpecimen;
    }

    public ImageData originalSpecimen(String originalSpecimen) {
        this.originalSpecimen = originalSpecimen;
        return this;
    }

    public void setOriginalSpecimen(String originalSpecimen) {
        this.originalSpecimen = originalSpecimen;
    }

    public String getViewMetadata() {
        return viewMetadata;
    }

    public ImageData viewMetadata(String viewMetadata) {
        this.viewMetadata = viewMetadata;
        return this;
    }

    public void setViewMetadata(String viewMetadata) {
        this.viewMetadata = viewMetadata;
    }

    public String getCaption() {
        return caption;
    }

    public ImageData caption(String caption) {
        this.caption = caption;
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMeasurement() {
        return measurement;
    }

    public ImageData measurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public ImageData measurementType(String measurementType) {
        this.measurementType = measurementType;
        return this;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getProcessId() {
        return processId;
    }

    public ImageData processId(String processId) {
        this.processId = processId;
        return this;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getLicenseHolder() {
        return licenseHolder;
    }

    public ImageData licenseHolder(String licenseHolder) {
        this.licenseHolder = licenseHolder;
        return this;
    }

    public void setLicenseHolder(String licenseHolder) {
        this.licenseHolder = licenseHolder;
    }

    public String getLicense() {
        return license;
    }

    public ImageData license(String license) {
        this.license = license;
        return this;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseYear() {
        return licenseYear;
    }

    public ImageData licenseYear(String licenseYear) {
        this.licenseYear = licenseYear;
        return this;
    }

    public void setLicenseYear(String licenseYear) {
        this.licenseYear = licenseYear;
    }

    public String getLicenseInstitution() {
        return licenseInstitution;
    }

    public ImageData licenseInstitution(String licenseInstitution) {
        this.licenseInstitution = licenseInstitution;
        return this;
    }

    public void setLicenseInstitution(String licenseInstitution) {
        this.licenseInstitution = licenseInstitution;
    }

    public String getLicenseContact() {
        return licenseContact;
    }

    public ImageData licenseContact(String licenseContact) {
        this.licenseContact = licenseContact;
        return this;
    }

    public void setLicenseContact(String licenseContact) {
        this.licenseContact = licenseContact;
    }

    public String getPhotographer() {
        return photographer;
    }

    public ImageData photographer(String photographer) {
        this.photographer = photographer;
        return this;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public GerenciaId getIdSample() {
        return idSample;
    }

    public ImageData idSample(GerenciaId gerenciaId) {
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
        if (!(o instanceof ImageData)) {
            return false;
        }
        return id != null && id.equals(((ImageData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageData{" +
            "id=" + getId() +
            ", nameImage='" + getNameImage() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", originalSpecimen='" + getOriginalSpecimen() + "'" +
            ", viewMetadata='" + getViewMetadata() + "'" +
            ", caption='" + getCaption() + "'" +
            ", measurement='" + getMeasurement() + "'" +
            ", measurementType='" + getMeasurementType() + "'" +
            ", processId='" + getProcessId() + "'" +
            ", licenseHolder='" + getLicenseHolder() + "'" +
            ", license='" + getLicense() + "'" +
            ", licenseYear='" + getLicenseYear() + "'" +
            ", licenseInstitution='" + getLicenseInstitution() + "'" +
            ", licenseContact='" + getLicenseContact() + "'" +
            ", photographer='" + getPhotographer() + "'" +
            "}";
    }
}
