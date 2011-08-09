package org.springframework.social.google.connect;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;

public class GoogleServiceProvider extends AbstractOAuth1ServiceProvider<Google> {

	public GoogleServiceProvider(String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret, 
				"https://www.google.com/accounts/OAuthGetRequestToken", 
				"https://www.google.com/accounts/OAuthAuthorizeToken", 
				"https://www.google.com/accounts/OAuthGetAccessToken"));
	}

	@Override
	public Google getApi(String accessToken, String secret) {
		return new GoogleTemplate(getConsumerKey(), getConsumerSecret(), accessToken, secret);
	}


}
