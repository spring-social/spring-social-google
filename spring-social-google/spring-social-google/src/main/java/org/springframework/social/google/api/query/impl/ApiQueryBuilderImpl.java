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

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.query.ApiPage;
import org.springframework.social.google.api.query.ApiQueryBuilder;
import org.springframework.social.google.api.query.QueryBuilder;

/**
 * Abstract superclass for {@link QueryBuilder} implementations that query Google+.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public class ApiQueryBuilderImpl<Q extends ApiQueryBuilder<?, T>, T extends ApiPage<?>> extends QueryBuilderImpl<Q, T> implements ApiQueryBuilder<Q, T> {

	private final Class<T> type;
	private final AbstractGoogleApiOperations operations;

	private String text;
	private String pageToken;
		
	public ApiQueryBuilderImpl(String feedUrl, Class<T> type, AbstractGoogleApiOperations operations) {
		super(feedUrl);
		this.type = type;
		this.operations = operations;
	}
	
	@Override
	public Q searchFor(String text) {
		this.text = text;
		return castThis();
	}
	
	@Override
	public Q fromPage(String pageToken) {
		this.pageToken = pageToken;
		return castThis();
	}
	
	@Override
	protected StringBuilder build() {
		
		StringBuilder sb = super.build();
		appendQueryParam(sb, "query", text);
		appendQueryParam(sb, "pageToken", pageToken);
		return sb;
	}

	@Override
	public T getPage() {
		return operations.getPage(build().toString(), type);
	}

}
