package org.springframework.social.google.api.plus.person.impl;

import static org.springframework.social.google.api.plus.person.impl.PersonTemplate.PEOPLE_SEARCH_URL;

import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.plus.person.PersonQueryBuilder;
import org.springframework.social.google.api.plus.query.impl.PlusQueryBuilderImpl;

public class PersonQueryBuilderImpl extends PlusQueryBuilderImpl<PersonQueryBuilder, PeoplePage> implements PersonQueryBuilder {

	public PersonQueryBuilderImpl(AbstractGooglePlusOperations operations) {
		super(PEOPLE_SEARCH_URL, PeoplePage.class, operations);
	}

}
