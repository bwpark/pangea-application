package com.nuri.pangea.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Base.class)
public abstract class Base_ {

	public static volatile SingularAttribute<Base, Instant> created;
	public static volatile SingularAttribute<Base, Instant> modified;
	public static volatile SingularAttribute<Base, Long> id;

	public static final String CREATED = "created";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";

}

