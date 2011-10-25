package org.springframework.social.google.api.gdata.query.impl;

import static org.springframework.util.StringUtils.hasText;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;
import org.springframework.social.google.api.gdata.query.TextQueryBuilder;

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
		if(hasText(text)) {
			appendQueryParam(sb, "q", text.trim());
		}
		return sb;
	}
}
