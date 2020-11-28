package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.DealStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Deal.class)
public abstract class Deal_ {

	public static volatile SingularAttribute<Deal, Integer> unitPrice;
	public static volatile SingularAttribute<Deal, Integer> quantity;
	public static volatile SingularAttribute<Deal, Instant> created;
	public static volatile SingularAttribute<Deal, String> description;
	public static volatile SingularAttribute<Deal, Pack> pack;
	public static volatile SingularAttribute<Deal, Integer> point;
	public static volatile SingularAttribute<Deal, Issue> with;
	public static volatile SetAttribute<Deal, DealOption> deals;
	public static volatile SingularAttribute<Deal, String> name;
	public static volatile SingularAttribute<Deal, Instant> modified;
	public static volatile SingularAttribute<Deal, Long> id;
	public static volatile SingularAttribute<Deal, Avatar> to;
	public static volatile SingularAttribute<Deal, Integer> coin;
	public static volatile SingularAttribute<Deal, DealStatus> status;

	public static final String UNIT_PRICE = "unitPrice";
	public static final String QUANTITY = "quantity";
	public static final String CREATED = "created";
	public static final String DESCRIPTION = "description";
	public static final String PACK = "pack";
	public static final String POINT = "point";
	public static final String WITH = "with";
	public static final String DEALS = "deals";
	public static final String NAME = "name";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String TO = "to";
	public static final String COIN = "coin";
	public static final String STATUS = "status";

}

