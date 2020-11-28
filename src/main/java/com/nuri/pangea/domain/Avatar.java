package com.nuri.pangea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.nuri.pangea.domain.enumeration.AvatarStatus;

/**
 * A Avatar.
 */
@Entity
@Table(name = "avatar")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Avatar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 128)
    @Column(name = "category_name", length = 128)
    private String categoryName;

    @NotNull
    @Size(max = 128)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @Lob
    @Column(name = "text")
    private String text;

    
    @Lob
    @Column(name = "logo", nullable = false)
    private byte[] logo;

    @Column(name = "logo_content_type", nullable = false)
    private String logoContentType;

    
    @Lob
    @Column(name = "banner", nullable = false)
    private byte[] banner;

    @Column(name = "banner_content_type", nullable = false)
    private String bannerContentType;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @NotNull
    @Column(name = "point", nullable = false)
    private Integer point;

    @NotNull
    @Column(name = "jhi_like", nullable = false)
    private Integer like;

    @NotNull
    @Column(name = "dislike", nullable = false)
    private Integer dislike;

    @NotNull
    @Column(name = "grade", nullable = false)
    private Integer grade;

    @NotNull
    @Column(name = "credit", nullable = false)
    private Integer credit;

    @NotNull
    @Column(name = "views", nullable = false)
    private Integer views;

    @NotNull
    @Column(name = "comments", nullable = false)
    private Integer comments;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AvatarStatus status;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Column(name = "modified", nullable = false)
    private Instant modified;

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Issue> issues = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Interact> interacts = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Emotion> emotions = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Repute> reputes = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Regular> regulars = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Report> reports = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Chemistry> chemistries = new HashSet<>();

    @OneToMany(mappedBy = "me")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Pack> buys = new HashSet<>();

    @OneToMany(mappedBy = "to")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Deal> sales = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "avatars", allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "avatars", allowSetters = true)
    private Category2avatar category;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Avatar categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public Avatar name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Avatar description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public Avatar text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Avatar logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Avatar logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public byte[] getBanner() {
        return banner;
    }

    public Avatar banner(byte[] banner) {
        this.banner = banner;
        return this;
    }

    public void setBanner(byte[] banner) {
        this.banner = banner;
    }

    public String getBannerContentType() {
        return bannerContentType;
    }

    public Avatar bannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
        return this;
    }

    public void setBannerContentType(String bannerContentType) {
        this.bannerContentType = bannerContentType;
    }

    public Integer getCoin() {
        return coin;
    }

    public Avatar coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getPoint() {
        return point;
    }

    public Avatar point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLike() {
        return like;
    }

    public Avatar like(Integer like) {
        this.like = like;
        return this;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public Avatar dislike(Integer dislike) {
        this.dislike = dislike;
        return this;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Integer getGrade() {
        return grade;
    }

    public Avatar grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public Avatar credit(Integer credit) {
        this.credit = credit;
        return this;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getViews() {
        return views;
    }

    public Avatar views(Integer views) {
        this.views = views;
        return this;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public Avatar comments(Integer comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public AvatarStatus getStatus() {
        return status;
    }

    public Avatar status(AvatarStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AvatarStatus status) {
        this.status = status;
    }

    public Instant getCreated() {
        return created;
    }

    public Avatar created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public Avatar modified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public Avatar issues(Set<Issue> issues) {
        this.issues = issues;
        return this;
    }

    public Avatar addIssue(Issue issue) {
        this.issues.add(issue);
        issue.setMe(this);
        return this;
    }

    public Avatar removeIssue(Issue issue) {
        this.issues.remove(issue);
        issue.setMe(null);
        return this;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Set<Interact> getInteracts() {
        return interacts;
    }

    public Avatar interacts(Set<Interact> interacts) {
        this.interacts = interacts;
        return this;
    }

    public Avatar addInteract(Interact interact) {
        this.interacts.add(interact);
        interact.setMe(this);
        return this;
    }

    public Avatar removeInteract(Interact interact) {
        this.interacts.remove(interact);
        interact.setMe(null);
        return this;
    }

    public void setInteracts(Set<Interact> interacts) {
        this.interacts = interacts;
    }

    public Set<Emotion> getEmotions() {
        return emotions;
    }

    public Avatar emotions(Set<Emotion> emotions) {
        this.emotions = emotions;
        return this;
    }

    public Avatar addEmotion(Emotion emotion) {
        this.emotions.add(emotion);
        emotion.setMe(this);
        return this;
    }

    public Avatar removeEmotion(Emotion emotion) {
        this.emotions.remove(emotion);
        emotion.setMe(null);
        return this;
    }

    public void setEmotions(Set<Emotion> emotions) {
        this.emotions = emotions;
    }

    public Set<Repute> getReputes() {
        return reputes;
    }

    public Avatar reputes(Set<Repute> reputes) {
        this.reputes = reputes;
        return this;
    }

    public Avatar addRepute(Repute repute) {
        this.reputes.add(repute);
        repute.setMe(this);
        return this;
    }

    public Avatar removeRepute(Repute repute) {
        this.reputes.remove(repute);
        repute.setMe(null);
        return this;
    }

    public void setReputes(Set<Repute> reputes) {
        this.reputes = reputes;
    }

    public Set<Regular> getRegulars() {
        return regulars;
    }

    public Avatar regulars(Set<Regular> regulars) {
        this.regulars = regulars;
        return this;
    }

    public Avatar addRegular(Regular regular) {
        this.regulars.add(regular);
        regular.setMe(this);
        return this;
    }

    public Avatar removeRegular(Regular regular) {
        this.regulars.remove(regular);
        regular.setMe(null);
        return this;
    }

    public void setRegulars(Set<Regular> regulars) {
        this.regulars = regulars;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public Avatar reports(Set<Report> reports) {
        this.reports = reports;
        return this;
    }

    public Avatar addReport(Report report) {
        this.reports.add(report);
        report.setMe(this);
        return this;
    }

    public Avatar removeReport(Report report) {
        this.reports.remove(report);
        report.setMe(null);
        return this;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Chemistry> getChemistries() {
        return chemistries;
    }

    public Avatar chemistries(Set<Chemistry> chemistries) {
        this.chemistries = chemistries;
        return this;
    }

    public Avatar addChemistry(Chemistry chemistry) {
        this.chemistries.add(chemistry);
        chemistry.setMe(this);
        return this;
    }

    public Avatar removeChemistry(Chemistry chemistry) {
        this.chemistries.remove(chemistry);
        chemistry.setMe(null);
        return this;
    }

    public void setChemistries(Set<Chemistry> chemistries) {
        this.chemistries = chemistries;
    }

    public Set<Pack> getBuys() {
        return buys;
    }

    public Avatar buys(Set<Pack> packs) {
        this.buys = packs;
        return this;
    }

    public Avatar addBuy(Pack pack) {
        this.buys.add(pack);
        pack.setMe(this);
        return this;
    }

    public Avatar removeBuy(Pack pack) {
        this.buys.remove(pack);
        pack.setMe(null);
        return this;
    }

    public void setBuys(Set<Pack> packs) {
        this.buys = packs;
    }

    public Set<Deal> getSales() {
        return sales;
    }

    public Avatar sales(Set<Deal> deals) {
        this.sales = deals;
        return this;
    }

    public Avatar addSale(Deal deal) {
        this.sales.add(deal);
        deal.setTo(this);
        return this;
    }

    public Avatar removeSale(Deal deal) {
        this.sales.remove(deal);
        deal.setTo(null);
        return this;
    }

    public void setSales(Set<Deal> deals) {
        this.sales = deals;
    }

    public User getUser() {
        return user;
    }

    public Avatar user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category2avatar getCategory() {
        return category;
    }

    public Avatar category(Category2avatar category2avatar) {
        this.category = category2avatar;
        return this;
    }

    public void setCategory(Category2avatar category2avatar) {
        this.category = category2avatar;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Avatar)) {
            return false;
        }
        return id != null && id.equals(((Avatar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Avatar{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            ", banner='" + getBanner() + "'" +
            ", bannerContentType='" + getBannerContentType() + "'" +
            ", coin=" + getCoin() +
            ", point=" + getPoint() +
            ", like=" + getLike() +
            ", dislike=" + getDislike() +
            ", grade=" + getGrade() +
            ", credit=" + getCredit() +
            ", views=" + getViews() +
            ", comments=" + getComments() +
            ", status='" + getStatus() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            "}";
    }
}
