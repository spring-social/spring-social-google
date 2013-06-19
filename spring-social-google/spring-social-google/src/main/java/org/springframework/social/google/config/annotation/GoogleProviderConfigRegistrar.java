/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.config.annotation;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.social.config.annotation.AbstractProviderConfigRegistrarSupport;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.config.GoogleApiHelper;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.google.security.GoogleAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * {@link ImportBeanDefinitionRegistrar} for configuring a {@link GoogleConnectionFactory} bean and a request-scoped {@link Google} bean.
 * @author Craig Walls
 */
public class GoogleProviderConfigRegistrar extends AbstractProviderConfigRegistrarSupport {

	public GoogleProviderConfigRegistrar() {
		super(EnableGoogle.class, GoogleConnectionFactory.class, GoogleApiHelper.class);
	}

	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return GoogleAuthenticationService.class;
	}

}
