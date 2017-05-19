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

/**
 * Interface defining operations for integrating with Google Calendar.
 *
 * See the reference documentation at
 * <a href="http://developers.google.com/google-apps/calendar/v3/reference/">
 * http://developers.google.com/google-apps/calendar/v3/reference/</a>.
 * <br>
 * Requires one of the following OAuth scope(s):
 *   <ul>
 *      <li>https://www.googleapis.com/auth/calendar.readonly</li>
 *      <li>https://www.googleapis.com/auth/calendar</li>
 *  </ul>
 * See
 * <a href="http://developers.google.com/google-apps/calendar/auth">http://developers.google.com/google-apps/calendar/auth</a>
 * for details about the different scopes.
 *
 *
 * @author Martin Wink
 */
public interface CalendarOperations {
  /**
   * The identifier for the user's primary calendar.
   */
  public static final String PRIMARY_CALENDAR_ID = "primary";

  /**
   * Create a builder pattern object for refining a query that lists the calendars
   * that are accessible to the authenticated user.
   * @return a query builder for listing calendars.
   */
  CalendarListQueryBuilder calendarListQuery();

  /** Fetch a calendar from the user's calendar list.
   * @param calendarId the calendar's identifier. The special identifier "primary"
   * ({@link #PRIMARY_CALENDAR_ID}) may be used.
   * @return the calendar.
   */
  Calendar getCalendar(String calendarId);

  /**
   * Create a builder pattern object for refining a query that lists the events that
   * are accessible to the authenticated user.
   * @param calendarId the calendar's identifier. The special identifier "primary"
   * ({@link #PRIMARY_CALENDAR_ID}) may be used.
   * @return a query builder for listing events.
   */
  EventListQueryBuilder eventListQuery(String calendarId);

  /**
   * Fetch an event from the specified calendar.
   * @param calendarId the calendar's identifier. The special identifier "primary"
   * ({@link #PRIMARY_CALENDAR_ID}) may be used.
   * @param eventId the event's identifier.
   * @return the event.
   */
  Event getEvent(String calendarId, String eventId);

  /**
   * Create a new event in the specified calendar, based on the specification text.
   * @param calendarId the calendar's identifier. The special identifier "primary"
   * ({@link #PRIMARY_CALENDAR_ID}) may be used.
   * @param specification text describing the event to be created.
   * @param sendNotifications whether to send notifications about the creation of the event.
   * @return the new event.
   */
  Event quickAddEvent(String calendarId, String specification, boolean sendNotifications);

  /**
   * Delete an existing event.
   * @param calendarId the identifier of the calendar containing the event. The special
   * identifier "primary" ({@link #PRIMARY_CALENDAR_ID}) may be used.
   * @param eventId the event's identifier.
   * @param sendNotifications whether to send notifications about the deletion of the event.
   */
  void deleteEvent(String calendarId, String eventId, boolean sendNotifications);

  /**
   * Update an existing event.
   * @param calendarId The identifier of the calendar containing the event.
   * The special identifier "primary" ({@link #PRIMARY_CALENDAR_ID}) may be used.
   * @param event The event to update.
   * @param sendNotifications Whether to send notifications about the update to the event.
   */
  void updateEvent(String calendarId, Event event, boolean sendNotifications);
}
