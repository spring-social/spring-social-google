package org.springframework.social.google.api.plus.person;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PlaceLived {

	private final String value;

	@JsonCreator
	public PlaceLived(@JsonProperty("value") String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}
	
	
}
