package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.EmotionStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Emotion.class)
public abstract class Emotion_ {

	public static volatile SingularAttribute<Emotion, Issue> issue;
	public static volatile SingularAttribute<Emotion, Instant> created;
	public static volatile SingularAttribute<Emotion, Avatar> me;
	public static volatile SingularAttribute<Emotion, Instant> modified;
	public static volatile SingularAttribute<Emotion, Long> id;
	public static volatile SingularAttribute<Emotion, Avatar> you;
	public static volatile SingularAttribute<Emotion, EmotionStatus> status;

	public static final String ISSUE = "issue";
	public static final String CREATED = "created";
	public static final String ME = "me";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String YOU = "you";
	public static final String STATUS = "status";

}

