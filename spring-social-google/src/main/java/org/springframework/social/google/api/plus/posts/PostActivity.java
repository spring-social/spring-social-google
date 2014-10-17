package org.springframework.social.google.api.plus.posts;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostActivity {
	
	@JsonProperty
	private PostObject object;
	@JsonProperty
	private PostAcl access;
	public void createActivity(String message){
		object = new PostObject();
		List<PostAclResource> items = new ArrayList<PostAclResource>();
		PostAclResource aclResource = new PostAclResource();
		access = new PostAcl();		
		aclResource.setType("domain");
		items.add(aclResource);
		object.setOriginalContent(message);
		access.setItems(items);
		access.setDomainRestricted(true);		
	}
}
