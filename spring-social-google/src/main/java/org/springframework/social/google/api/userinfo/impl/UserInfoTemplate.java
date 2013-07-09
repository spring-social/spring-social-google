/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.google.api.userinfo.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.social.google.api.userinfo.UserInfoOperations;
import org.springframework.web.client.RestTemplate;

/**
 * {@link UserInfoOperations} implementation.
 * @author Gabriel Axel
 *
 */
public class UserInfoTemplate extends AbstractGoogleApiOperations implements UserInfoOperations {

	public UserInfoTemplate(RestTemplate restTemplate, boolean authorized) {
		super(restTemplate, authorized);
	}

	public GoogleUserInfo getUserInfo() {
		requireAuthorization();
		return restTemplate.getForObject("https://www.googleapis.com/oauth2/v2/userinfo", GoogleUserInfo.class);
	}

}
