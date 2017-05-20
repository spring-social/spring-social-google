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

import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

public class CalendarTemplate_EventUrlTests extends AbstractGoogleApiTest {

  private static final TimeZone TEST_TIMEZONE = TimeZone.getTimeZone("GMT");
  private static final Date TEST_TIME_MAX = makeDate(2014, 1, 1);
  private static final Date TEST_TIME_MIN = makeDate(2014, 2, 1);
  private static final Date TEST_UPDATED_MIN = makeDate(2014, 3, 1);

  private static Date makeDate(final int y, final int m, final int d) {
    final java.util.Calendar cal = java.util.Calendar.getInstance(TEST_TIMEZONE);
    cal.setTimeInMillis(0);
    cal.set(y, m - 1, d);
    return cal.getTime();
  }

  @Test
  public void listEvents_primary_no_options() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_fromPage() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?pageToken=CigKGnFiOGdnNWZkZDZkNXRwZGNyNW9kNDRhaHBvGAEggICAnbfk_5AUGg0IABIAGNiisr2F9sEC&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      // A typical page token.
      .fromPage("CigKGnFiOGdnNWZkZDZkNXRwZGNyNW9kNDRhaHBvGAEggICAnbfk_5AUGg0IABIAGNiisr2F9sEC")
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_fromPage_escaping() throws UnsupportedEncodingException {

    final String pageToken = "abc123_¬!£$%^&*()_+-=[]{};'#:@~,./<>?";

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?pageToken=" + encodeUTF8(pageToken) + "&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .fromPage(pageToken)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_alwaysIncludeEmail_true() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?alwaysIncludeEmail=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .alwaysIncludeEmail(true)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_alwaysIncludeEmail_false() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .alwaysIncludeEmail(false)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_iCalUID() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?iCalUid=testiCalUID&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .iCalUid("testiCalUID")
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_maxAttendees() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?maxAttendees=11&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .maxAttendees(11)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_maxResults() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?maxResults=100&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .maxResultsNumber(100)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_orderBy_START_TIME() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?orderBy=startTime&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .orderBy(OrderBy.START_TIME)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_orderBy_UPDATED() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?orderBy=updated&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .orderBy(OrderBy.UPDATED)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_showDeleted_true() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?showDeleted=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .showDeleted(true)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_showDeleted_false() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .showDeleted(false)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_showHiddenInvitations_true() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?showHiddenInvitations=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .showHiddenInvitations(true)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_showHiddenInvitations_false() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .showHiddenInvitations(false)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_singleEvents_true() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?singleEvents=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .singleEvents(true)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_singleEvents_false() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .singleEvents(false)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_timeMin_Date() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?timeMin=2014-02-01T00:00:00.000Z&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .timeMin(TEST_TIME_MIN)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_timeMin_YMD() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?timeMin=2013-02-01T00:00:00.000Z&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .timeMin(2013, 2, 1)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_timeMax_Date() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?timeMax=2014-01-01T00:00:00.000Z&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .timeMax(TEST_TIME_MAX)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_timeMax_YMD() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?timeMax=2013-01-01T00:00:00.000Z&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .timeMax(2013, 1, 1)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_timeZone() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?timeZone=GMT&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .timeZone(TEST_TIMEZONE)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_updatedMin() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?updatedMin=2014-03-01T00:00:00.000Z&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .updatedMin(TEST_UPDATED_MIN)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_primary_combined_options() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?alwaysIncludeEmail=true&orderBy=startTime&timeZone=GMT&pageToken=pretendPageToken&singleEvents=true&showHiddenInvitations=true&maxResults=50&maxAttendees=9&timeMin=2014-02-01T00:00:00.000Z&iCalUid=test-iCalUid&showDeleted=true&timeMax=2014-01-01T00:00:00.000Z&updatedMin=2014-03-01T00:00:00.000Z&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .alwaysIncludeEmail(true)
      .orderBy(OrderBy.START_TIME)
      .timeZone(TEST_TIMEZONE)
      .fromPage("pretendPageToken")
      .singleEvents(true)
      .showHiddenInvitations(true)
      .maxResultsNumber(50)
      .maxAttendees(9)
      .timeMin(TEST_TIME_MIN)
      .iCalUid("test-iCalUid")
      .showDeleted(true)
      .timeMax(TEST_TIME_MAX)
      .updatedMin(TEST_UPDATED_MIN)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_escape_calendarId() throws UnsupportedEncodingException {

    final String calendarId = "abc123!\"£$%^&*()_+-=[]{};'#:@~,./<>?";

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/" + encodeUTF8(calendarId) + "/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_get_event"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(calendarId)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void listEvents_escape_pageToken() throws UnsupportedEncodingException {

    final String calendarId = "abc123!\"£$%^&*()_+-=[]{};'#:@~,./<>?";
    final String pageToken = "abc123!\"£$%^&*()_+-=[]{};'#:@~,./<>?";

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/" + encodeUTF8(calendarId) + "/events?pageToken=" + encodeUTF8(pageToken) + "&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_empty"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(calendarId)
      .fromPage(pageToken)
      .getPage();

    assertNotNull(eventPage);
  }

  @Test
  public void getEvent_primary() {

    final String id = "_60q30c1g60o30e1i60o4ac1g60rj8gpl88rj2c1h84s34h9g60s30c1g60o30c1g6oo4agph751kah9o84r46ghg64o30c1g60o30c1g60o30c1g60o32c1g60o30c1g891jeh9o88qk6cpk612k6c1k6ss3idi488o36h1i8p1k6hhp6oog";

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events/_60q30c1g60o30e1i60o4ac1g60rj8gpl88rj2c1h84s34h9g60s30c1g60o30c1g6oo4agph751kah9o84r46ghg64o30c1g60o30c1g60o30c1g60o32c1g60o30c1g891jeh9o88qk6cpk612k6c1k6ss3idi488o36h1i8p1k6hhp6oog?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_get_event"), APPLICATION_JSON));

    final Event event = google.calendarOperations().getEvent(CalendarOperations.PRIMARY_CALENDAR_ID, id);

    assertNotNull(event);
  }

  @Test
  public void getEvent_escape_calendarId() throws UnsupportedEncodingException {

    final String calendarId = "abc123!\"£$%^&*()_+-=[]{};'#:@~,./<>?";
    final String eventId = "abc123!\"£$%^&*()_+-=[]{};'#:@~,./<>?";

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/" + encodeUTF8(calendarId) + "/events/" + encodeUTF8(eventId) + "?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_get_event"), APPLICATION_JSON));

    final Event event = google.calendarOperations().getEvent(calendarId, eventId);

    assertNotNull(event);
  }

  @Test
  public void quickAddEvent() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events/quickAdd?text=Appointment+at+Somewhere+every+June+3rd+10am-10%3A25am&sendNotifications=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(POST))
      .andRespond(
        withSuccess(jsonResource("mock_added_event"), APPLICATION_JSON));

