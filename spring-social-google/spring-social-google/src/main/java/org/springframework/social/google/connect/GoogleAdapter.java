package org.springframework.social.google.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;

/**
 * Google ApiAdapter implementation.
 * @author Gabriel Axel
 *
 */
public class GoogleAdapter implements ApiAdapter<Google> {

	public boolean test(Google google) {
		return true;
	}

	public void setConnectionValues(Google google, ConnectionValues values) {
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		values.setProviderUserId(profile.getId());
		values.setDisplayName(profile.getName());
		values.setProfileUrl(profile.getLink());
		values.setImageUrl(profile.getProfilePictureUrl());
	}

	public UserProfile fetchUserProfile(Google google) {
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		return new UserProfileBuilder()
			.setUsername(profile.getEmail())
			.setEmail(profile.getEmail())
			.setName(profile.getName())
			.setFirstName(profile.getFirstName())
			.setLastName(profile.getLastName())
			.build();
	}

	public void updateStatus(Google google, String message) {
		throw new UnsupportedOperationException();
	}

}
