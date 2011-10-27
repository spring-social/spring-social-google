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
import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

/**
 * {@link ClientHttpRequestInterceptor} implementation that sets GData-specific headers.
 * @author Gabriel Axel
 */
public class GDataInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		HttpRequest protectedResourceRequest = new HttpRequestDecorator(request);
		protectedResourceRequest.getHeaders().set("GData-Version", "3.0");
		
		if(request.getMethod() == PUT || request.getMethod() == DELETE) {
			protectedResourceRequest.getHeaders().set("If-Match", "*");
		}
		
		return execution.execute(protectedResourceRequest, body);
	}

}
