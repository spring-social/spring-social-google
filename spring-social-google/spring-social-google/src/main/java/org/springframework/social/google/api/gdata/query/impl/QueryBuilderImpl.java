package org.springframework.social.google.api.gdata.query.impl;

import static org.apache.commons.lang.time.DateFormatUtils.ISO_DATETIME_FORMAT;

import java.util.Date;
import java.util.List;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.GDataPage;
import org.springframework.social.google.api.gdata.query.QueryBuilder;

public abstract class QueryBuilderImpl<Q extends QueryBuilder<?, T>, T> implements QueryBuilder<Q, T> {

	private final String feedUrl;
	private final AbstractGDataOperations operations;
	private final EntryExtractor<T> extractor;
	
	private int startIndex;
	private int maxResults;
	private Date updatedMin;
	private Date updatedMax;
	
	public QueryBuilderImpl(String feedUrl, AbstractGDataOperations operations, EntryExtractor<T> extractor) {
		this.feedUrl = feedUrl;
		this.operations = operations;
		this.extractor = extractor;
	}
	
	@SuppressWarnings("unchecked")
	protected Q castThis() {
		return (Q)this;
	}
	
	@Override
	public Q startingFromIndex(int startIndex) {
		this.startIndex = startIndex;
		return castThis();
	}
	
	@Override
	public Q maxResultsNumber(int maxResults) {
		this.maxResults = maxResults;
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
	
	protected void appendQueryParam(StringBuilder sb, String name, Date value) {
		appendQueryParam(sb, name, ISO_DATETIME_FORMAT.format(value));
	}
	
	protected void appendQueryParam(StringBuilder sb, String name, Object value) {
		sb.append(name).append('=').append(value).append('&');
	}
	
	protected StringBuilder build() {
		
		StringBuilder sb = new StringBuilder(feedUrl).append('?');
		
		if(startIndex > 0) {
			appendQueryParam(sb, "start-index", startIndex);
		}
		
		if(maxResults > 0) {
			appendQueryParam(sb, "max-results", maxResults);
		}
		
		if(updatedMin != null) {
			appendQueryParam(sb, "updated-min", updatedMin);
		}
		
		if(updatedMax != null) {
			appendQueryParam(sb, "updated-max", updatedMax);
		}
		
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
