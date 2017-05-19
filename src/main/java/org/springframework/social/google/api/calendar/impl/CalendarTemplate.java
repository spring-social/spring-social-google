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

import org.springframework.social.google.api.calendar.Calendar;
import org.springframework.social.google.api.calendar.CalendarListQueryBuilder;
import org.springframework.social.google.api.calendar.CalendarOperations;
import org.springframework.social.google.api.calendar.CalendarPage;
import org.springframework.social.google.api.calendar.DeleteEventBuilder;
import org.springframework.social.google.api.calendar.Event;
import org.springframework.social.google.api.calendar.EventListQueryBuilder;
import org.springframework.social.google.api.calendar.EventPage;
import org.springframework.social.google.api.calendar.QuickAddEventBuilder;
import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * {@link CalendarOperations} implementation.
 *
 * @author Martin Wink
 */
public class CalendarTemplate extends AbstractGoogleApiOperations implements CalendarOperations {

  private static final String CALENDAR_BASE_URL = "https://www.googleapis.com/calendar/v3";

  public CalendarTemplate(final RestTemplate restTemplate, final boolean isAuthorized) {
    super(restTemplate, isAuthorized);
    Assert.notNull(restTemplate, "RestTemplate must not be null");
  }

  @Override
  public CalendarListQueryBuilder calendarListQuery() {
    return new CalendarListQueryBuilderImpl(CALENDAR_BASE_URL + "/users/me/calendarList", CalendarPage.class, restTemplate);
  }

  @Override
  public EventListQueryBuilder eventListQuery(final String calendarId) {
    Assert.notNull(calendarId, "CalendarId must not be null");
    return new EventListQueryBuilderImpl(CALENDAR_BASE_URL + "/calendars/{0}/events", calendarId, EventPage.class, restTemplate);
  }

  @Override
  public Calendar getCalendar(final String calendarId) {
    Assert.notNull(calendarId, "CalendarId must not be null");
    final CalendarGetQueryBuilderImpl builder = new CalendarGetQueryBuilderImpl(CALENDAR_BASE_URL + "/users/me/calendarList/{0}", calendarId);
    return restTemplate.getForObject(builder.buildUri(), Calendar.class);
  }

  @Override
  public Event getEvent(final String calendarId, final String eventId) {
    Assert.notNull(calendarId, "CalendarId must not be null");
    Assert.notNull(eventId, "EventId must not be null");
    final EventGetQueryBuilderImpl builder = new EventGetQueryBuilderImpl(CALENDAR_BASE_URL + "/calendars/{0}/events/{1}", calendarId, eventId);
    return restTemplate.getForObject(builder.buildUri(), Event.class);
  }

  @Override
  public Event quickAddEvent(final String calendarId, final String specification, final boolean sendNotifications) {
    Assert.notNull(calendarId, "CalendarId must not be null");
    Assert.notNull(specification, "Specification must not be null");
    final QuickAddEventBuilder builder = new QuickAddEventBuilderImpl(CALENDAR_BASE_URL + "/calendars/{0}/events/quickAdd", calendarId);
    builder.text(specification).sendNotifications(sendNotifications);
    return restTemplate.postForObject(builder.buildUri(), null, Event.class);
  }

  @Override
  public void deleteEvent(final String calendarId, final String eventId, final boolean sendNotifications) {
    Assert.notNull(calendarId, "CalendarId must not be null");
    Assert.notNull(eventId, "EventId must not be null");
    final DeleteEventBuilder builder = new DeleteEventBuilderImpl(CALENDAR_BASE_URL + "/calendars/{0}/events/{1}", calendarId, eventId);
    builder.sendNotifications(sendNotifications);
    restTemplate.delete(builder.buildUri());
  }

  @Override
  public void updateEvent(final String calendarId, final Event event, final boolean sendNotifications) {
    Assert.notNull(calendarId, "CalendarId must not be null");
    Assert.notNull(event, "Event must not be null");
    final UpdateEventBuilderImpl builder = new UpdateEventBuilderImpl(CALENDAR_BASE_URL + "/calendars/{0}/events/{1}", calendarId, event.getId());
    builder.sendNotifications(sendNotifications);
    restTemplate.put(builder.buildUri(), event);
  }
}
