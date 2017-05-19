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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;
import org.springframework.social.google.api.calendar.Event.Attendee;
import org.springframework.social.google.api.calendar.Event.Creator;
import org.springframework.social.google.api.calendar.Event.DateTimeTimezone;
import org.springframework.social.google.api.calendar.Event.ExtendedProperties;
import org.springframework.social.google.api.calendar.Event.Gadget;
import org.springframework.social.google.api.calendar.Event.Organizer;
import org.springframework.social.google.api.calendar.Event.ReminderOverride;
import org.springframework.social.google.api.calendar.Event.Reminders;
import org.springframework.social.google.api.calendar.Event.Source;

/**
 * Tests to verify that the JSON responses are correctly parsed for each of the requests.
 * @author Martin Wink
 */
public class CalendarTemplate_EventJsonTests extends AbstractGoogleApiTest {

  @Test
  public void listEvents() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"1415499182398000\"", eventPage.getEtag());
  }

  @Test
  public void listEvents_missing() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_missing"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNull(eventPage.getItems());
  }

  @Test
  public void listEvents_empty() {

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_empty"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(0, eventPage.getItems().size());
  }

  @Test
  public void listEvents_values_1() {
// Fully populate the JSON first.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_1"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("string1", event.getId());
    assertEquals(EventStatus.CONFIRMED, event.getStatus());
    assertEquals("string3", event.getHtmlLink());
    assertEquals(DateUtils.makeDate(2000, 1, 1), event.getCreated());
    assertEquals(DateUtils.makeDate(2000, 1, 1), event.getUpdated());

    assertEquals("string4", event.getSummary());
    assertEquals("string5", event.getDescription());
    assertEquals("string6", event.getLocation());
    assertEquals("string7", event.getColorId());
    assertEquals("string17", event.getRecurringEventId());
    assertEquals(Transparency.OPAQUE, event.getTransparency());
    assertEquals(Visibility.DEFAULT, event.getVisibility());
    assertEquals("string21", event.getiCalUID());
    assertEquals(1, event.getSequence().intValue());
    assertEquals("string29", event.getHangoutLink());

    assertEquals(true, event.isEndTimeUnspecified());
    assertEquals(true, event.isAttendeesOmitted());
    assertEquals(true, event.isAnyoneCanAddSelf());
    assertEquals(true, event.isGuestsCanInviteOthers());
    assertEquals(true, event.isGuestsCanModify());
    assertEquals(true, event.isGuestsCanSeeOtherGuests());
    assertEquals(true, event.isPrivateCopy());
    assertEquals(true, event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNotNull(creator);
      assertEquals("string8", creator.getId());
      assertEquals("string9", creator.getEmail());
      assertEquals("string10", creator.getDisplayName());
      assertEquals(true, creator.isSelf());
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNotNull(organizer);
      assertEquals("string11", organizer.getId());
      assertEquals("string12", organizer.getEmail());
      assertEquals("string13", organizer.getDisplayName());
      assertEquals(true, organizer.isSelf());
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNotNull(dtz);
      assertEquals(DateUtils.makeDate(2000, 1, 1), dtz.getDate());
      assertEquals(DateUtils.makeDate(2000, 1, 1), dtz.getDateTime());
      assertEquals(DateUtils.TEST_TIMEZONE, dtz.getTimeZone());
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNotNull(dtz);
      assertEquals(DateUtils.makeDate(2000, 1, 1), dtz.getDate());
      assertEquals(DateUtils.makeDate(2000, 1, 1), dtz.getDateTime());
      assertEquals(DateUtils.TEST_TIMEZONE, dtz.getTimeZone());
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNotNull(recurrence);
      assertEquals(1, recurrence.size());
      assertEquals("string16", recurrence.get(0));
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNotNull(dtz);
      assertEquals(DateUtils.makeDate(2000, 1, 1), dtz.getDate());
      assertEquals(DateUtils.makeDate(2000, 1, 1), dtz.getDateTime());
      assertEquals(DateUtils.TEST_TIMEZONE, dtz.getTimeZone());
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNotNull(attendees);
      assertEquals(1, attendees.size());

      final Attendee att = attendees.get(0);
      assertEquals("string22", att.getId());
      assertEquals("string23", att.getEmail());
      assertEquals("string24", att.getDisplayName());
      assertEquals(true, att.isOrganizer());
      assertEquals(true, att.isSelf());
      assertEquals(true, att.isResource());
      assertEquals(true, att.isOptional());
      assertEquals(AttendeeStatus.NEEDS_ACTION, att.getResponseStatus());
      assertEquals("string26", att.getComment());
      assertEquals(1, att.getAdditionalGuests().intValue());
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNotNull(extendedProperties);
      {
        final Map<String, String> m = extendedProperties.getPrivateProperties();
        assertNotNull(m);
        assertEquals(1, m.size());
        assertEquals("string27", m.get("(key1)"));
      }
      {
        final Map<String, String> m = extendedProperties.getSharedProperties();
        assertNotNull(m);
        assertEquals(1, m.size());
        assertEquals("string28", m.get("(key2)"));
      }
    }

    {
      final Gadget gadget = event.getGadget();
      assertNotNull(gadget);
      assertEquals("string30", gadget.getType());
      assertEquals("string31", gadget.getTitle());
      assertEquals("string32", gadget.getLink());
      assertEquals("string33", gadget.getIconLink());
      assertEquals(11, gadget.getWidth().intValue());
      assertEquals(11, gadget.getHeight().intValue());
      assertEquals(DisplayMode.ICON, gadget.getDisplay());

      final Map<String, String> m = gadget.getPreferences();
      assertNotNull(m);
      assertEquals(1, m.size());
      assertEquals("string35", m.get("(key3)"));
    }

    {
      final Reminders reminders = event.getReminders();
      assertNotNull(reminders);
      assertEquals(true, reminders.isUseDefault());

      final List<ReminderOverride> overrides = reminders.getOverrides();
      assertNotNull(overrides);
      assertEquals(1, overrides.size());
      final ReminderOverride ro = overrides.get(0);
      assertNotNull(ro);
      assertEquals(NotificationMethod.EMAIL, ro.getMethod());
      assertEquals(12, ro.getMinutes().intValue());
    }

    {
      final Source source = event.getSource();
      assertNotNull(source);
      assertEquals("string37", source.getUrl());
      assertEquals("string38", source.getTitle());
    }
  }

  @Test
  public void listEvents_values_2() {
// Swap the values for the next in sequence - booleans swap, enums on to next, strings to empty, numbers to zero.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_2"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("", event.getId());
    assertEquals(EventStatus.TENTATIVE, event.getStatus());
    assertEquals("", event.getHtmlLink());
    assertEquals(DateUtils.makeDate(1970, 1, 1), event.getCreated());
    assertEquals(DateUtils.makeDate(1970, 1, 1), event.getUpdated());

    assertEquals("", event.getSummary());
    assertEquals("", event.getDescription());
    assertEquals("", event.getLocation());
    assertEquals("", event.getColorId());
    assertEquals("", event.getRecurringEventId());
    assertEquals(Transparency.TRANSPARENT, event.getTransparency());
    assertEquals(Visibility.PUBLIC, event.getVisibility());
    assertEquals("", event.getiCalUID());
    assertEquals(0, event.getSequence().intValue());
    assertEquals("", event.getHangoutLink());

    assertEquals(false, event.isEndTimeUnspecified());
    assertEquals(false, event.isAttendeesOmitted());
    assertEquals(false, event.isAnyoneCanAddSelf());
    assertEquals(false, event.isGuestsCanInviteOthers());
    assertEquals(false, event.isGuestsCanModify());
    assertEquals(false, event.isGuestsCanSeeOtherGuests());
    assertEquals(false, event.isPrivateCopy());
    assertEquals(false, event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNotNull(creator);
      assertEquals("", creator.getId());
      assertEquals("", creator.getEmail());
      assertEquals("", creator.getDisplayName());
      assertEquals(false, creator.isSelf());
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNotNull(organizer);
      assertEquals("", organizer.getId());
      assertEquals("", organizer.getEmail());
      assertEquals("", organizer.getDisplayName());
      assertEquals(false, organizer.isSelf());
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNotNull(dtz);
      assertEquals(DateUtils.makeDate(1970, 1, 1), dtz.getDate());
      assertEquals(DateUtils.makeDate(1970, 1, 1), dtz.getDateTime());
      assertEquals(DateUtils.TEST_TIMEZONE, dtz.getTimeZone());
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNotNull(dtz);
      assertEquals(DateUtils.makeDate(1970, 1, 1), dtz.getDate());
      assertEquals(DateUtils.makeDate(1970, 1, 1), dtz.getDateTime());
      assertEquals(DateUtils.TEST_TIMEZONE, dtz.getTimeZone());
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNotNull(recurrence);
      assertEquals(1, recurrence.size());
      assertEquals("", recurrence.get(0));
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNotNull(dtz);
      assertEquals(DateUtils.makeDate(1970, 1, 1), dtz.getDate());
      assertEquals(DateUtils.makeDate(1970, 1, 1), dtz.getDateTime());
      assertEquals(DateUtils.TEST_TIMEZONE, dtz.getTimeZone());
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNotNull(attendees);
      assertEquals(1, attendees.size());

      final Attendee att = attendees.get(0);
      assertEquals("", att.getId());
      assertEquals("", att.getEmail());
      assertEquals("", att.getDisplayName());
      assertEquals(false, att.isOrganizer());
      assertEquals(false, att.isSelf());
      assertEquals(false, att.isResource());
      assertEquals(false, att.isOptional());
      assertEquals(AttendeeStatus.DECLINED, att.getResponseStatus());
      assertEquals("", att.getComment());
      assertEquals(0, att.getAdditionalGuests().intValue());
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNotNull(extendedProperties);
      {
        final Map<String, String> m = extendedProperties.getPrivateProperties();
        assertNotNull(m);
        assertEquals(1, m.size());
        assertEquals("", m.get("(key1)"));
      }
      {
        final Map<String, String> m = extendedProperties.getSharedProperties();
        assertNotNull(m);
        assertEquals(1, m.size());
        assertEquals("", m.get("(key2)"));
      }
    }

    {
      final Gadget gadget = event.getGadget();
      assertNotNull(gadget);
      assertEquals("", gadget.getType());
      assertEquals("", gadget.getTitle());
      assertEquals("", gadget.getLink());
      assertEquals("", gadget.getIconLink());
      assertEquals(0, gadget.getWidth().intValue());
      assertEquals(0, gadget.getHeight().intValue());
      assertEquals(DisplayMode.CHIP, gadget.getDisplay());

      final Map<String, String> m = gadget.getPreferences();
      assertNotNull(m);
      assertEquals(1, m.size());
      assertEquals("", m.get("(key3)"));
    }

    {
      final Reminders reminders = event.getReminders();
      assertNotNull(reminders);
      assertEquals(false, reminders.isUseDefault());

      final List<ReminderOverride> overrides = reminders.getOverrides();
      assertNotNull(overrides);
      assertEquals(1, overrides.size());
      final ReminderOverride ro = overrides.get(0);
      assertNotNull(ro);
      assertEquals(NotificationMethod.SMS, ro.getMethod());
      assertEquals(0, ro.getMinutes().intValue());
    }

    {
      final Source source = event.getSource();
      assertNotNull(source);
      assertEquals("", source.getUrl());
      assertEquals("", source.getTitle());
    }
  }

  @Test
  public void listEvents_values_3() {
// Swap the values for the next in sequence - booleans swap, enums on to next, strings to empty, numbers to zero.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_3"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("", event.getId());
    assertEquals(EventStatus.CANCELLED, event.getStatus());
    assertNull(event.getHtmlLink());
    assertNull(event.getCreated());
    assertNull(event.getUpdated());

    assertNull(event.getSummary());
    assertNull(event.getDescription());
    assertNull(event.getLocation());
    assertNull(event.getColorId());
    assertNull(event.getRecurringEventId());
    assertNull(event.getTransparency());
    assertEquals(Visibility.PRIVATE, event.getVisibility());
    assertNull(event.getiCalUID());
    assertNull(event.getSequence());
    assertNull(event.getHangoutLink());

    assertNull(event.isEndTimeUnspecified());
    assertNull(event.isAttendeesOmitted());
    assertNull(event.isAnyoneCanAddSelf());
    assertTrue(event.isGuestsCanInviteOthers());
    assertNull(event.isGuestsCanModify());
    assertTrue(event.isGuestsCanSeeOtherGuests());
    assertNull(event.isPrivateCopy());
    assertNull(event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNotNull(creator);
      assertNull(creator.getId());
      assertNull(creator.getEmail());
      assertNull(creator.getDisplayName());
      assertNull(creator.isSelf());
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNotNull(organizer);
      assertNull(organizer.getId());
      assertNull(organizer.getEmail());
      assertNull(organizer.getDisplayName());
      assertNull(organizer.isSelf());
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNotNull(dtz);
      assertNull(dtz.getDate());
      assertNull(dtz.getDateTime());
      assertNull(dtz.getTimeZone());
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNotNull(dtz);
      assertNull(dtz.getDate());
      assertNull(dtz.getDateTime());
      assertNull(dtz.getTimeZone());
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNotNull(recurrence);
      assertEquals(0, recurrence.size());
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNotNull(dtz);
      assertNull(dtz.getDate());
      assertNull(dtz.getDateTime());
      assertNull(dtz.getTimeZone());
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNotNull(attendees);
      assertEquals(1, attendees.size());

      final Attendee att = attendees.get(0);
      assertNull(att.getId());
      assertNull(att.getEmail());
      assertNull(att.getDisplayName());
      assertNull(att.isOrganizer());
      assertNull(att.isSelf());
      assertNull(att.isResource());
      assertNull(att.isOptional());
      assertEquals(AttendeeStatus.TENTATIVE, att.getResponseStatus());
      assertNull(att.getComment());
      assertNull(att.getAdditionalGuests());
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNotNull(extendedProperties);
      {
        final Map<String, String> m = extendedProperties.getPrivateProperties();
        assertNotNull(m);
        assertEquals(0, m.size());
      }
      {
        final Map<String, String> m = extendedProperties.getSharedProperties();
        assertNotNull(m);
        assertEquals(0, m.size());
      }
    }

    {
      final Gadget gadget = event.getGadget();
      assertNotNull(gadget);
      assertNull(gadget.getType());
      assertNull(gadget.getTitle());
      assertNull(gadget.getLink());
      assertNull(gadget.getIconLink());
      assertNull(gadget.getWidth());
      assertNull(gadget.getHeight());
      assertNull(gadget.getDisplay());

      final Map<String, String> m = gadget.getPreferences();
      assertNotNull(m);
      assertEquals(0, m.size());
    }

    {
      final Reminders reminders = event.getReminders();
      assertNotNull(reminders);
      assertNull(reminders.isUseDefault());

      final List<ReminderOverride> overrides = reminders.getOverrides();
      assertNotNull(overrides);
      assertEquals(1, overrides.size());
      final ReminderOverride ro = overrides.get(0);
      assertNotNull(ro);
      assertEquals(NotificationMethod.POPUP, ro.getMethod());
      assertNull(ro.getMinutes());
    }

    {
      final Source source = event.getSource();
      assertNotNull(source);
      assertNull(source.getUrl());
      assertNull(source.getTitle());
    }
  }

  @Test
  public void listEvents_values_4() {
// Swap the values for the next in sequence - booleans swap, enums on to next, strings to empty, numbers to zero.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_4"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("", event.getId());
    assertNull(event.getStatus());
    assertNull(event.getHtmlLink());
    assertNull(event.getCreated());
    assertNull(event.getUpdated());

    assertNull(event.getSummary());
    assertNull(event.getDescription());
    assertNull(event.getLocation());
    assertNull(event.getColorId());
    assertNull(event.getRecurringEventId());
    assertNull(event.getTransparency());
    assertEquals(Visibility.CONFIDENTIAL, event.getVisibility());
    assertNull(event.getiCalUID());
    assertNull(event.getSequence());
    assertNull(event.getHangoutLink());

    assertNull(event.isEndTimeUnspecified());
    assertNull(event.isAttendeesOmitted());
    assertNull(event.isAnyoneCanAddSelf());
    assertTrue(event.isGuestsCanInviteOthers());
    assertNull(event.isGuestsCanModify());
    assertTrue(event.isGuestsCanSeeOtherGuests());
    assertNull(event.isPrivateCopy());
    assertNull(event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNull(creator);
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNull(organizer);
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNotNull(dtz);
      assertNull(dtz.getDate());
      assertNull(dtz.getDateTime());
      assertNull(dtz.getTimeZone());
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNotNull(dtz);
      assertNull(dtz.getDate());
      assertNull(dtz.getDateTime());
      assertNull(dtz.getTimeZone());
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNull(recurrence);
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNotNull(dtz);
      assertNull(dtz.getDate());
      assertNull(dtz.getDateTime());
      assertNull(dtz.getTimeZone());
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNotNull(attendees);
      assertEquals(1, attendees.size());

      final Attendee att = attendees.get(0);
      assertNull(att.getId());
      assertNull(att.getEmail());
      assertNull(att.getDisplayName());
      assertNull(att.isOrganizer());
      assertNull(att.isSelf());
      assertNull(att.isResource());
      assertNull(att.isOptional());
      assertEquals(AttendeeStatus.ACCEPTED, att.getResponseStatus());
      assertNull(att.getComment());
      assertNull(att.getAdditionalGuests());
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNotNull(extendedProperties);
      {
        final Map<String, String> m = extendedProperties.getPrivateProperties();
        assertNull(m);
      }
      {
        final Map<String, String> m = extendedProperties.getSharedProperties();
        assertNull(m);
      }
    }

    {
      final Gadget gadget = event.getGadget();
      assertNotNull(gadget);
      assertNull(gadget.getType());
      assertNull(gadget.getTitle());
      assertNull(gadget.getLink());
      assertNull(gadget.getIconLink());
      assertNull(gadget.getWidth());
      assertNull(gadget.getHeight());
      assertNull(gadget.getDisplay());

      final Map<String, String> m = gadget.getPreferences();
      assertNull(m);
    }

    {
      final Reminders reminders = event.getReminders();
      assertNotNull(reminders);
      assertNull(reminders.isUseDefault());

      final List<ReminderOverride> overrides = reminders.getOverrides();
      assertNotNull(overrides);
      assertEquals(1, overrides.size());
      final ReminderOverride ro = overrides.get(0);
      assertNotNull(ro);
      assertNull(ro.getMethod());
      assertNull(ro.getMinutes());
    }

    {
      final Source source = event.getSource();
      assertNull(source);
    }
  }

  @Test
  public void listEvents_values_5() {
// Swap the values for the next in sequence - booleans swap, enums on to next, strings to empty, numbers to zero.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_5"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("", event.getId());
    assertNull(event.getStatus());
    assertNull(event.getHtmlLink());
    assertNull(event.getCreated());
    assertNull(event.getUpdated());

    assertNull(event.getSummary());
    assertNull(event.getDescription());
    assertNull(event.getLocation());
    assertNull(event.getColorId());
    assertNull(event.getRecurringEventId());
    assertNull(event.getTransparency());
    assertNull(event.getVisibility());
    assertNull(event.getiCalUID());
    assertNull(event.getSequence());
    assertNull(event.getHangoutLink());

    assertNull(event.isEndTimeUnspecified());
    assertNull(event.isAttendeesOmitted());
    assertNull(event.isAnyoneCanAddSelf());
    assertTrue(event.isGuestsCanInviteOthers());
    assertNull(event.isGuestsCanModify());
    assertTrue(event.isGuestsCanSeeOtherGuests());
    assertNull(event.isPrivateCopy());
    assertNull(event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNull(creator);
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNull(organizer);
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNull(dtz);
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNull(dtz);
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNull(recurrence);
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNull(dtz);
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNotNull(attendees);
      assertEquals(1, attendees.size());

      final Attendee att = attendees.get(0);
      assertNull(att.getId());
      assertNull(att.getEmail());
      assertNull(att.getDisplayName());
      assertNull(att.isOrganizer());
      assertNull(att.isSelf());
      assertNull(att.isResource());
      assertNull(att.isOptional());
      assertNull(att.getResponseStatus());
      assertNull(att.getComment());
      assertNull(att.getAdditionalGuests());
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNull(extendedProperties);
    }

    {
      final Gadget gadget = event.getGadget();
      assertNull(gadget);
    }

    {
      final Reminders reminders = event.getReminders();
      assertNotNull(reminders);
      assertNull(reminders.isUseDefault());

      final List<ReminderOverride> overrides = reminders.getOverrides();
      assertNotNull(overrides);
      assertEquals(0, overrides.size());
    }

    {
      final Source source = event.getSource();
      assertNull(source);
    }
  }

  @Test
  public void listEvents_values_6() {
// Swap the values for the next in sequence - booleans swap, enums on to next, strings to empty, numbers to zero.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_6"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("", event.getId());
    assertNull(event.getStatus());
    assertNull(event.getHtmlLink());
    assertNull(event.getCreated());
    assertNull(event.getUpdated());

    assertNull(event.getSummary());
    assertNull(event.getDescription());
    assertNull(event.getLocation());
    assertNull(event.getColorId());
    assertNull(event.getRecurringEventId());
    assertNull(event.getTransparency());
    assertNull(event.getVisibility());
    assertNull(event.getiCalUID());
    assertNull(event.getSequence());
    assertNull(event.getHangoutLink());

    assertNull(event.isEndTimeUnspecified());
    assertNull(event.isAttendeesOmitted());
    assertNull(event.isAnyoneCanAddSelf());
    assertTrue(event.isGuestsCanInviteOthers());
    assertNull(event.isGuestsCanModify());
    assertTrue(event.isGuestsCanSeeOtherGuests());
    assertNull(event.isPrivateCopy());
    assertNull(event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNull(creator);
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNull(organizer);
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNull(dtz);
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNull(dtz);
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNull(recurrence);
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNull(dtz);
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNotNull(attendees);
      assertEquals(0, attendees.size());
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNull(extendedProperties);
    }

    {
      final Gadget gadget = event.getGadget();
      assertNull(gadget);
    }

    {
      final Reminders reminders = event.getReminders();
      assertNotNull(reminders);
      assertNull(reminders.isUseDefault());

      final List<ReminderOverride> overrides = reminders.getOverrides();
      assertNull(overrides);
    }

    {
      final Source source = event.getSource();
      assertNull(source);
    }
  }

  @Test
  public void listEvents_values_7() {
// Swap the values for the next in sequence - booleans swap, enums on to next, strings to empty, numbers to zero.

    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("mock_list_events_values_7"), APPLICATION_JSON));

    final EventPage eventPage = google.calendarOperations()
      .eventListQuery(CalendarOperations.PRIMARY_CALENDAR_ID)
      .getPage();

    assertNotNull(eventPage);
    assertNull(eventPage.getNextPageToken());
    assertEquals("\"0001\"", eventPage.getEtag());
    assertNotNull(eventPage.getItems());
    assertEquals(1, eventPage.getItems().size());

    final Event event = eventPage.getItems().get(0);
    assertNotNull(event);
    assertEquals("\"etag1\"", event.getEtag());

    assertEquals("", event.getId());
    assertNull(event.getStatus());
    assertNull(event.getHtmlLink());
    assertNull(event.getCreated());
    assertNull(event.getUpdated());

    assertNull(event.getSummary());
    assertNull(event.getDescription());
    assertNull(event.getLocation());
    assertNull(event.getColorId());
    assertNull(event.getRecurringEventId());
    assertNull(event.getTransparency());
    assertNull(event.getVisibility());
    assertNull(event.getiCalUID());
    assertNull(event.getSequence());
    assertNull(event.getHangoutLink());

    assertNull(event.isEndTimeUnspecified());
    assertNull(event.isAttendeesOmitted());
    assertNull(event.isAnyoneCanAddSelf());
    assertTrue(event.isGuestsCanInviteOthers());
    assertNull(event.isGuestsCanModify());
    assertTrue(event.isGuestsCanSeeOtherGuests());
    assertNull(event.isPrivateCopy());
    assertNull(event.isLocked());

    {
      final Creator creator = event.getCreator();
      assertNull(creator);
    }

    {
      final Organizer organizer = event.getOrganizer();
      assertNull(organizer);
    }

    {
      final DateTimeTimezone dtz = event.getStart();
      assertNull(dtz);
    }

    {
      final DateTimeTimezone dtz = event.getEnd();
      assertNull(dtz);
    }

    {
      final List<String> recurrence = event.getRecurrence();
      assertNull(recurrence);
    }

    {
      final DateTimeTimezone dtz = event.getOriginalStartTime();
      assertNull(dtz);
    }

    {
      final List<Attendee> attendees = event.getAttendees();
      assertNull(attendees);
    }

    {
      final ExtendedProperties extendedProperties = event.getExtendedProperties();
      assertNull(extendedProperties);
    }

    {
      final Gadget gadget = event.getGadget();
      assertNull(gadget);
    }

    {
      final Reminders reminders = event.getReminders();
      assertNull(reminders);
    }

    {
      final Source source = event.getSource();
      assertNull(source);
    }
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
    assertEquals(id, event.getId());
    assertEquals("Wearable Tech Show", event.getSummary());
  }

  @Test
  public void quickAddEvent() {

    // Not testing the actual creation, but check URL.
    mockServer
      .expect(requestTo("https://www.googleapis.com/calendar/v3/calendars/primary/events/quickAdd?text=Appointment+at+Somewhere+every+June+3rd+10am-10%3A25am&sendNotifications=true&access_token=ACCESS_TOKEN"))
      .andExpect(method(POST))
      .andRespond(
        withSuccess(jsonResource("mock_added_event"), APPLICATION_JSON));

    final Event event = google.calendarOperations().quickAddEvent("primary", "Appointment at Somewhere every June 3rd 10am-10:25am", true);

    assertNotNull(event);
    assertEquals(1, event.getRecurrence().size());
    assertEquals("RRULE:FREQ=YEARLY;INTERVAL=1", event.getRecurrence().get(0));
    assertNotNull(event.getStart());
    assertNotNull(event.getStart().getDateTime());
    assertNull(event.getStart().getDate());
  }
}
