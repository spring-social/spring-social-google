package org.springframework.social.google.api.activity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.profile.BasicProfile;

public class Activity {

	private final String id;
	private final String title;
	private final Date published;
	private final Date updated;
	private final BasicProfile actor;
	
	@JsonCreator
	public Activity(
			@JsonProperty("id") String id, 
			@JsonProperty("title") String title, 
			@JsonProperty("published") Date published, 
			@JsonProperty("updated") Date updated,
			@JsonProperty("actor") BasicProfile actor) {
		this.id = id;
		this.title = title;
		this.published = published;
		this.updated = updated;
		this.actor = actor;
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

	public BasicProfile getActor() {
		return actor;
	}
	
}
