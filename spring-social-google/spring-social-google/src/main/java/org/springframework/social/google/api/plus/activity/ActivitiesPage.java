package org.springframework.social.google.api.plus.activity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.query.PlusPage;

public class ActivitiesPage extends PlusPage<Activity> {

	public ActivitiesPage(@JsonProperty("items") List<Activity> items, @JsonProperty("nextPageToken") String nextPageToken) {
		super(items, nextPageToken);
	}

}
