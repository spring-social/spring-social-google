/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.plus.activity.impl;

import org.springframework.social.google.api.plus.activity.ActivitiesPage;
import org.springframework.social.google.api.plus.activity.ActivityOperations;
import org.springframework.social.google.api.plus.activity.ActivityQueryBuilder;
import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.web.client.RestTemplate;

/**
 * {@link ActivityOperations} implementation.
 * @author Gabriel Axel
 */
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
