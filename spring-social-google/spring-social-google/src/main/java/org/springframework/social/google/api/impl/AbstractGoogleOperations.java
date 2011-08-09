package org.springframework.social.google.api.impl;

import org.springframework.social.MissingAuthorizationException;

public class AbstractGoogleOperations {

	private final boolean isAuthorized;

	public AbstractGoogleOperations(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException();
		}
	}
}
