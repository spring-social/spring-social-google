package org.springframework.social.google.api.oauth2.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.oauth2.OAuth2Operations;
import org.springframework.social.google.api.oauth2.UserInfo;
import org.springframework.web.client.RestTemplate;

public class OAuth2Template  extends AbstractGoogleApiOperations implements OAuth2Operations {

	private static final String USERINFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
	
	public OAuth2Template(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public UserInfo getUserinfo() {
			return getEntity(USERINFO_URL, UserInfo.class);
	}

}
