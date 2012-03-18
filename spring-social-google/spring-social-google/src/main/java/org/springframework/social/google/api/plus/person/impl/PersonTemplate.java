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
		return new PersonQueryBuilderImpl(this);
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
