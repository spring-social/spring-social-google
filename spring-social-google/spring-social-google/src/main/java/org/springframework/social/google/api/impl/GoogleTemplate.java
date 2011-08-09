package org.springframework.social.google.api.impl;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

	private final UserOperations userOperations;
	
	public GoogleTemplate(String accessToken) {
		super(accessToken);
		userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
	}

	public UserOperations userOperations() {
		return userOperations;
	}
}
