package org.springframework.social.google.api.plus;

import java.util.Date;

import org.springframework.social.google.api.ApiEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing a comment.
 * 
 * @author Gabriel Axel
 */
public class ActivityComment extends ApiEntity {

	public static class CommentObject {

		@JsonProperty
		private String content;
	}

	private Date published;
	
	private Date updated;
	
	@JsonProperty
	private CommentObject object;
	
	private Person actor;

	public Date getPublished() {
		return published;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getContent() {
		return object != null ? object.content : null;
	}

	public Person getActor() {
		return actor;
	}

}
