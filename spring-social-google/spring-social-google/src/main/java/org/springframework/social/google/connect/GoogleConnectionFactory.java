package org.springframework.social.google.connect;

import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.google.api.Google;

public class GoogleConnectionFactory extends OAuth1ConnectionFactory<Google> {

	public GoogleConnectionFactory(String consumerKey, String consumerSecret) {
		super("google", new GoogleServiceProvider(consumerKey, consumerSecret), new GoogleAdapter());
	}

}
