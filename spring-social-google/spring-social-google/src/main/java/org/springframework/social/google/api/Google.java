package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.google.api.contact.ContactOperations;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;

public interface Google extends ApiBinding {

	LegacyProfileOperations userOperations();
	ContactOperations contactOperations();
}
