package org.springframework.social.google.api.plus.posts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class PostAcl {

	@JsonProperty
	private List<PostAclResource> items;
	@JsonProperty
	private boolean domainRestricted;
	
	public List<PostAclResource> getItems() {
		return items;
	}

	public void setItems(List<PostAclResource> items) {
		this.items = items;
	}

	public boolean isDomainRestricted() {
		return domainRestricted;
	}

	public void setDomainRestricted(boolean domainRestricted) {
		this.domainRestricted = domainRestricted;
	}
	
}
