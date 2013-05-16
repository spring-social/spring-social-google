package org.springframework.social.google.security;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class GoogleAuthenticationService extends OAuth2AuthenticationService<Google> {

	public GoogleAuthenticationService(String apiKey, String appSecret) {
		super(new GoogleConnectionFactory(apiKey, appSecret));
	}

}
