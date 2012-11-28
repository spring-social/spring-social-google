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
package org.springframework.social.google.api.drive.impl;

import static org.springframework.social.google.api.drive.DriveFile.FOLDER;
import static org.springframework.social.google.api.drive.impl.DriveTemplate.DRIVE_FILES_URL;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.google.api.drive.DriveFileQueryBuilder;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link DriveFileQueryBuilder} implementation.
 * @author Gabriel Axel
 */
class DriveFileQueryBuilderImpl extends ApiQueryBuilderImpl<DriveFileQueryBuilder, DriveFilesPage> implements DriveFileQueryBuilder {

	private static final String TITLE = "title";
	private static final String FULL_TEXT = "fullText";
	private static final String MIME_TYPE = "mimeType";
	private static final String MODIFIED_DATE = "modifiedDate";
	private static final String LAST_VIEWED_BY_ME_DATE = "lastViewedByMeDate";
	private static final String TRASHED = "trashed";
	private static final String STARRED = "starred";
	private static final String HIDDEN = "hidden";
	private static final String PARENTS = "parents";
	private static final String OWNERS = "owners";
	private static final String WRITERS = "writers";
	private static final String READERS = "readers";
	private static final String SHARED_WITH_ME = "sharedWithMe";

	private static String encode(String text) {
		try {
			return URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	private static final String CONTAINS = " contains ";
	private static final String IN = " in ";
	private static final String EQ = "=";
	private static final String NE = "!=";
	private static final String GT = ">";
	private static final String GE = ">=";
	private static final String LT = "<";
	private static final String LE = "<=";
	private static final String NOT = "not ";
	private static final String AND = " and ";

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	private boolean negate;
	private List<String> qTerms = new ArrayList<String>();
	
	DriveFileQueryBuilderImpl(RestTemplate restTemplate) {
		super(DRIVE_FILES_URL, DriveFilesPage.class, restTemplate);
	}
	
	private DriveFileQueryBuilder addStringCompareTerm(String field, String operator, String value) {
		qTerms.add(new StringBuilder(field).append(operator).append('\'').append(value).append('\'').toString());
		return this;
	}
	
	private DriveFileQueryBuilder addDateCompareTerm(String field, String operator, Date value) {
		return addStringCompareTerm(field, operator, dateFormat.format(value));
	}
	
	private DriveFileQueryBuilder addBooleanTerm(String field, boolean value) {
		qTerms.add(new StringBuilder(field).append(EQ).append(value).toString());
		return this;
	}
	
	private DriveFileQueryBuilder addInTerm(String field, String value) {
		qTerms.add(new StringBuilder().append('\'').append(value).append('\'').append(IN).append(field).toString());
		return this;
	}

	@Override
	public DriveFileQueryBuilder not() {
		negate = true;
		return this;
	}
	
	@Override
	public DriveFileQueryBuilder titleIs(String title) {
		return addStringCompareTerm(TITLE, EQ, title);
	}
	
	@Override
	public DriveFileQueryBuilder titleContains(String text) {
		return addStringCompareTerm(TITLE, CONTAINS, text);
	}
	
	@Override
	public DriveFileQueryBuilder fullTextContains(String text) {
		return addStringCompareTerm(FULL_TEXT, CONTAINS, text);
	}
	
	@Override
	public DriveFileQueryBuilder mimeTypeIs(String mimeType) {
		return addStringCompareTerm(MIME_TYPE, EQ, mimeType);
	}
	
	@Override
	public DriveFileQueryBuilder mimeTypeIsNot(String mimeType) {
		return addStringCompareTerm(MIME_TYPE, NE, mimeType);
	}
	
	@Override
	public DriveFileQueryBuilder modifiedDateIs(Date date) {
		return addDateCompareTerm(MODIFIED_DATE, EQ, date);
	}
	
	@Override
	public DriveFileQueryBuilder modifiedDateIsOrBefore(Date date) {
		return addDateCompareTerm(MODIFIED_DATE, LE, date);
	}
	
	@Override
	public DriveFileQueryBuilder modifiedDateBefore(Date date) {
		return addDateCompareTerm(MODIFIED_DATE, LT, date);
	}
	
	@Override
	public DriveFileQueryBuilder modifiedDateIsOrAfter(Date date) {
		return addDateCompareTerm(MODIFIED_DATE, GE, date);
	}
	
	@Override
	public DriveFileQueryBuilder modifiedDateAfter(Date date) {
		return addDateCompareTerm(MODIFIED_DATE, GT, date);
	}
	
	@Override
	public DriveFileQueryBuilder lastViewedByMeIs(Date date) {
		return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, EQ, date);
	}
	
	@Override
	public DriveFileQueryBuilder lastViewedByMeIsOrBefore(Date date) {
		return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, LE, date);
	}
	
	@Override
	public DriveFileQueryBuilder lastViewedByMeBefore(Date date) {
		return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, LT, date);
	}
	
	@Override
	public DriveFileQueryBuilder lastViewedByMeIsOrAfter(Date date) {
		return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, GE, date);
	}
	
	@Override
	public DriveFileQueryBuilder lastViewedByMeAfter(Date date) {
		return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, GT, date);
	}
	
	@Override
	public DriveFileQueryBuilder trashed(boolean trashed) {
		return addBooleanTerm(TRASHED, trashed);
	}
	
	@Override
	public DriveFileQueryBuilder starred(boolean starred) {
		return addBooleanTerm(STARRED, starred);
	}
	
	@Override
	public DriveFileQueryBuilder hidden(boolean hidden) {
		return addBooleanTerm(HIDDEN, hidden);
	}
	
	@Override
	public DriveFileQueryBuilder parentIs(String id) {
		return addInTerm(PARENTS, id);
	}
	
	@Override
	public DriveFileQueryBuilder ownerIs(String email) {
		return addInTerm(OWNERS, email);
	}
	
	@Override
	public DriveFileQueryBuilder writerIs(String email) {
		return addInTerm(WRITERS, email);
	}
	
	@Override
	public DriveFileQueryBuilder readerIs(String email) {
		return addInTerm(READERS, email);
	}
	
	@Override
	public DriveFileQueryBuilder sharedWithMe() {
		qTerms.add(SHARED_WITH_ME);
		return this;
	}
	
	@Override
	public DriveFileQueryBuilder isFolder() {
		return mimeTypeIs(FOLDER);
	}
	
	@Override
	public DriveFileQueryBuilder isFile() {
		return mimeTypeIsNot(FOLDER);
	}
	
	@Override
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		if(!qTerms.isEmpty()) {
			StringBuilder qb = new StringBuilder(collectionToDelimitedString(qTerms, AND));
			if(negate) {
				qb.insert(0, NOT);
			}
			String encoded = encode(qb.toString());
			appendQueryParam(sb, "q", encoded);
		}
		return sb;
	}
}
