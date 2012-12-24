package org.springframework.social.google.api.plus;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.ApiEntity;

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
