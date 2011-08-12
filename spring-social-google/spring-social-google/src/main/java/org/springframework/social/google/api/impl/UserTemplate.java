package org.springframework.social.google.api.impl;

import org.springframework.social.google.api.GoogleProfile;
import org.springframework.social.google.api.UserOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractGoogleOperations implements UserOperations {

	private final RestTemplate restTemplate;
	
	public UserTemplate(RestTemplate restTemplate, boolean authorized) {
		super(authorized);
		this.restTemplate = restTemplate;
	}

	public GoogleProfile getUserProfile() {
		return restTemplate.getForObject("https://www.googleapis.com/oauth2/v1/userinfo", GoogleProfile.class);
	}


}
