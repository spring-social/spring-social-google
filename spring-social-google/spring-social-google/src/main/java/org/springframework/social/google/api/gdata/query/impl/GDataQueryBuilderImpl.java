package org.springframework.social.google.api.gdata.query.impl;

import java.util.Date;
import java.util.List;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.query.GDataPage;
import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;
import org.springframework.social.google.api.query.impl.QueryBuilderImpl;

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
		
		if(startIndex > 0) {
			appendQueryParam(sb, "start-index", startIndex);
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
