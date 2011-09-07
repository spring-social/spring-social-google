package org.springframework.social.google.api.impl;

import static org.springframework.http.HttpMethod.*;
import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

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
