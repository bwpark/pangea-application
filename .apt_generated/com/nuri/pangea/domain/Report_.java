package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.ReportStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Report.class)
public abstract class Report_ {

	public static volatile SingularAttribute<Report, Instant> created;
	public static volatile SingularAttribute<Report, String> name;
	public static volatile SingularAttribute<Report, Avatar> me;
	public static volatile SingularAttribute<Report, String> description;
	public static volatile SingularAttribute<Report, Instant> modified;
	public static volatile SingularAttribute<Report, Long> id;
	public static volatile SingularAttribute<Report, Avatar> you;
	public static volatile SingularAttribute<Report, ReportStatus> status;

	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String ME = "me";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String YOU = "you";
	public static final String STATUS = "status";

}

