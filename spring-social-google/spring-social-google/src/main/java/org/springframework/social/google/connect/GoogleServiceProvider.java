package org.springframework.social.google.connect;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class GoogleServiceProvider extends AbstractOAuth2ServiceProvider<Google> {

	public GoogleServiceProvider(String clientId, String clientSecret) {
		super(new GoogleOAuth2Template(clientId, clientSecret));
	}

	@Override
	public Google getApi(String accessToken) {
		return new GoogleTemplate(accessToken);
	}

}
