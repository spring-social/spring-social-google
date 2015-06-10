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
package org.springframework.social.google.api.drive;

import java.util.Date;

import org.springframework.social.google.api.query.ApiQueryBuilder;
import org.springframework.social.google.api.query.QueryBuilder;

/**
 * {@link QueryBuilder} for {@link DriveFile}
 * @author Gabriel Axel
 */
public interface DriveFileQueryBuilder extends ApiQueryBuilder<DriveFileQueryBuilder, DriveFilesPage> {

	DriveFileQueryBuilder not();

	DriveFileQueryBuilder titleIs(String title);

	DriveFileQueryBuilder titleContains(String text);

	DriveFileQueryBuilder fullTextContains(String text);

	DriveFileQueryBuilder mimeTypeIs(String mimeType);

	DriveFileQueryBuilder mimeTypeIsNot(String mimeType);

	DriveFileQueryBuilder modifiedDateIs(Date date);

	DriveFileQueryBuilder modifiedDateIsOrBefore(Date date);

	DriveFileQueryBuilder modifiedDateBefore(Date date);

	DriveFileQueryBuilder modifiedDateIsOrAfter(Date date);

	DriveFileQueryBuilder modifiedDateAfter(Date date);

	DriveFileQueryBuilder lastViewedByMeIs(Date date);

	DriveFileQueryBuilder lastViewedByMeIsOrBefore(Date date);

	DriveFileQueryBuilder lastViewedByMeBefore(Date date);

	DriveFileQueryBuilder lastViewedByMeIsOrAfter(Date date);

	DriveFileQueryBuilder lastViewedByMeAfter(Date date);

	DriveFileQueryBuilder trashed(boolean trashed);

	DriveFileQueryBuilder starred(boolean starred);

	DriveFileQueryBuilder hidden(boolean hidden);

	DriveFileQueryBuilder parentIs(String id);

	DriveFileQueryBuilder ownerIs(String email);

	DriveFileQueryBuilder writerIs(String email);

	DriveFileQueryBuilder readerIs(String email);

	DriveFileQueryBuilder sharedWithMe();

	DriveFileQueryBuilder propertiesHas(String propertyKey, String propertyValue, PropertyVisibility visibility);

	DriveFileQueryBuilder isFolder();

	DriveFileQueryBuilder isFile();

}
