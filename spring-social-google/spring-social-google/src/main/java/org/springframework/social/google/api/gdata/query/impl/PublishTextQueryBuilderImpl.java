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
package org.springframework.social.google.api.gdata.query.impl;

import java.util.Date;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.PublishTextQueryBuilder;
import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;
import org.springframework.social.google.api.query.QueryBuilder;

/**
 * Extension of {@link TextQueryBuilderImpl} for searching by publish date.
 * @author Gabriel Axel
 * @param <Q> the {@link QueryBuilder} type
 * @param <T> the model type
 */
public abstract class PublishTextQueryBuilderImpl<Q extends GDataQueryBuilder<?, T>, T> extends TextQueryBuilderImpl<Q, T> implements PublishTextQueryBuilder<Q, T> {

	private Date publishedMin;
	private Date publishedMax;
	
	public PublishTextQueryBuilderImpl(String feedUrl,
			AbstractGDataOperations operations, EntryExtractor<T> extractor) {
		super(feedUrl, operations, extractor);
	}

	@Override
	public Q publishedFrom(Date publishedMin) {
		this.publishedMin = publishedMin;
		return castThis();
	}
	
	@Override
	public Q publishedUntil(Date publishedMax) {
		this.publishedMax = publishedMax;
		return castThis();
	}
	
	@Override
	public StringBuilder build() {
		
		StringBuilder sb = super.build();
		
		if(publishedMin != null) {
			appendQueryParam(sb, "published-min", publishedMin);
		}
		
		if(publishedMax != null) {
			appendQueryParam(sb, "published-max", publishedMax);
		}
		
		return sb;
	}
}
