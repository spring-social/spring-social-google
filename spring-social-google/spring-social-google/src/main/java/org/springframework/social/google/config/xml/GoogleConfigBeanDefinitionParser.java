package org.springframework.social.google.config.xml;

import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.google.config.GoogleApiHelper;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.google.security.GoogleAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

public class GoogleConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

	protected GoogleConfigBeanDefinitionParser() {
		super(GoogleConnectionFactory.class, GoogleApiHelper.class);
	}

	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return GoogleAuthenticationService.class;
	}

}
