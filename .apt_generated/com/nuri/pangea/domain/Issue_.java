package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.IssueStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Issue.class)
public abstract class Issue_ {

	public static volatile SingularAttribute<Issue, Integer> comments;
	public static volatile SetAttribute<Issue, Emotion> emotions;
	public static volatile SingularAttribute<Issue, Integer> like;
	public static volatile SingularAttribute<Issue, Integer> dislike;
	public static volatile SingularAttribute<Issue, String> author;
	public static volatile SingularAttribute<Issue, Instant> created;
	public static volatile SingularAttribute<Issue, String> description;
	public static volatile SetAttribute<Issue, IssueResource> resources;
	public static volatile SingularAttribute<Issue, String> categoryName;
	public static volatile SingularAttribute<Issue, Integer> point;
	public static volatile SetAttribute<Issue, Interact> interacts;
	public static volatile SingularAttribute<Issue, String> name;
	public static volatile SetAttribute<Issue, IssueOption> options;
	public static volatile SingularAttribute<Issue, Avatar> me;
	public static volatile SingularAttribute<Issue, Instant> modified;
	public static volatile SingularAttribute<Issue, Long> id;
	public static volatile SingularAttribute<Issue, String> text;
	public static volatile SingularAttribute<Issue, Integer> views;
	public static volatile SingularAttribute<Issue, Integer> coin;
	public static volatile SingularAttribute<Issue, IssueStatus> status;

	public static final String COMMENTS = "comments";
	public static final String EMOTIONS = "emotions";
	public static final String LIKE = "like";
	public static final String DISLIKE = "dislike";
	public static final String AUTHOR = "author";
	public static final String CREATED = "created";
	public static final String DESCRIPTION = "description";
	public static final String RESOURCES = "resources";
	public static final String CATEGORY_NAME = "categoryName";
	public static final String POINT = "point";
	public static final String INTERACTS = "interacts";
	public static final String NAME = "name";
	public static final String OPTIONS = "options";
	public static final String ME = "me";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String VIEWS = "views";
	public static final String COIN = "coin";
	public static final String STATUS = "status";

}

