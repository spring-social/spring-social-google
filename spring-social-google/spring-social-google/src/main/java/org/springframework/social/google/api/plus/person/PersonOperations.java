package org.springframework.social.google.api.plus.person;


public interface PersonOperations {

	Person getPerson(String id);
	Person getGoogleProfile();
	PersonQueryBuilder personQuery();
}
