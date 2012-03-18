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
package org.springframework.social.google.api.plus.person.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.plus.person.ContactQueryBuilder;
import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.google.api.plus.person.PersonOperations;
import org.springframework.social.google.api.plus.person.PersonQueryBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * {@link PersonOperations} implementation.
 * @author Gabriel Axel
 */
public class PersonTemplate extends AbstractGoogleApiOperations implements PersonOperations {

	static final String PEOPLE_SEARCH_URL = "https://www.googleapis.com/plus/v1/people";
	static final String PEOPLE_URL = PEOPLE_SEARCH_URL + '/';
	private static final String ACTIVITIES_URL = "https://www.googleapis.com/plus/v1/activities/";
	private static final String PLUSONERS = "/people/plusoners";
	private static final String RESHARERS = "/people/resharers";
	
	static final String FEED_PREFIX = "https://www-opensocial.googleusercontent.com/api/people/@me/";
	static final String CONTACTS_FEED = FEED_PREFIX + "@all/";
	
	public PersonTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public Person getPerson(String id) {
		return getEntity(PEOPLE_URL + id, Person.class);
	}
	
	@Override
	public Person getContact(String id) {
		return getEntity(CONTACTS_FEED + id + "?fields=@all", ContactEntryWrapper.class).getEntry();
	}

	@Override
	public Person getGoogleProfile() {
		return getPerson("me");
	}

	@Override
	public PersonQueryBuilder personQuery() {
		return new PersonQueryBuilderImpl(restTemplate);
	}

	@Override
	public PeoplePage searchPeople(String query, String pageToken) {
		return personQuery().searchFor(query).fromPage(pageToken).getPage();
	}

	@Override
	public PeoplePage getActivityPlusOners(String activityId, String pageToken) {
		return getEntity(ACTIVITIES_URL + activityId + PLUSONERS, PeoplePage.class);
	}

	@Override
	public PeoplePage getActivityResharers(String activityId, String pageToken) {
		return getEntity(ACTIVITIES_URL + activityId + RESHARERS, PeoplePage.class);
	}

	@Override
	public ContactQueryBuilder contactQuery() {
		return new ContactQueryBuilderImpl(restTemplate);
	}

}
