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

import static org.springframework.util.StringUtils.hasText;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;
import org.springframework.social.google.api.gdata.query.TextQueryBuilder;
import org.springframework.social.google.api.query.QueryBuilder;

/**
 * Extension of {@link GDataQueryBuilderImpl} for full text search.
 * @author Gabriel Axel
 * @param <Q> the {@link QueryBuilder} type
 * @param <T> the model type
 */
public abstract class TextQueryBuilderImpl<Q extends GDataQueryBuilder<?, T>, T> extends GDataQueryBuilderImpl<Q, T> implements TextQueryBuilder<Q, T> {

	private String text;
	
	public TextQueryBuilderImpl(String feedUrl,
			AbstractGDataOperations operations, EntryExtractor<T> extractor) {
		super(feedUrl, operations, extractor);
	}

	@Override
	public Q searchFor(String text) {
		this.text = text;
		return castThis();
	}
	
	@Override
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		appendQueryParam(sb, "q", text);
		return sb;
	}
}
