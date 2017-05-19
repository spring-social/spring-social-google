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
package org.springframework.social.google.api.calendar.impl;

import org.springframework.social.google.api.calendar.CalendarListQueryBuilder;
import org.springframework.social.google.api.calendar.CalendarPage;
import org.springframework.social.google.api.calendar.PermissionRole;
import org.springframework.social.google.api.impl.ApiEnumSerializer;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link CalendarListQueryBuilder} implementation.
 *
 * @author Martin Wink
 */
public class CalendarListQueryBuilderImpl extends ApiQueryBuilderImpl<CalendarListQueryBuilder, CalendarPage> implements CalendarListQueryBuilder {

  public CalendarListQueryBuilderImpl(final String feedUrl, final Class<CalendarPage> type, final RestTemplate restTemplate) {
    super(feedUrl, type, restTemplate);
  }

  @Override
  public CalendarListQueryBuilder showDeleted(final boolean showDeleted) {
    return appendQueryParam("showDeleted", showDeleted);
  }

  @Override
  public CalendarListQueryBuilder showHidden(final boolean showHidden) {
    return appendQueryParam("showHidden", showHidden);
  }

  @Override
  public CalendarListQueryBuilder minAccessRole(final PermissionRole minAccessRole) {
    return appendQueryParam("minAccessRole", ApiEnumSerializer.enumToString(minAccessRole));
  }
}
