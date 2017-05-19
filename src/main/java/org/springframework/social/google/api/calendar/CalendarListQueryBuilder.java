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
package org.springframework.social.google.api.calendar;

import org.springframework.social.google.api.query.ApiQueryBuilder;

/**
 * Constructs queries for listing the accessible calendars, following the builder pattern.
 * The {@link #getPage()} method returns a {@link CalendarPage} containing
 * {@link Calendar} instances.
 * <p>See the reference documentation at
 * <a href="http://developers.google.com/google-apps/calendar/v3/reference/calendarList/list">
 * http://developers.google.com/google-apps/calendar/v3/reference/calendarList/list</a>
 * for details of the parameters.
 * </p>
 *
 * @author Martin Wink
 */
public interface CalendarListQueryBuilder extends ApiQueryBuilder<CalendarListQueryBuilder, CalendarPage> {
  /**
   * The minimum access role for the user in the returned entries. Optional.
   * The default is no restriction.
   * @param minAccessRole the minimum access role for the user in the returned entries.
   * @return this {@code CalendarListQueryBuilder}, for refining the query.
   */
  CalendarListQueryBuilder minAccessRole(PermissionRole minAccessRole);

  /**
   * Whether to include deleted calendar list entries in the result. Optional.
   * The default is false.
   * @param showDeleted whether to include deleted calendar list entries in the result.
   * @return this {@code CalendarListQueryBuilder}, for refining the query.
   */
  CalendarListQueryBuilder showDeleted(boolean showDeleted);

  /**
   * Whether to show hidden entries. Optional. The default is false.
   * @param showHidden whether to show hidden entries.
   * @return this {@code CalendarListQueryBuilder}, for refining the query.
   */
  CalendarListQueryBuilder showHidden(boolean showHidden);
}
