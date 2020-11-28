package com.nuri.pangea.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Chemistry.class)
public abstract class Chemistry_ {

	public static volatile SingularAttribute<Chemistry, Integer> toYou;
	public static volatile SingularAttribute<Chemistry, Integer> toMe;
	public static volatile SingularAttribute<Chemistry, Instant> created;
	public static volatile SingularAttribute<Chemistry, Avatar> me;
	public static volatile SingularAttribute<Chemistry, String> yourName;
	public static volatile SingularAttribute<Chemistry, Long> id;
	public static volatile SingularAttribute<Chemistry, Avatar> you;

	public static final String TO_YOU = "toYou";
	public static final String TO_ME = "toMe";
	public static final String CREATED = "created";
	public static final String ME = "me";
	public static final String YOUR_NAME = "yourName";
	public static final String ID = "id";
	public static final String YOU = "you";

}

