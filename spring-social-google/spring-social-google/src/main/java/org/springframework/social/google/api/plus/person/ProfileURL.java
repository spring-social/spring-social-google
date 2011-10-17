package org.springframework.social.google.api.plus.person;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ProfileURL {

	private final String value;
	private final String type;
	
	@JsonCreator
	public ProfileURL(@JsonProperty("value") String value, @JsonProperty("type") String type) {
		this.value = value;
		this.type = type;
	}
	
	@Override
	public String toString() {
		if(type == null) {
			return value;
		}
		return new StringBuilder(value).append(" (").append(type).append(')').toString();
	}
	
	public String getValue() {
		return value;
	}
	
	public String getType() {
		return type;
	}
		
}
