package org.springframework.social.google.api.drive.impl;

import java.util.Date;

import org.springframework.social.google.api.drive.FileCommentQueryBuilder;
import org.springframework.social.google.api.drive.FileCommentsPage;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

public class FileCommentQueryBuilderImpl extends ApiQueryBuilderImpl<FileCommentQueryBuilder, FileCommentsPage> implements FileCommentQueryBuilder {

	private boolean includeDeleted;
	
	private Date updatedMin;
	
	public FileCommentQueryBuilderImpl(String feedUrl,
			Class<FileCommentsPage> type, RestTemplate restTemplate) {
		super(feedUrl, type, restTemplate);
	}

	@Override
	public FileCommentQueryBuilder setIncludeDeleted(boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
		return this;
	}

	@Override
	public FileCommentQueryBuilder setUpdatedMin(Date updatedMin) {
		this.updatedMin = updatedMin;
		return this;
	}

	@Override
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		appendQueryParam(sb, "includeDeleted", includeDeleted);
		appendQueryParam(sb, "updatedMin", updatedMin);
		return sb;
	}
}
