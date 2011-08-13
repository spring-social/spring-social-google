package org.springframework.social.google.api.impl;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

public class AbstractGoogleOperations {

	protected final RestTemplate restTemplate;
	private final boolean isAuthorized;

	public AbstractGoogleOperations(RestTemplate restTemplate, boolean isAuthorized) {
		this.restTemplate = restTemplate;
		this.isAuthorized = isAuthorized;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException();
		}
	}
}
