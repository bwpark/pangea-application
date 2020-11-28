package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nuri.pangea.domain.enumeration.EmotionStatus;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Emotion.
 */
@Entity
@Table(name = "emotion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Emotion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmotionStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @ManyToOne
    @JsonIgnoreProperties(value = "emotions", allowSetters = true)
    private Avatar you;

    @ManyToOne
    @JsonIgnoreProperties(value = "emotions", allowSetters = true)
    private Issue issue;

    @ManyToOne
    @JsonIgnoreProperties(value = "emotions", allowSetters = true)
    private Avatar me;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmotionStatus getStatus() {
        return status;
    }

    public Emotion status(EmotionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(EmotionStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Emotion created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Emotion modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Avatar getYou() {
        return you;
    }

    public Emotion you(Avatar avatar) {
        this.you = avatar;
        return this;
    }

    public void setYou(Avatar avatar) {
        this.you = avatar;
    }

    public Issue getIssue() {
        return issue;
    }

    public Emotion issue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Avatar getMe() {
        return me;
    }

    public Emotion me(Avatar avatar) {
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
        if (!(o instanceof Emotion)) {
            return false;
        }
        return id != null && id.equals(((Emotion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emotion{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }

    @PrePersist
    public void prePersist() {
        setCreated(Instant.now());
        setModified(Instant.now());
    }

    @PostUpdate
    public void PreUpdate() {
        setModified(Instant.now());
    }
}
