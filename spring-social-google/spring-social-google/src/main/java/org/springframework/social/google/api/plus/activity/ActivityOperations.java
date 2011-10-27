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
package org.springframework.social.google.api.plus.activity;

/**
 * Defines operations for reading Google+ activities.
 * To use "me" as user ID, requires OAuth2 scope https://www.googleapis.com/auth/plus.me
 * @author Gabriel Axel
 */
public interface ActivityOperations {

	/**
	 * Retrieves a user's activities.
	 * @param userId user ID or "me"
	 * @param pageToken page token, or null for first page
	 * @return {@link Activity} page.
	 */
	ActivitiesPage getActivitiesPage(String userId, String pageToken);
	
	/**
	 * Retrieves a user's first page of activit.ies
	 * @param userId user ID or "me"
	 * @return {@link Activity} page
	 */
	ActivitiesPage getActivitiesPage(String userId);
	
	/**
	 * Creates an {@link ActivityQueryBuilder}.
	 * @return a new {@link ActivityQueryBuilder}
	 */
	ActivityQueryBuilder activityQuery();
}
