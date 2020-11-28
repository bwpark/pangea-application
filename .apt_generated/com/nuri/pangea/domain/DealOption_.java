package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.DealOptionStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DealOption.class)
public abstract class DealOption_ {

	public static volatile SingularAttribute<DealOption, Instant> created;
	public static volatile SingularAttribute<DealOption, String> name;
	public static volatile SingularAttribute<DealOption, Instant> modified;
	public static volatile SingularAttribute<DealOption, Long> id;
	public static volatile SingularAttribute<DealOption, Deal> pack;
	public static volatile SingularAttribute<DealOption, DealOptionStatus> status;

	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String PACK = "pack";
	public static final String STATUS = "status";

}

