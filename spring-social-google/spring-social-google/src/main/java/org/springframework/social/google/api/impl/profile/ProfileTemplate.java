package org.springframework.social.google.api.impl.profile;

import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.profile.GoogleProfile;
import org.springframework.social.google.api.profile.ProfileOperations;
import org.springframework.web.client.RestTemplate;

public class ProfileTemplate extends AbstractGoogleOperations implements
		ProfileOperations {

	private static final String PROFILE_URL = "https://www.googleapis.com/plus/v1/people/";
	
	public ProfileTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public GoogleProfile getGoogleProfile(String id) {
		return getEntity(PROFILE_URL + id, GoogleProfile.class);
	}

	@Override
	public GoogleProfile getGoogleProfile() {
		return getGoogleProfile("me");
	}

}
