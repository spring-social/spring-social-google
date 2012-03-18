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

import static org.springframework.social.google.api.plus.person.impl.PersonTemplate.PEOPLE_SEARCH_URL;

import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.plus.person.PersonQueryBuilder;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link PersonQueryBuilder} implementation.
 * @author Gabriel Axel
 */
public class PersonQueryBuilderImpl extends ApiQueryBuilderImpl<PersonQueryBuilder, PeoplePage> implements PersonQueryBuilder {

	private String text;

	public PersonQueryBuilderImpl(RestTemplate restTemplate) {
		super(PEOPLE_SEARCH_URL, PeoplePage.class, restTemplate);
	}
	
	@Override
	public PersonQueryBuilder searchFor(String text) {
		this.text = text;
		return this;
	}
	
	@Override
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		appendQueryParam(sb, "query", text);
		return sb;
	}

}
