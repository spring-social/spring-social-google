package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.google.api.contact.ContactOperations;

public interface Google extends ApiBinding {

	UserOperations userOperations();
	ContactOperations contactOperations();
}
