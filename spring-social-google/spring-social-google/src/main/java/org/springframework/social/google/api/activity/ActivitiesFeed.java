package org.springframework.social.google.api.activity;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ActivitiesFeed {

	private final String id;
	private final Date updated;
	private final String nextPageToken;
	private final List<Activity> activities;
	
	@JsonCreator
	public ActivitiesFeed(
			@JsonProperty("id") String id,
			@JsonProperty("updated") Date updated,
			@JsonProperty("nextPageToken") String nextPageToken,
			@JsonProperty("items") List<Activity> activities) {
		this.id = id;
		this.updated = updated;
		this.nextPageToken = nextPageToken;
		this.activities = activities;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getUpdated() {
		return updated;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
}