    final Event event = google.calendarOperations().quickAddEvent("primary", "Appointment at Somewhere every June 3rd 10am-10:25am", true);

    assertNotNull(event);
  }

  @Test
  public void deleteEvent() {

    final String id = "_60q30c1g60o30e1i60o4ac1g60rj8gpl88rj2c1h84s34h9g60s30c1g60o30c1g6oo4agph751kah9o84r46ghg64o30c1g60o30c1g60o30c1g60o32c1g60o30c1g891jeh9o88qk6cpk612k6c1k6ss3idi488o36h1i8p1k6hhp6oog";

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events/_60q30c1g60o30e1i60o4ac1g60rj8gpl88rj2c1h84s34h9g60s30c1g60o30c1g6oo4agph751kah9o84r46ghg64o30c1g60o30c1g60o30c1g60o32c1g60o30c1g891jeh9o88qk6cpk612k6c1k6ss3idi488o36h1i8p1k6hhp6oog?sendNotifications=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(DELETE))
      .andRespond(
        withSuccess());

    google.calendarOperations().deleteEvent("primary", id, true);
  }

  @Test
  public void updateEvent() throws IOException {

    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  // kind etc not used in Event.
    final Event event = mapper.readValue(jsonResource("mock_added_event").getFile(), Event.class);

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events/onn6d9kbhps6fc47m96auhtkdo?sendNotifications=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(PUT))
      .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))  // compatible with because ";charset=UTF-8" is included.
      .andRespond(
        withSuccess());

    google.calendarOperations().updateEvent("primary", event, true);
  }
}
