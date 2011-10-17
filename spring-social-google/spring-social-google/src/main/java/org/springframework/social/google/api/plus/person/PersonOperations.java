package org.springframework.social.google.api.plus.person;

import org.springframework.social.google.api.plus.query.PlusPage;

public interface PersonOperations {

	Person getPerson(String id);
	Person getGoogleProfile();
	
//	PlusPage<BasePerson> searchPeople(String text, String pageToken);
}
