package org.springframework.social.google.api.plus.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.springframework.social.google.api.plus.GooglePlusEntity;
import org.springframework.social.google.api.plus.person.BasePerson;

@JsonTypeName("plus#activity")
public class Activity extends GooglePlusEntity {
	
	public static class ActivityObject {
		
		private final String content;
		private final List<Attachment> attachments;

		@JsonCreator
		protected ActivityObject(@JsonProperty("content") String content,
				@JsonProperty("attachments") List<Attachment> attachments) {
			this.content = content;
			this.attachments = attachments;
		}
		
	}

	private final String title;
	private final Date published;
	private final Date updated;
	private final String url;
	private final BasePerson actor;
	private final ActivityObject object;
		
	@JsonCreator
	public Activity(
			@JsonProperty("id") String id, 
			@JsonProperty("title") String title, 
			@JsonProperty("published") Date published, 
			@JsonProperty("updated") Date updated,
			@JsonProperty("url") String url,
			@JsonProperty("actor") BasePerson actor,
			@JsonProperty("object") ActivityObject object) {
		super(id);
		this.title = title;
		this.published = published;
		this.updated = updated;
		this.url = url;
		this.actor = actor;
		this.object = object;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublished() {
		return published;
	}

	public Date getUpdated() {
		return updated;
	}
	
	public String getUrl() {
		return url;
	}

	public BasePerson getActor() {
		return actor;
	}
	
	public String getContent() {
		return object.content;
	}
	
	public List<Attachment> getAttachments() {
		return object.attachments == null ? new ArrayList<Attachment>() : object.attachments;
	}
}
