package org.springframework.social.google.api.query.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.social.google.api.query.QueryBuilder;

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
		appendQueryParam(sb, name, dateFormatter.format(value));
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, Object value) {
		sb.append(name).append('=').append(value).append('&');
	}
	
	protected StringBuilder build() {
		
		StringBuilder sb = new StringBuilder(feedUrl).append('?');
		
		if(maxResults > 0) {
			appendQueryParam(sb, "max-results", maxResults);
		}
		
		return sb;
	}
}
