package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.AvatarStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Avatar.class)
public abstract class Avatar_ {

	public static volatile SetAttribute<Avatar, Report> reports;
	public static volatile SetAttribute<Avatar, Pack> buys;
	public static volatile SingularAttribute<Avatar, Integer> dislike;
	public static volatile SingularAttribute<Avatar, String> description;
	public static volatile SingularAttribute<Avatar, String> categoryName;
	public static volatile SetAttribute<Avatar, Issue> issues;
	public static volatile SingularAttribute<Avatar, Integer> point;
	public static volatile SetAttribute<Avatar, Interact> interacts;
	public static volatile SetAttribute<Avatar, Deal> sales;
	public static volatile SetAttribute<Avatar, Regular> regulars;
	public static volatile SingularAttribute<Avatar, byte[]> logo;
	public static volatile SingularAttribute<Avatar, String> logoContentType;
	public static volatile SingularAttribute<Avatar, Instant> modified;
	public static volatile SingularAttribute<Avatar, Long> id;
	public static volatile SingularAttribute<Avatar, String> text;
	public static volatile SingularAttribute<Avatar, Integer> credit;
	public static volatile SingularAttribute<Avatar, Integer> views;
	public static volatile SingularAttribute<Avatar, String> bannerContentType;
	public static volatile SingularAttribute<Avatar, Integer> comments;
	public static volatile SetAttribute<Avatar, Emotion> emotions;
	public static volatile SingularAttribute<Avatar, Integer> like;
	public static volatile SingularAttribute<Avatar, Instant> created;
	public static volatile SetAttribute<Avatar, Chemistry> chemistries;
	public static volatile SingularAttribute<Avatar, byte[]> banner;
	public static volatile SetAttribute<Avatar, Repute> reputes;
	public static volatile SingularAttribute<Avatar, Integer> grade;
	public static volatile SingularAttribute<Avatar, String> name;
	public static volatile SingularAttribute<Avatar, Category2avatar> category;
	public static volatile SingularAttribute<Avatar, User> user;
	public static volatile SingularAttribute<Avatar, Integer> coin;
	public static volatile SingularAttribute<Avatar, AvatarStatus> status;

	public static final String REPORTS = "reports";
	public static final String BUYS = "buys";
	public static final String DISLIKE = "dislike";
	public static final String DESCRIPTION = "description";
	public static final String CATEGORY_NAME = "categoryName";
	public static final String ISSUES = "issues";
	public static final String POINT = "point";
	public static final String INTERACTS = "interacts";
	public static final String SALES = "sales";
	public static final String REGULARS = "regulars";
	public static final String LOGO = "logo";
	public static final String LOGO_CONTENT_TYPE = "logoContentType";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String CREDIT = "credit";
	public static final String VIEWS = "views";
	public static final String BANNER_CONTENT_TYPE = "bannerContentType";
	public static final String COMMENTS = "comments";
	public static final String EMOTIONS = "emotions";
	public static final String LIKE = "like";
	public static final String CREATED = "created";
	public static final String CHEMISTRIES = "chemistries";
	public static final String BANNER = "banner";
	public static final String REPUTES = "reputes";
	public static final String GRADE = "grade";
	public static final String NAME = "name";
	public static final String CATEGORY = "category";
	public static final String USER = "user";
	public static final String COIN = "coin";
	public static final String STATUS = "status";

}

