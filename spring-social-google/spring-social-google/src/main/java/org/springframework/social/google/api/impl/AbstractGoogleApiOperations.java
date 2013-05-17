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
package org.springframework.social.google.api.impl;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.util.StringUtils.hasText;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.google.api.ApiEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract superclass for implementations that work with Google+ APIs.
 * @author Gabriel Axel
 */
public abstract class AbstractGoogleApiOperations {
	
	protected final RestTemplate restTemplate;
	protected final boolean isAuthorized;

	protected AbstractGoogleApiOperations(RestTemplate restTemplate, boolean isAuthorized) {
		this.restTemplate = restTemplate;
		this.isAuthorized = isAuthorized;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("google");
		}
	}
	
	protected <T> T getEntity(String url, Class<T> type) {
		return restTemplate.getForObject(url, type);
	}
	
	protected <T extends ApiEntity> T saveEntity(String baseUrl, T entity) {
		
		String url;
		HttpMethod method;
		
		if(hasText(entity.getId())) {
			url = baseUrl + '/' + entity.getId();
			method = PUT;
		} else {
			url = baseUrl;
			method = POST;
		}
		
		@SuppressWarnings("unchecked")
		ResponseEntity<T> response = 
			restTemplate.exchange(url, method, new HttpEntity<T>(entity), (Class<T>)entity.getClass());
		
		return response.getBody();
	}

	protected void deleteEntity(String baseUrl, ApiEntity entity) {
		restTemplate.delete(baseUrl + '/' + entity.getId());
	}
	
	protected <T> T patch(String url, Object request, Class<T> responseType) {
		ResponseEntity<T> response = restTemplate.exchange(url, PATCH, new HttpEntity<Object>(request), responseType);
		return response.getBody();
	}
}
