package org.springframework.social.google.api.plus.person.impl;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

public class OAuth2Draft10RequestInterceptor implements
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
