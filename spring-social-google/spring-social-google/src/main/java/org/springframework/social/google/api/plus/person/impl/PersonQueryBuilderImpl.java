package org.springframework.social.google.api.plus.person.impl;

import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.plus.person.PersonQueryBuilder;
import org.springframework.social.google.api.plus.query.impl.PlusQueryBuilderImpl;
import static org.springframework.social.google.api.plus.person.impl.PersonTemplate.PEOPLE_URL;

public class PersonQueryBuilderImpl extends PlusQueryBuilderImpl<PersonQueryBuilder, PeoplePage> implements PersonQueryBuilder {

	public PersonQueryBuilderImpl(AbstractGooglePlusOperations operations) {
		super(PEOPLE_URL, PeoplePage.class, operations);
	}

}
