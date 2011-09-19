package org.springframework.social.google.api.profile;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Organization {

	private final String name;
	private final String title;
	private final String type;
	
	@JsonCreator
	public Organization(@JsonProperty("name") String name, @JsonProperty("title") String title, @JsonProperty("type") String type) {
		this.name = name;
		this.title = title;
		this.type = type;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		if(title != null) {
			sb.append(" / ").append(title);
		}
		if(type != null) {
			sb.append(" (").append(type).append(')');
		}
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
}
