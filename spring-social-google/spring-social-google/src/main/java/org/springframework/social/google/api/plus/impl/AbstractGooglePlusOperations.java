package org.springframework.social.google.api.plus.impl;

import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractGooglePlusOperations extends AbstractGoogleOperations {

	protected AbstractGooglePlusOperations(RestTemplate restTemplate,
			boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}
	
	protected <T> T getEntity(String url, Class<T> type) {
		return restTemplate.getForObject(url, type);
	}

}
