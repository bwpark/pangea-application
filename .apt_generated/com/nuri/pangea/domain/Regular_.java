package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.RegularStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Regular.class)
public abstract class Regular_ {

	public static volatile SingularAttribute<Regular, Instant> created;
	public static volatile SingularAttribute<Regular, String> name;
	public static volatile SingularAttribute<Regular, Avatar> me;
	public static volatile SingularAttribute<Regular, Instant> modified;
	public static volatile SingularAttribute<Regular, Long> id;
	public static volatile SingularAttribute<Regular, Avatar> you;
	public static volatile SingularAttribute<Regular, RegularStatus> status;

	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String ME = "me";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String YOU = "you";
	public static final String STATUS = "status";

}

