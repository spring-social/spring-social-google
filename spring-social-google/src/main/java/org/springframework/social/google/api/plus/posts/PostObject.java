package org.springframework.social.google.api.plus.posts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostObject {

	@JsonProperty
	private List<PostAttachment> attachments;
	@JsonProperty
	private String id;
	@JsonProperty
	private String originalContent;

	public List<PostAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<PostAttachment> attachments) {
		this.attachments = attachments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

}
