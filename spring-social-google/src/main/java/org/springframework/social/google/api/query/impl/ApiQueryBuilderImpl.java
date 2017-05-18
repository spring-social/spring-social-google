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
package org.springframework.social.google.api.query.impl;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.social.google.api.query.ApiPage;
import org.springframework.social.google.api.query.ApiQueryBuilder;
import org.springframework.social.google.api.query.QueryBuilder;
import org.springframework.web.client.RestTemplate;

import static org.springframework.util.StringUtils.hasText;

/**
 * Abstract superclass for {@link QueryBuilder} implementations that query Google+.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public class ApiQueryBuilderImpl<Q extends ApiQueryBuilder<?, T>, T extends ApiPage<?>> extends QueryBuilderImpl<Q, T> implements ApiQueryBuilder<Q, T> {

	private final Class<T> type;
	private final RestTemplate restTemplate;

	public ApiQueryBuilderImpl(Class<T> type, RestTemplate restTemplate) {
		this.type = type;
		this.restTemplate = restTemplate;
	}
	
	public ApiQueryBuilderImpl(String feedUrl, Class<T> type, RestTemplate restTemplate) {
		super(feedUrl);
		this.type = type;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Q fromPage(String pageToken) {
		pageToken = hasText(pageToken) ? encode(pageToken) : pageToken;
		return appendQueryParam("pageToken", pageToken);
	}
	
	@Override
	public T getPage() {
		try {
			return restTemplate.getForObject(new URI(build().toString()), type);
		} catch (URISyntaxException e) {
			throw new IllegalStateException(e);
		}
	}

}
