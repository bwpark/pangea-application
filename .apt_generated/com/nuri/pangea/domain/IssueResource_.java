package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.ResourceType;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IssueResource.class)
public abstract class IssueResource_ {

	public static volatile SingularAttribute<IssueResource, Issue> issue;
	public static volatile SingularAttribute<IssueResource, Instant> created;
	public static volatile SingularAttribute<IssueResource, String> link;
	public static volatile SingularAttribute<IssueResource, Long> id;
	public static volatile SingularAttribute<IssueResource, ResourceType> type;

	public static final String ISSUE = "issue";
	public static final String CREATED = "created";
	public static final String LINK = "link";
	public static final String ID = "id";
	public static final String TYPE = "type";

}

