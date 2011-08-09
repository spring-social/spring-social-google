package org.springframework.social.google.api.impl;

import org.springframework.social.google.api.Google;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public class GoogleTemplate extends AbstractOAuth1ApiBinding implements Google {

	public GoogleTemplate(String consumerKey, String consumerSecret,
			String accessToken, String accessTokenSecret) {
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
}
