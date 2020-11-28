package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.ReputeStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Repute.class)
public abstract class Repute_ {

	public static volatile SingularAttribute<Repute, Instant> created;
	public static volatile SingularAttribute<Repute, Integer> grade;
	public static volatile SingularAttribute<Repute, Avatar> me;
	public static volatile SingularAttribute<Repute, String> description;
	public static volatile SingularAttribute<Repute, Instant> modified;
	public static volatile SingularAttribute<Repute, Long> id;
	public static volatile SingularAttribute<Repute, Integer> credit;
	public static volatile SingularAttribute<Repute, Avatar> you;
	public static volatile SingularAttribute<Repute, ReputeStatus> status;

	public static final String CREATED = "created";
	public static final String GRADE = "grade";
	public static final String ME = "me";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String CREDIT = "credit";
	public static final String YOU = "you";
	public static final String STATUS = "status";

}

