package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.Category2IssueStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category2Issue.class)
public abstract class Category2Issue_ {

	public static volatile SingularAttribute<Category2Issue, Category2Issue> parent;
	public static volatile SetAttribute<Category2Issue, Category2Issue> children;
	public static volatile SingularAttribute<Category2Issue, Instant> created;
	public static volatile SingularAttribute<Category2Issue, String> icon;
	public static volatile SingularAttribute<Category2Issue, String> name;
	public static volatile SingularAttribute<Category2Issue, String> description;
	public static volatile SingularAttribute<Category2Issue, Instant> modified;
	public static volatile SingularAttribute<Category2Issue, Long> id;
	public static volatile SingularAttribute<Category2Issue, Category2IssueStatus> status;

	public static final String PARENT = "parent";
	public static final String CHILDREN = "children";
	public static final String CREATED = "created";
	public static final String ICON = "icon";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

