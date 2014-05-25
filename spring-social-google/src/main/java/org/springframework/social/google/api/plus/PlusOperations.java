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
package org.springframework.social.google.api.plus;

import org.springframework.social.google.api.plus.moments.Moment;
import org.springframework.social.google.api.plus.moments.MomentQueryBuilder;
import org.springframework.social.google.api.plus.moments.MomentsPage;

/**
 * Defines operations for searching and retrieving Google+ activities, comments
 * and people. To use "me" as user ID, requires OAuth2 scope
 * https://www.googleapis.com/auth/plus.me , and to get your e-mail address add the scope "email".
 * 
 * @author Gabriel Axel
 */
public interface PlusOperations {

	/**
	 * Retrieves an activity by its ID.
	 * 
	 * @param id
	 *            activity ID
	 * @return the retrieved {@link Activity}
	 */
	Activity getActivity(String id);

	/**
	 * Retrieves a user's activities.
	 * 
	 * @param userId
	 *            user ID or "me"
	 * @param pageToken
	 *            page token, or null for first page
	 * @return {@link Activity} page.
	 */
	ActivitiesPage getActivities(String userId, String pageToken);

	/**
	 * Retrieves a user's first page of activities
	 * 
	 * @param userId
	 *            user ID or "me"
	 * @return {@link Activity} page
	 */
	ActivitiesPage getActivities(String userId);

	/**
	 * Searches for public activities matching a text query
	 * 
	 * @param query
	 *            text to search by
	 * @param pageToken
	 *            page token, or null for first page
	 * @return {@link Activity} page
	 */
	ActivitiesPage searchPublicActivities(String query, String pageToken);

	/**
	 * Creates an {@link ActivityQueryBuilder}.
	 * 
	 * @return a new {@link ActivityQueryBuilder}
	 */
	ActivityQueryBuilder activityQuery();

	/**
	 * Retrieves a comment by its ID.
	 * 
	 * @param id
	 *            comment ID
	 * @return the retrieved {@link ActivityComment}
	 */
	ActivityComment getComment(String id);

	/**
	 * Retrieves the comments of an activity.
	 * 
	 * @param activityId
	 *            activity ID
	 * @param pageToken
	 *            page to retrieve or null for first page
	 * @return page of {@link ActivityComment}
	 */
	ActivityCommentsPage getComments(String activityId, String pageToken);

	/**
	 * Retrieves a user's Google profile.
	 * 
	 * @param id
	 *            user ID or "me"
	 * @return the retrieved {@link Person}
	 */
	Person getPerson(String id);

	/**
	 * Retrieves the authenticated user's Google profile.
	 * 
	 * @return the retrieved {@link Person}
	 */
	Person getGoogleProfile();

	/**
	 * Retrieves people in a user's circles
	 * 
	 * @param id
	 *            userId or "me"
	 * @param pageToken
	 *            page to retrieve or null for the first page
	 * @return {@link PeoplePage} of visible people to the authenticated user
	 */
	PeoplePage getPeopleInCircles(String id, String pageToken);

	/**
	 * Retrieves people that match the query text.
	 * 
	 * @param query
	 *            text to search by
	 * @param pageToken
	 *            page to retrieve or null for the first page
	 * @return {@link PeoplePage} with the matching results
	 */
	PeoplePage searchPeople(String query, String pageToken);

	/**
	 * Retrieves people who have +1'd an activity.
	 * 
	 * @param activityId
	 *            activity ID
	 * @param pageToken
	 *            page to retrieve or null for the first page
	 * @return {@link PeoplePage} of +1'ers
	 */
	PeoplePage getActivityPlusOners(String activityId, String pageToken);

	/**
	 * Retrieves people who have reshared an activity.
	 * 
	 * @param activityId
	 *            activity ID
	 * @param pageToken
	 *            page to retrieve or null for the first page
	 * @return {@link PeoplePage} of resharers
	 */
	PeoplePage getActivityResharers(String activityId, String pageToken);

	/**
	 * Creates a {@link PersonQueryBuilder}.
	 * 
	 * @return a new {@link PersonQueryBuilder}
	 */
	PersonQueryBuilder personQuery();

	/**
	 * Inserts a new moment (app activity)
	 * 
	 * @param moment
	 *            Moment to insert
	 * @return the inserted moment
	 */
	Moment insertMoment(Moment moment);

	/**
	 * Creates a new {@link MomentQueryBuilder}
	 * 
	 * @return a new {@link MomentQueryBuilder}
	 */
	MomentQueryBuilder momentQuery();

	/**
	 * Retrieves moments (app activities) created by this application
	 * 
	 * @param pageToken
	 *            page to retrieve or null for first page
	 * @return {@link MomentsPage} with moments created by this application
	 */
	MomentsPage getMoments(String pageToken);

	/**
	 * Deletes a moment (app activity) created by this application
	 * 
	 * @param id
	 *            the moment ID
	 */
	void deleteMoment(String id);
}
