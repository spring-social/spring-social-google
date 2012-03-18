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

import static org.springframework.social.google.api.plus.person.impl.PersonTemplate.FEED_PREFIX;
import static org.springframework.util.StringUtils.hasText;

import org.springframework.social.google.api.plus.person.ContactQueryBuilder;
import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.query.impl.QueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link ContactQueryBuilder} implementation.
 * @author Gabriel Axel
 */
public class ContactQueryBuilderImpl extends QueryBuilderImpl<ContactQueryBuilder, PeoplePage> implements ContactQueryBuilder {

	private static final int DEFAULT_PAGE_SIZE = 20;
	
	private final RestTemplate restTemplate;
	private final OAuth2Draft10RequestInterceptor oauth2Draft10RequestInterceptor = new OAuth2Draft10RequestInterceptor();
	
	private int index;
	private String group = "@all";
	
	public ContactQueryBuilderImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		maxResults = DEFAULT_PAGE_SIZE;
	}
	
	@Override
	public ContactQueryBuilder fromGroup(String group) {
		this.group = group;
		return this;
	}
		
	@Override
	protected StringBuilder build() {
		StringBuilder sb = new StringBuilder(FEED_PREFIX).append(group).append("?fields=@all&");
		appendQueryParam(sb, "startIndex", index);
		appendQueryParam(sb, "count", maxResults);
		return sb;
	}

	@Override
	public ContactQueryBuilder fromPage(String pageToken) {
		index = hasText(pageToken) ? index = Integer.parseInt(pageToken) : 0;
		return this;
	}
	
	@Override
	public PeoplePage getPage() {
		
		restTemplate.getInterceptors().add(oauth2Draft10RequestInterceptor);
		ContactsResponse response = restTemplate.getForObject(build().toString(), ContactsResponse.class);
		restTemplate.getInterceptors().remove(oauth2Draft10RequestInterceptor);

		String nextPageToken = response.getTotal() < index + maxResults ? null : String.valueOf(index + maxResults);
		return new PeoplePage(response.getItems(), nextPageToken);
		
	}

}
