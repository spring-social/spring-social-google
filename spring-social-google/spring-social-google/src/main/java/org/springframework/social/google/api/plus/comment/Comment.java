package org.springframework.social.google.api.plus.comment;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.person.BasePerson;

/**
 * Model class representing a comment.
 * 
 * @author Gabriel Axel
 */
public class Comment {

	public static class CommentObject {

		private final String content;

		@JsonCreator
		public CommentObject(@JsonProperty("content") String content) {
			this.content = content;
		}
	}

	private final String id;
	private final Date published;
	private final Date updated;
	private final String content;
	private final BasePerson actor;

	@JsonCreator
	public Comment(@JsonProperty("id") String id,
			@JsonProperty("published") Date published,
			@JsonProperty("updated") Date updated,
			@JsonProperty("object") CommentObject object,
			@JsonProperty("actor") BasePerson actor) {
		this.id = id;
		this.published = published;
		this.updated = updated;
		this.content = object.content;
		this.actor = actor;
	}

	public String getId() {
		return id;
	}

	public Date getPublished() {
		return published;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getContent() {
		return content;
	}

	public BasePerson getActor() {
		return actor;
	}

}
