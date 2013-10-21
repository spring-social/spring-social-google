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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.social.google.api.query.QueryBuilder;

/**
 * Abstract superclass for {@link QueryBuilder} implementations.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public abstract class QueryBuilderImpl<Q extends QueryBuilder<?, T>, T> implements QueryBuilder<Q, T> {

	private static final Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
	
	protected String feedUrl;
	private Map<String, String> params = new HashMap<String, String>();	
	
	protected QueryBuilderImpl() {
	}
	
	protected QueryBuilderImpl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	@SuppressWarnings("unchecked")
	protected Q castThis() {
		return (Q)this;
	}
	
	@Override
	public Q maxResultsNumber(int maxResults) {
		appendQueryParam("maxResults", maxResults);
		return castThis();
	}
	
	protected Q appendQueryParam(String name, Date value) {
		if(value != null) {
			appendQueryParam(name, dateFormatter.format(value));
		}
		return castThis();
	}
	
	protected Q appendQueryParam(String name, int value) {
		if(value > 0) {
			appendQueryParam(name, String.valueOf(value));
		}
		return castThis();
	}
	
	protected Q appendQueryParam(String name, boolean value) {
		if(value) {
			appendQueryParam(name, "true");
		}
		return castThis();
	}
	
	protected Q appendQueryParam(String name, Object value) {
		if(value != null) {
			appendQueryParam(name, value.toString());
		}
		return castThis();
	}
	
	protected Q appendQueryParam(StringBuilder sb, String name, Enum<?> value) {
		if(value != null) {
			appendQueryParam(name, value.name().toLowerCase());
		}
		return castThis();
	}
	
	protected Q appendQueryParam(String name, String value) {
		if(hasText(value)) {
			params.put(name, value);
		}
		return castThis();
	}
	
	protected String build() {
		
		StringBuilder sb = new StringBuilder(feedUrl);
		if(!params.isEmpty() && feedUrl.indexOf('?') < 0) {
			sb.append('?');
		}
		
		for(Entry<String, String> param : params.entrySet()) {
			if(sb.charAt(sb.length() - 1) != '?') {
				sb.append('&');
			}
			sb.append(param.getKey()).append('=').append(param.getValue());
		}
		
		return sb.toString();
	}
}
