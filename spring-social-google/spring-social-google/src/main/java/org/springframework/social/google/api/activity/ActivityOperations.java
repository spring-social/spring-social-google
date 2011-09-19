package org.springframework.social.google.api.activity;

public interface ActivityOperations {

	ActivitiesFeed getActivitiesFeed(String userId, String pageToken);

	ActivitiesFeed getActivitiesFeed(String userId);
}
