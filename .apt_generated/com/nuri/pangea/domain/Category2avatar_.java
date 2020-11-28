package com.nuri.pangea.domain;

import com.nuri.pangea.domain.enumeration.Category2avatarStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category2avatar.class)
public abstract class Category2avatar_ {

	public static volatile SingularAttribute<Category2avatar, Category2avatar> parent;
	public static volatile SetAttribute<Category2avatar, Category2avatar> children;
	public static volatile SingularAttribute<Category2avatar, Instant> created;
	public static volatile SingularAttribute<Category2avatar, String> icon;
	public static volatile SingularAttribute<Category2avatar, String> name;
	public static volatile SingularAttribute<Category2avatar, String> description;
	public static volatile SingularAttribute<Category2avatar, Instant> modified;
	public static volatile SingularAttribute<Category2avatar, Long> id;
	public static volatile SingularAttribute<Category2avatar, Category2avatarStatus> status;
	public static volatile SetAttribute<Category2avatar, Avatar> avatars;

	public static final String PARENT = "parent";
	public static final String CHILDREN = "children";
	public static final String CREATED = "created";
	public static final String ICON = "icon";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED = "modified";
	public static final String ID = "id";
	public static final String STATUS = "status";
	public static final String AVATARS = "avatars";

}

