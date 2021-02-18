package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CollectionData.
 */
@Entity
@Table(name = "collection_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CollectionData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "collector")
    private String collector;

    @Column(name = "collection_date")
    private LocalDate collectionDate;

    @Column(name = "country_ocean")
    private String countryOcean;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "region")
    private String region;

    @Column(name = "sector")
    private String sector;

    @Column(name = "exact_site")
    private String exactSite;

    @Column(name = "identif")
    private String identif;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "elevation")
    private String elevation;

    @Column(name = "depth")
    private String depth;

    @Column(name = "elevation_precision")
    private String elevationPrecision;

    @Column(name = "depth_precision")
    private String depthPrecision;

    @Column(name = "gps_source")
    private String gpsSource;

    @Column(name = "coordinate_accuracy")
    private String coordinateAccuracy;

    @Column(name = "event_time")
    private String eventTime;

    @Column(name = "collection_date_occuracy")
    private String collectionDateOccuracy;

    @Column(name = "habitat")
    private String habitat;

    @Column(name = "sampling_protocol")
    private String samplingProtocol;

    @Column(name = "collection_notes")
    private String collectionNotes;

    @Column(name = "site_code")
    private String siteCode;

    @Column(name = "collection_event_id")
    private String collectionEventId;

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

    public String getCollector() {
        return collector;
    }

    public CollectionData collector(String collector) {
        this.collector = collector;
        return this;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public CollectionData collectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
        return this;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getCountryOcean() {
        return countryOcean;
    }

    public CollectionData countryOcean(String countryOcean) {
        this.countryOcean = countryOcean;
        return this;
    }

    public void setCountryOcean(String countryOcean) {
        this.countryOcean = countryOcean;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public CollectionData stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getRegion() {
        return region;
    }

    public CollectionData region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSector() {
        return sector;
    }

    public CollectionData sector(String sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getExactSite() {
        return exactSite;
    }

    public CollectionData exactSite(String exactSite) {
        this.exactSite = exactSite;
        return this;
    }

    public void setExactSite(String exactSite) {
        this.exactSite = exactSite;
    }

    public String getIdentif() {
        return identif;
    }

    public CollectionData identif(String identif) {
        this.identif = identif;
        return this;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public String getLatitude() {
        return latitude;
    }

    public CollectionData latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public CollectionData longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public CollectionData elevation(String elevation) {
        this.elevation = elevation;
        return this;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getDepth() {
        return depth;
    }

    public CollectionData depth(String depth) {
        this.depth = depth;
        return this;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getElevationPrecision() {
        return elevationPrecision;
    }

    public CollectionData elevationPrecision(String elevationPrecision) {
        this.elevationPrecision = elevationPrecision;
        return this;
    }

    public void setElevationPrecision(String elevationPrecision) {
        this.elevationPrecision = elevationPrecision;
    }

    public String getDepthPrecision() {
        return depthPrecision;
    }

    public CollectionData depthPrecision(String depthPrecision) {
        this.depthPrecision = depthPrecision;
        return this;
    }

    public void setDepthPrecision(String depthPrecision) {
        this.depthPrecision = depthPrecision;
    }

    public String getGpsSource() {
        return gpsSource;
    }

    public CollectionData gpsSource(String gpsSource) {
        this.gpsSource = gpsSource;
        return this;
    }

    public void setGpsSource(String gpsSource) {
        this.gpsSource = gpsSource;
    }

    public String getCoordinateAccuracy() {
        return coordinateAccuracy;
    }

    public CollectionData coordinateAccuracy(String coordinateAccuracy) {
        this.coordinateAccuracy = coordinateAccuracy;
        return this;
    }

    public void setCoordinateAccuracy(String coordinateAccuracy) {
        this.coordinateAccuracy = coordinateAccuracy;
    }

    public String getEventTime() {
        return eventTime;
    }

    public CollectionData eventTime(String eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getCollectionDateOccuracy() {
        return collectionDateOccuracy;
    }

    public CollectionData collectionDateOccuracy(String collectionDateOccuracy) {
        this.collectionDateOccuracy = collectionDateOccuracy;
        return this;
    }

    public void setCollectionDateOccuracy(String collectionDateOccuracy) {
        this.collectionDateOccuracy = collectionDateOccuracy;
    }

    public String getHabitat() {
        return habitat;
    }

    public CollectionData habitat(String habitat) {
        this.habitat = habitat;
        return this;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getSamplingProtocol() {
        return samplingProtocol;
    }

    public CollectionData samplingProtocol(String samplingProtocol) {
        this.samplingProtocol = samplingProtocol;
        return this;
    }

    public void setSamplingProtocol(String samplingProtocol) {
        this.samplingProtocol = samplingProtocol;
    }

    public String getCollectionNotes() {
        return collectionNotes;
    }

    public CollectionData collectionNotes(String collectionNotes) {
        this.collectionNotes = collectionNotes;
        return this;
    }

    public void setCollectionNotes(String collectionNotes) {
        this.collectionNotes = collectionNotes;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public CollectionData siteCode(String siteCode) {
        this.siteCode = siteCode;
        return this;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getCollectionEventId() {
        return collectionEventId;
    }

    public CollectionData collectionEventId(String collectionEventId) {
        this.collectionEventId = collectionEventId;
        return this;
    }

    public void setCollectionEventId(String collectionEventId) {
        this.collectionEventId = collectionEventId;
    }

    public VoucherInfo getVoucherInfo() {
        return voucherInfo;
    }

    public CollectionData voucherInfo(VoucherInfo voucherInfo) {
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
        if (!(o instanceof CollectionData)) {
            return false;
        }
        return id != null && id.equals(((CollectionData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CollectionData{" +
            "id=" + getId() +
            ", collector='" + getCollector() + "'" +
            ", collectionDate='" + getCollectionDate() + "'" +
            ", countryOcean='" + getCountryOcean() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", region='" + getRegion() + "'" +
            ", sector='" + getSector() + "'" +
            ", exactSite='" + getExactSite() + "'" +
            ", identif='" + getIdentif() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", elevation='" + getElevation() + "'" +
            ", depth='" + getDepth() + "'" +
            ", elevationPrecision='" + getElevationPrecision() + "'" +
            ", depthPrecision='" + getDepthPrecision() + "'" +
            ", gpsSource='" + getGpsSource() + "'" +
            ", coordinateAccuracy='" + getCoordinateAccuracy() + "'" +
            ", eventTime='" + getEventTime() + "'" +
            ", collectionDateOccuracy='" + getCollectionDateOccuracy() + "'" +
            ", habitat='" + getHabitat() + "'" +
            ", samplingProtocol='" + getSamplingProtocol() + "'" +
            ", collectionNotes='" + getCollectionNotes() + "'" +
            ", siteCode='" + getSiteCode() + "'" +
            ", collectionEventId='" + getCollectionEventId() + "'" +
            "}";
    }
}
