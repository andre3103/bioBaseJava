package org.itv.biobase2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A VoucherInfo.
 */
@Entity
@Table(name = "voucher_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoucherInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "data")
    private LocalDate data;

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

    public VoucherInfo loginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public LocalDate getData() {
        return data;
    }

    public VoucherInfo data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VoucherInfo)) {
            return false;
        }
        return id != null && id.equals(((VoucherInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherInfo{" +
            "id=" + getId() +
            ", loginName='" + getLoginName() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
