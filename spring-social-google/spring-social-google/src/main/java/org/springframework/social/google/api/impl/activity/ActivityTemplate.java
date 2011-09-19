package org.springframework.social.google.api.impl.activity;

import org.springframework.social.google.api.activity.ActivitiesFeed;
import org.springframework.social.google.api.activity.ActivityOperations;
import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.web.client.RestTemplate;

public class ActivityTemplate extends AbstractGoogleOperations implements
		ActivityOperations {

	private static final String ACTIVITY_URL = "https://www.googleapis.com/plus/v1/people/";
	private static final String ACTIVITIES_PUBLIC = "/activities/public";
	
	public ActivityTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public ActivitiesFeed getActivitiesFeed(String userId, String pageToken) {
		StringBuilder sb = new StringBuilder(ACTIVITY_URL).append(userId).append(ACTIVITIES_PUBLIC);
		if(pageToken != null) {
			sb.append("?pageToken=").append(pageToken);
		}
		return getEntity(sb.toString(), ActivitiesFeed.class);
	}

	@Override
	public ActivitiesFeed getActivitiesFeed(String userId) {
		return getActivitiesFeed(userId, null);
	}

}
