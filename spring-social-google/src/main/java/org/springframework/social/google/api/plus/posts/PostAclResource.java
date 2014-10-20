package org.springframework.social.google.api.plus.posts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PostAclResource {

	@JsonProperty
	private String type;
	@JsonProperty
	private String id;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
