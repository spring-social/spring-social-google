package org.springframework.social.google.api.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		HttpEntity<String> responseEntity = restTemplate.exchange("https://www-opensocial.googleusercontent.com/api/people/@me/", HttpMethod.GET, requestEntity, String.class);
		System.out.println("========================================");
		System.out.println(responseEntity.getBody());
		System.out.println("========================================");
		
		return restTemplate.getForObject("https://www-opensocial.googleusercontent.com/api/people/@me/", GoogleProfile.class);
	}


}
