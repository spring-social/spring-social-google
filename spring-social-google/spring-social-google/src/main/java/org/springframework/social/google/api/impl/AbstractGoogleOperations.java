package org.springframework.social.google.api.impl;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractGoogleOperations {

	protected final RestTemplate restTemplate;
	protected final boolean isAuthorized;

	protected AbstractGoogleOperations(RestTemplate restTemplate, boolean isAuthorized) {
		this.restTemplate = restTemplate;
		this.isAuthorized = isAuthorized;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException();
		}
	}

}