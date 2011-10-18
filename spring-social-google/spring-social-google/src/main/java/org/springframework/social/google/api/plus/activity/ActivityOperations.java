package org.springframework.social.google.api.plus.activity;


public interface ActivityOperations {

	ActivitiesPage getActivitiesPage(String userId, String pageToken);
	ActivitiesPage getActivitiesPage(String userId);
	ActivityQueryBuilder activityQuery();
}
