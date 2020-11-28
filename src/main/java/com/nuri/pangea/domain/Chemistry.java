package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Chemistry.
 */
@Entity
@Table(name = "chemistry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chemistry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 128)
    @Column(name = "your_name", length = 128, nullable = false)
    private String yourName;

    @NotNull
    @Column(name = "to_you", nullable = false)
    private Integer toYou;

    @NotNull
    @Column(name = "to_me", nullable = false)
    private Integer toMe;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @ManyToOne
    @JsonIgnoreProperties(value = "chemistries", allowSetters = true)
    private Avatar you;

    @ManyToOne
    @JsonIgnoreProperties(value = "chemistries", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYourName() {
        return yourName;
    }

    public Chemistry yourName(String yourName) {
        this.yourName = yourName;
        return this;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public Integer getToYou() {
        return toYou;
    }

    public Chemistry toYou(Integer toYou) {
        this.toYou = toYou;
        return this;
    }

    public void setToYou(Integer toYou) {
        this.toYou = toYou;
    }

    public Integer getToMe() {
        return toMe;
    }

    public Chemistry toMe(Integer toMe) {
        this.toMe = toMe;
        return this;
    }

    public void setToMe(Integer toMe) {
        this.toMe = toMe;
    }

    public Instant getCreated() {
        return created;
    }

    public Chemistry created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Avatar getYou() {
        return you;
    }

    public Chemistry you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Avatar getMe() {
        return me;
    }

    public Chemistry me(Avatar avatar) {
        this.me = avatar;
        return this;
    }

    public void setMe(Avatar avatar) {
        this.me = avatar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Chemistry)) {
            return false;
        }
        return id != null && id.equals(((Chemistry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Chemistry{" +
            "id=" + getId() +
            ", yourName='" + getYourName() + "'" +
            ", toYou=" + getToYou() +
            ", toMe=" + getToMe() +
            ", created='" + getCreated() + "'" +
            "}";
    }
}
