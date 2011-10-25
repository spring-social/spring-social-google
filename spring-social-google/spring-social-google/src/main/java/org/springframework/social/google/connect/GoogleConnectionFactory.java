package org.springframework.social.google.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.google.api.Google;

/**
 * Google ConnectionFactory implementation.
 * @author Gabriel Axel
 *
 */
public class GoogleConnectionFactory extends OAuth2ConnectionFactory<Google> {

	public GoogleConnectionFactory(String clientId, String clientSecret) {
		super("google", new GoogleServiceProvider(clientId, clientSecret),
				new GoogleAdapter());
	}

}
