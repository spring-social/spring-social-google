package org.springframework.social.google.api.plus.query.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.social.google.api.plus.query.PlusPage;
import org.springframework.social.google.api.plus.query.PlusQueryBuilder;
import org.springframework.social.google.api.query.impl.QueryBuilderImpl;

public class PlusQueryBuilderImpl<Q extends PlusQueryBuilder<?, T>, T extends PlusPage<?>> extends QueryBuilderImpl<Q, T> implements PlusQueryBuilder<Q, T> {

	private final Class<T> type;
	private final AbstractGooglePlusOperations operations;

	private String text;
	private String pageToken;
		
	public PlusQueryBuilderImpl(String feedUrl, Class<T> type, AbstractGooglePlusOperations operations) {
		super(feedUrl);
		this.type = type;
		this.operations = operations;
	}
	
	@Override
	public Q searchFor(String text) {
		this.text = text;
		return castThis();
	}
	
	@Override
	public Q fromPage(String pageToken) {
		this.pageToken = pageToken;
		return castThis();
	}
	
	@Override
	protected StringBuilder build() {
		
		StringBuilder sb = super.build();
		
		if(isNotBlank(text)) {
			appendQueryParam(sb, "query", text);
		}
		
		if(isNotBlank(pageToken)) {
			appendQueryParam(sb, "pageToken", pageToken);
		}
		
		return sb;
	}

	@Override
	public T getPage() {
		return operations.getPage(build().toString(), type);
	}

}
