package org.springframework.social.google.api.plus;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.springframework.social.google.api.plus.activity.Activity;
import org.springframework.social.google.api.plus.person.BasePerson;

//@JsonTypeInfo(property="kind", include=As.PROPERTY, use=Id.NAME)
//@JsonSubTypes({@Type(BasePerson.class), @Type(Activity.class)})
public abstract class GooglePlusEntity {

	private final String id;

	protected GooglePlusEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
