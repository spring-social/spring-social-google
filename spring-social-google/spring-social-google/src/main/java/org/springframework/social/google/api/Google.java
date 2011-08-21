package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;

public interface Google extends ApiBinding {

	UserOperations userOperations();
	ContactOperations contactOperations();
}
