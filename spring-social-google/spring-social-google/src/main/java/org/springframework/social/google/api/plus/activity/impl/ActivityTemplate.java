package org.springframework.social.google.api.plus.activity.impl;

import org.springframework.social.google.api.plus.activity.ActivitiesPage;
import org.springframework.social.google.api.plus.activity.ActivityOperations;
import org.springframework.social.google.api.plus.activity.ActivityQueryBuilder;
import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.web.client.RestTemplate;

public class ActivityTemplate extends AbstractGooglePlusOperations implements ActivityOperations {

	private static final String ACTIVITY_URL = "https://www.googleapis.com/plus/v1/people/";
	private static final String ACTIVITIES_PUBLIC = "/activities/public";
	
	public ActivityTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public ActivitiesPage getActivitiesPage(String userId, String pageToken) {
		StringBuilder sb = new StringBuilder(ACTIVITY_URL).append(userId).append(ACTIVITIES_PUBLIC);
		if(pageToken != null) {
			sb.append("?pageToken=").append(pageToken);
		}
		return getPage(sb.toString(), ActivitiesPage.class);
	}

	@Override
	public ActivitiesPage getActivitiesPage(String userId) {
		return getActivitiesPage(userId, null);
	}

	@Override
	public ActivityQueryBuilder activityQuery() {
		return new ActivityQueryBuilderImpl(this);
	}

}
