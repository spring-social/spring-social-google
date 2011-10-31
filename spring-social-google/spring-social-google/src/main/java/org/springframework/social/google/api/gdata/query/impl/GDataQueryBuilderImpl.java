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
import java.util.List;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.GDataPage;
import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;
import org.springframework.social.google.api.query.QueryBuilder;
import org.springframework.social.google.api.query.impl.QueryBuilderImpl;

/**
 * Abstract superclass for {@link QueryBuilder} implementations.
 * @author Gabriel Axel
 * @param <Q> the {@link QueryBuilder} type
 * @param <T> the model type
 */
public abstract class GDataQueryBuilderImpl<Q extends GDataQueryBuilder<?, T>, T> extends QueryBuilderImpl<Q, T> implements GDataQueryBuilder<Q, T> {
	
	private final AbstractGDataOperations operations;
	private final EntryExtractor<T> extractor;
	
	private int startIndex;
	
	private Date updatedMin;
	private Date updatedMax;
	
	public GDataQueryBuilderImpl(String feedUrl, AbstractGDataOperations operations, EntryExtractor<T> extractor) {
		super(feedUrl);
		this.operations = operations;
		this.extractor = extractor;
	}
	
	@Override
	public Q startingFromIndex(int startIndex) {
		this.startIndex = startIndex;
		return castThis();
	}
	
	@Override
	public Q updatedFrom(Date updatedMin) {
		this.updatedMin = updatedMin;
		return castThis();
	}
	
	@Override
	public Q updatedUntil(Date updatedMax) {
		this.updatedMax = updatedMax;
		return castThis();
	}
	
	protected StringBuilder build() {
		
		StringBuilder sb = super.build();
		appendQueryParam(sb, "start-index", startIndex);
		appendQueryParam(sb, "updated-min", updatedMin);
		appendQueryParam(sb, "updated-max", updatedMax);
		return sb;
	}
	
	@Override
	public List<T> getList() {
		return operations.extractFeedEntries(build().toString(), extractor);
	}

	@Override
	public GDataPage<T> getPage() {
		return operations.extractFeedEntriesPage(build().toString(), extractor);
	}
}
