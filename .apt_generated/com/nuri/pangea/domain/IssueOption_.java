package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.IssueOptionStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IssueOption.class)
public abstract class IssueOption_ {

	public static volatile SingularAttribute<IssueOption, Issue> issue;
	public static volatile SingularAttribute<IssueOption, Instant> created;
	public static volatile SingularAttribute<IssueOption, String> name;
	public static volatile SingularAttribute<IssueOption, String> description;
	public static volatile SingularAttribute<IssueOption, Instant> modified;
	public static volatile SingularAttribute<IssueOption, Long> id;
	public static volatile SingularAttribute<IssueOption, Integer> point;
	public static volatile SingularAttribute<IssueOption, Integer> coin;
	public static volatile SingularAttribute<IssueOption, IssueOptionStatus> status;

	public static final String ISSUE = "issue";
	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String POINT = "point";
	public static final String COIN = "coin";
	public static final String STATUS = "status";

}

