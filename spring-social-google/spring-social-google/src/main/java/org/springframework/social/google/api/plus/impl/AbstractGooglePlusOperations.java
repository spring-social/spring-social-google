package org.springframework.social.google.api.plus.impl;

import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.plus.GooglePlusEntity;
import org.springframework.social.google.api.plus.query.PlusPage;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractGooglePlusOperations extends AbstractGoogleOperations {

	protected AbstractGooglePlusOperations(RestTemplate restTemplate,
			boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}
	
	protected <T extends GooglePlusEntity> T getEntity(String url, Class<T> type) {
		return restTemplate.getForObject(url, type);
	}
	
	public <T extends PlusPage<?>> T getPage(String url, Class<T> type) {
		System.out.println(url);
		return restTemplate.getForObject(url, type);
	}

}
