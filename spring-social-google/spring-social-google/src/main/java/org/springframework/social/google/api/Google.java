package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.google.api.gdata.contact.ContactOperations;
import org.springframework.social.google.api.gdata.picasa.PicasaOperations;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.social.google.api.plus.activity.ActivityOperations;
import org.springframework.social.google.api.plus.person.PersonOperations;

/**
* Interface specifying a basic set of operations for interacting with Google APIs.
* Implemented by {@link GoogleTemplate}.
* @author Gabriel Axel
*/
public interface Google extends ApiBinding {

	LegacyProfileOperations userOperations();
	ContactOperations contactOperations();
	PersonOperations personOperations();
	PicasaOperations picasaOperations();
	ActivityOperations activityOperations();
}
