package org.springframework.social.google.api.plus.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.google.api.plus.Activity;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends Activity {

	@JsonProperty
	private PostObject object;

	@JsonProperty
	private PostAcl access;

	@JsonProperty
	private String verb = "post";

	public PostActivity() {
	}

	public PostActivity(String message) {
		object = new PostObject();
		object.setOriginalContent(message);

		PostAclResource aclResource = new PostAclResource();
		aclResource.setType("domain");

		List<PostAclResource> items = new ArrayList<PostAclResource>();
		items.add(aclResource);

		access = new PostAcl();
		access.setItems(items);
		access.setDomainRestricted(true);
	}

	@Override
	public String getContent() {
		return object.getOriginalContent();
	}

	@Override
	public List<Attachment> getAttachments() {
		if (object.getAttachments() == null){
			return new ArrayList<Attachment>();
		} else {
			return new ArrayList<Attachment>(object.getAttachments());
		}
	}

	@Override
	public int getPlusOners() {
		return 0;
	}

	@Override
	public int getResharers() {
		return 0;
	}

	@Override
	public int getReplies() {
		return 0;
	}
}
