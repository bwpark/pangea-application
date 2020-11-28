package com.nuri.pangea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TOS.
 */
@Entity
@Table(name = "tos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TOS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1024)
    @Column(name = "policy", length = 1024)
    private String policy;

    @Lob
    @Column(name = "text")
    private String text;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public TOS policy(String policy) {
        this.policy = policy;
        return this;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getText() {
        return text;
    }

    public TOS text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TOS)) {
            return false;
        }
        return id != null && id.equals(((TOS) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TOS{" +
            "id=" + getId() +
            ", policy='" + getPolicy() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}
