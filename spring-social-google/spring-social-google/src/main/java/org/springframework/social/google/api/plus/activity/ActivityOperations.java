package org.springframework.social.google.api.plus.activity;

public interface ActivityOperations {

	ActivitiesFeed getActivitiesFeed(String userId, String pageToken);

	ActivitiesFeed getActivitiesFeed(String userId);
}
