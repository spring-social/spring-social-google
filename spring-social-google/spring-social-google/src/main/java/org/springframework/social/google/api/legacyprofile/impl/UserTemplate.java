package org.springframework.social.google.api.legacyprofile.impl;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractGDataOperations implements LegacyProfileOperations {

	public UserTemplate(RestTemplate restTemplate, boolean authorized) {
		super(restTemplate, authorized);
	}

	public LegacyGoogleProfile getUserProfile() {
		requireAuthorization();
		return restTemplate.getForObject("https://www.googleapis.com/oauth2/v1/userinfo", LegacyGoogleProfile.class);
	}

}
