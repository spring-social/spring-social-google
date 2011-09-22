package org.springframework.social.google.api.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.profile.BasicProfile;

public class Activity {
	
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

	private final String id;
	private final String title;
	private final Date published;
	private final Date updated;
	private final String url;
	private final BasicProfile actor;
	private final ActivityObject object;
		
	@JsonCreator
	public Activity(
			@JsonProperty("id") String id, 
			@JsonProperty("title") String title, 
			@JsonProperty("published") Date published, 
			@JsonProperty("updated") Date updated,
			@JsonProperty("url") String url,
			@JsonProperty("actor") BasicProfile actor,
			@JsonProperty("object") ActivityObject object) {
		this.id = id;
		this.title = title;
		this.published = published;
		this.updated = updated;
		this.url = url;
		this.actor = actor;
		this.object = object;
	}

	public String getId() {
		return id;
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

	public BasicProfile getActor() {
		return actor;
	}
	
	public String getContent() {
		return object.content;
	}
	
	public List<Attachment> getAttachments() {
		return object.attachments == null ? new ArrayList<Attachment>() : object.attachments;
	}
}
