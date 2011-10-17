package org.springframework.social.google.api.impl.query;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.impl.helper.EntryExtractor;
import org.springframework.social.google.api.query.QueryBuilder;
import org.springframework.social.google.api.query.TextQueryBuilder;

public abstract class TextQueryBuilderImpl<Q extends QueryBuilder<?, T>, T> extends QueryBuilderImpl<Q, T> implements TextQueryBuilder<Q, T> {

	private String text;
	
	public TextQueryBuilderImpl(String feedUrl,
			AbstractGoogleOperations operations, EntryExtractor<T> extractor) {
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
		if(isNotBlank(text)) {
			appendQueryParam(sb, "q", text.trim());
		}
		return sb;
	}
}
