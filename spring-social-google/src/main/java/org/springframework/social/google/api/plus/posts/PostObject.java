package org.springframework.social.google.api.plus.posts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostObject {
	
	@JsonProperty
	private List<PostAttachments> attachments;
	@JsonProperty
	private String id;
	@JsonProperty
	private String originalContent;
	public List<PostAttachments> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<PostAttachments> attachments) {
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
