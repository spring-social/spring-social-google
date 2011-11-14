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

import static org.springframework.util.StringUtils.hasText;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.social.google.api.query.QueryBuilder;

/**
 * Abstract superclass for {@link QueryBuilder} implementations.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public abstract class QueryBuilderImpl<Q extends QueryBuilder<?, T>, T> implements QueryBuilder<Q, T> {

	private static final Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
	
	private final String feedUrl;
	private int maxResults;
	
	protected QueryBuilderImpl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	@SuppressWarnings("unchecked")
	protected Q castThis() {
		return (Q)this;
	}
	
	@Override
	public Q maxResultsNumber(int maxResults) {
		this.maxResults = maxResults;
		return castThis();
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, Date value) {
		if(value != null) {
			appendQueryParam(sb, name, dateFormatter.format(value));
		}
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, int value) {
		if(value > 0) {
			appendQueryParam(sb, name, String.valueOf(value));
		}
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, Object value) {
		if(value != null) {
			appendQueryParam(sb, name, value.toString());
		}
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, Enum<?> value) {
		if(value != null) {
			appendQueryParam(sb, name, value.name().toLowerCase());
		}
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, String value) {
		if(hasText(value)) {
			sb.append(name).append('=').append(value.trim()).append('&');
		}
	}
	
	protected StringBuilder build() {
		
		StringBuilder sb = new StringBuilder(feedUrl);
		if(feedUrl.indexOf('?') < 0) {
			sb.append('?');
		} else {
			sb.append('&');
		}
		appendQueryParam(sb, "max-results", maxResults);
		return sb;
	}
}
