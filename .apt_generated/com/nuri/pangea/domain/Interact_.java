package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.InteractStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Interact.class)
public abstract class Interact_ {

	public static volatile SingularAttribute<Interact, Interact> parent;
	public static volatile SingularAttribute<Interact, Issue> issue;
	public static volatile SingularAttribute<Interact, Integer> like;
	public static volatile SingularAttribute<Interact, Integer> dislike;
	public static volatile SingularAttribute<Interact, String> author;
	public static volatile SingularAttribute<Interact, Instant> created;
	public static volatile SingularAttribute<Interact, Integer> point;
	public static volatile SetAttribute<Interact, Interact> children;
	public static volatile SingularAttribute<Interact, Avatar> me;
	public static volatile SingularAttribute<Interact, Instant> modified;
	public static volatile SingularAttribute<Interact, Long> id;
	public static volatile SingularAttribute<Interact, String> text;
	public static volatile SingularAttribute<Interact, Avatar> you;
	public static volatile SingularAttribute<Interact, Integer> coin;
	public static volatile SingularAttribute<Interact, InteractStatus> status;

	public static final String PARENT = "parent";
	public static final String ISSUE = "issue";
	public static final String LIKE = "like";
	public static final String DISLIKE = "dislike";
	public static final String AUTHOR = "author";
	public static final String CREATED = "created";
	public static final String POINT = "point";
	public static final String CHILDREN = "children";
	public static final String ME = "me";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String YOU = "you";
	public static final String COIN = "coin";
	public static final String STATUS = "status";

}

