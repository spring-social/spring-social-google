package org.springframework.social.google.api.plus.person.impl;

import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.google.api.plus.person.PersonOperations;
import org.springframework.social.google.api.plus.person.PersonQueryBuilder;
import org.springframework.web.client.RestTemplate;

public class PersonTemplate extends AbstractGooglePlusOperations implements PersonOperations {

	static final String PEOPLE_URL = "https://www.googleapis.com/plus/v1/people/";
	
	public PersonTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public Person getPerson(String id) {
		return getEntity(PEOPLE_URL + id, Person.class);
	}

	@Override
	public Person getGoogleProfile() {
		return getPerson("me");
	}

	@Override
	public PersonQueryBuilder personQuery() {
		return new PersonQueryBuilderImpl(this);
	}

}
