package org.springframework.social.google.api.gdata.query.impl;

import java.util.Date;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.PublishTextQueryBuilder;
import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;

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
