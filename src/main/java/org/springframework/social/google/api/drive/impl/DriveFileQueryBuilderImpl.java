/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.social.google.api.drive.DriveFileQueryBuilder;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.drive.PropertyVisibility;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link DriveFileQueryBuilder} implementation.
 * @author Gabriel Axel
 */
class DriveFileQueryBuilderImpl extends ApiQueryBuilderImpl<DriveFileQueryBuilder, DriveFilesPage>
  implements DriveFileQueryBuilder {

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
  private static final String PROPERTIES = "properties";

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
  private static final String HAS = " has ";
  private static final String OBJ_START = "{ ";
  private static final String OBJ_END = " }";

  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

  private boolean negate;
  private final List<String> qTerms = new ArrayList<>();

  DriveFileQueryBuilderImpl(final RestTemplate restTemplate) {
    super(DRIVE_FILES_URL, DriveFilesPage.class, restTemplate);
  }

  private DriveFileQueryBuilder addStringCompareTerm(final String field, final String operator, final String value) {
    qTerms.add(field + operator + '\'' + value + '\'');
    return this;
  }

  private DriveFileQueryBuilder addDateCompareTerm(final String field, final String operator, final Date value) {
    return addStringCompareTerm(field, operator, dateFormat.format(value));
  }

  private DriveFileQueryBuilder addBooleanTerm(final String field, final boolean value) {
    qTerms.add(field + EQ + value);
    return this;
  }

  private DriveFileQueryBuilder addInTerm(final String field, final String value) {
    qTerms.add("'" + value + '\'' + IN + field);
    return this;
  }

  private DriveFileQueryBuilder addHasTerm(final String field, final Map<String, Object> item) {
    final List<String> criteria = new ArrayList<>();

    for (final Entry<String, Object> p : item.entrySet()) {
      if (p.getValue() != null) {
        criteria.add(p.getKey() + EQ + '\'' + p.getValue() + '\'');
      }
    }

    qTerms.add(field + HAS + OBJ_START + collectionToDelimitedString(criteria, AND) + OBJ_END);
    return this;
  }

  @Override
  public DriveFileQueryBuilder not() {
    negate = true;
    return this;
  }

  @Override
  public DriveFileQueryBuilder titleIs(final String title) {
    return addStringCompareTerm(TITLE, EQ, title);
  }

  @Override
  public DriveFileQueryBuilder titleContains(final String text) {
    return addStringCompareTerm(TITLE, CONTAINS, text);
  }

  @Override
  public DriveFileQueryBuilder fullTextContains(final String text) {
    return addStringCompareTerm(FULL_TEXT, CONTAINS, text);
  }

  @Override
  public DriveFileQueryBuilder mimeTypeIs(final String mimeType) {
    return addStringCompareTerm(MIME_TYPE, EQ, mimeType);
  }

  @Override
  public DriveFileQueryBuilder mimeTypeIsNot(final String mimeType) {
    return addStringCompareTerm(MIME_TYPE, NE, mimeType);
  }

  @Override
  public DriveFileQueryBuilder modifiedDateIs(final Date date) {
    return addDateCompareTerm(MODIFIED_DATE, EQ, date);
  }

  @Override
  public DriveFileQueryBuilder modifiedDateIsOrBefore(final Date date) {
    return addDateCompareTerm(MODIFIED_DATE, LE, date);
  }

  @Override
  public DriveFileQueryBuilder modifiedDateBefore(final Date date) {
    return addDateCompareTerm(MODIFIED_DATE, LT, date);
  }

  @Override
  public DriveFileQueryBuilder modifiedDateIsOrAfter(final Date date) {
    return addDateCompareTerm(MODIFIED_DATE, GE, date);
  }

  @Override
  public DriveFileQueryBuilder modifiedDateAfter(final Date date) {
    return addDateCompareTerm(MODIFIED_DATE, GT, date);
  }

  @Override
  public DriveFileQueryBuilder lastViewedByMeIs(final Date date) {
    return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, EQ, date);
  }

  @Override
  public DriveFileQueryBuilder lastViewedByMeIsOrBefore(final Date date) {
    return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, LE, date);
  }

  @Override
  public DriveFileQueryBuilder lastViewedByMeBefore(final Date date) {
    return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, LT, date);
  }

  @Override
  public DriveFileQueryBuilder lastViewedByMeIsOrAfter(final Date date) {
    return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, GE, date);
  }

  @Override
  public DriveFileQueryBuilder lastViewedByMeAfter(final Date date) {
    return addDateCompareTerm(LAST_VIEWED_BY_ME_DATE, GT, date);
  }

  @Override
  public DriveFileQueryBuilder trashed(final boolean trashed) {
    return addBooleanTerm(TRASHED, trashed);
  }

  @Override
  public DriveFileQueryBuilder starred(final boolean starred) {
    return addBooleanTerm(STARRED, starred);
  }

  @Override
  public DriveFileQueryBuilder hidden(final boolean hidden) {
    return addBooleanTerm(HIDDEN, hidden);
  }

  @Override
  public DriveFileQueryBuilder parentIs(final String id) {
    return addInTerm(PARENTS, id);
  }

  @Override
  public DriveFileQueryBuilder ownerIs(final String email) {
    return addInTerm(OWNERS, email);
  }

  @Override
  public DriveFileQueryBuilder writerIs(final String email) {
    return addInTerm(WRITERS, email);
  }

  @Override
  public DriveFileQueryBuilder readerIs(final String email) {
    return addInTerm(READERS, email);
  }

  @Override
  public DriveFileQueryBuilder sharedWithMe() {
    qTerms.add(SHARED_WITH_ME);
    return this;
  }

  @Override
  public DriveFileQueryBuilder propertiesHas(final String propertyKey, final String propertyValue,
                                             final PropertyVisibility propertyVisibility) {
    final Map<String, Object> propertyDef = new HashMap<>();
    propertyDef.put("key", propertyKey);
    propertyDef.put("value", propertyValue);
    propertyDef.put("visibility", propertyVisibility);
    return addHasTerm(PROPERTIES, propertyDef);
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
  protected String build() {
    if (!qTerms.isEmpty()) {
      final StringBuilder qb = new StringBuilder(collectionToDelimitedString(qTerms, AND));
      if (negate) {
        qb.insert(0, NOT);
      }
      final String encoded = encode(qb.toString());
      appendQueryParam("q", encoded);
    }
    return super.build();
  }
}
