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

import java.util.Date;

import org.springframework.social.google.api.drive.FileCommentQueryBuilder;
import org.springframework.social.google.api.drive.FileCommentsPage;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link FileCommentQueryBuilder} implementation.
 * @author Gabriel Axel
 */
public class FileCommentQueryBuilderImpl extends ApiQueryBuilderImpl<FileCommentQueryBuilder, FileCommentsPage> implements FileCommentQueryBuilder {

  public FileCommentQueryBuilderImpl(final String feedUrl,
                                     final Class<FileCommentsPage> type, final RestTemplate restTemplate) {
    super(feedUrl, type, restTemplate);
  }

  @Override
  public FileCommentQueryBuilder setIncludeDeleted(final boolean includeDeleted) {
    return appendQueryParam("includeDeleted", includeDeleted);
  }

  @Override
  public FileCommentQueryBuilder setUpdatedMin(final Date updatedMin) {
    return appendQueryParam("updatedMin", updatedMin);
  }
}
