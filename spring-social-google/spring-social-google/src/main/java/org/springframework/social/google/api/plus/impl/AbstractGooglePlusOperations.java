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
package org.springframework.social.google.api.plus.impl;

import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.plus.query.PlusPage;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract superclass for implementations that work with Google+ APIs.
 * @author Gabriel Axel
 */
public abstract class AbstractGooglePlusOperations extends AbstractGoogleOperations {

	protected AbstractGooglePlusOperations(RestTemplate restTemplate,
			boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}
	
	protected <T> T getEntity(String url, Class<T> type) {
		return restTemplate.getForObject(url, type);
	}
	
	public <T extends PlusPage<?>> T getPage(String url, Class<T> type) {
		System.out.println(url);
		return restTemplate.getForObject(url, type);
	}

}
