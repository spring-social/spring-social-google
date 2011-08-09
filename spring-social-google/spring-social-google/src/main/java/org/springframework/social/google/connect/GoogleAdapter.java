package org.springframework.social.google.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.google.api.Google;

public class GoogleAdapter implements ApiAdapter<Google> {

	public boolean test(Google google) {
		return false;
	}

	public void setConnectionValues(Google google, ConnectionValues values) {
		// TODO Auto-generated method stub
		
	}

	public UserProfile fetchUserProfile(Google google) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateStatus(Google google, String message) {
		// TODO Auto-generated method stub
		
	}

}
