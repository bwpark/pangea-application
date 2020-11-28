package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.PackStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pack.class)
public abstract class Pack_ {

	public static volatile SingularAttribute<Pack, Instant> created;
	public static volatile SetAttribute<Pack, Deal> deals;
	public static volatile SingularAttribute<Pack, Avatar> me;
	public static volatile SingularAttribute<Pack, String> description;
	public static volatile SingularAttribute<Pack, Instant> modified;
	public static volatile SingularAttribute<Pack, Long> id;
	public static volatile SingularAttribute<Pack, Integer> point;
	public static volatile SingularAttribute<Pack, Integer> coin;
	public static volatile SingularAttribute<Pack, String> shipTo;
	public static volatile SingularAttribute<Pack, PackStatus> status;

	public static final String CREATED = "created";
	public static final String DEALS = "deals";
	public static final String ME = "me";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String POINT = "point";
	public static final String COIN = "coin";
	public static final String SHIP_TO = "shipTo";
	public static final String STATUS = "status";

}

