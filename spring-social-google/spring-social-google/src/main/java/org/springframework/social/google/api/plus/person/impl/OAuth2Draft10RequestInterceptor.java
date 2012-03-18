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
package org.springframework.social.google.api.plus.person.impl;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

/**
 * An interceptor that replaces OAuth2 "Bearer" prefix with Draft 10 "OAuth" 
 * prefix and keeps the access token.
 * Used in a single PoCo operation.
 * @see ContactQueryBuilderImpl#getPage()
 * @author Gabriel Axel
 */
class OAuth2Draft10RequestInterceptor implements
		ClientHttpRequestInterceptor {

	private static final String AUTHORIZATION = "Authorization";
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		HttpRequest protectedResourceRequest = new HttpRequestDecorator(request);
		HttpHeaders headers = protectedResourceRequest.getHeaders();
		String authorization = headers.getFirst(AUTHORIZATION);
		authorization = authorization.replaceFirst("Bearer", "OAuth");
		headers.set(AUTHORIZATION, authorization);
		return execution.execute(protectedResourceRequest, body);
	}

}
