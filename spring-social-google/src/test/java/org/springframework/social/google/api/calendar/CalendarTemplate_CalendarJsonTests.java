/*
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.google.api.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

public class CalendarTemplate_CalendarJsonTests extends AbstractGoogleApiTest {
	@Test
	public void listCalendars() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?access_token=ACCESS_TOKEN"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().getPage();

		assertNotNull(calPage);
		assertNull(calPage.getNextPageToken());
		assertEquals("\"0000000000000000\"", calPage.getEtag());

		assertEquals(5, calPage.getItems().size());
		Calendar cal0 = calPage.getItems().get(0);
		Calendar cal1 = calPage.getItems().get(1);
		Calendar cal2 = calPage.getItems().get(2);
		Calendar cal3 = calPage.getItems().get(3);
		Calendar cal4 = calPage.getItems().get(4);

		// Test each field in turn, with different values set in different calendars.
		//	String summary;
		//	String description;
		//	String location;
		//	String timezone;
		//	String summaryOverride;
		//	String colorId;
		//	String backgroundColor;
		//	String foregroundColor;
		//	boolean hidden;
		//	boolean selected;
		//	PermissionRole accessRole;
		//	List<DefaultReminder> defaultReminders;
		//	NotificationSettings notificationSettings;
		//	boolean primary;
		//	boolean deleted;

		assertEquals("martxw@gmail.com", cal0.getId());
		assertEquals("en.uk#holiday@group.v.calendar.google.com", cal1.getId());
		assertEquals("missing_values#martxw@gmail.com", cal2.getId());
		assertEquals("alternate_values_1#martxw@gmail.com", cal3.getId());
		assertEquals("alternate_values_2#martxw@gmail.com", cal4.getId());

		assertEquals("\"0000000000000000\"", cal0.getEtag());
		assertEquals("\"0000000000000001\"", cal1.getEtag());
		assertEquals("\"0000000000000002\"", cal2.getEtag());
		assertEquals("\"0000000000000003\"", cal3.getEtag());
		assertEquals("\"0000000000000004\"", cal4.getEtag());

		assertEquals("martxw@gmail.com", cal0.getSummary());
		assertEquals("Holidays in United Kingdom", cal1.getSummary());
		assertNull(cal2.getSummary());
		assertEquals("", cal3.getSummary());
		assertEquals("", cal4.getSummary());

		assertNull(cal0.getDescription());
		assertEquals("Holidays and Observances in United Kingdom", cal1.getDescription());
		assertNull(cal2.getDescription());
		assertEquals("", cal3.getDescription());
		assertEquals("", cal4.getDescription());

		assertNull(cal0.getLocation());
		assertNull(cal1.getLocation());
		assertNull(cal2.getLocation());
		assertEquals("Somewhere", cal3.getLocation());
		assertEquals("Somewhere", cal4.getLocation());

		assertEquals("Europe/London", cal0.getTimeZone().getID());
		assertEquals("Europe/London", cal1.getTimeZone().getID());
		assertNull(cal2.getTimeZone());
		assertEquals("Europe/Paris", cal3.getTimeZone().getID());
		// Empty timeZone => GMT
		assertEquals("GMT", cal4.getTimeZone().getID());

		assertNull(cal0.getSummaryOverride());
		assertNull(cal1.getSummaryOverride());
		assertNull(cal2.getSummaryOverride());
		assertEquals("My title", cal3.getSummaryOverride());
		assertEquals("", cal4.getSummaryOverride());

		assertEquals("15", cal0.getColorId());
		assertEquals("2", cal1.getColorId());
		assertNull(cal2.getColorId());
		assertEquals("", cal3.getColorId());
		assertEquals("", cal4.getColorId());

		assertEquals("#9fc6e7", cal0.getBackgroundColor());
		assertEquals("#d06b64", cal1.getBackgroundColor());
		assertNull(cal2.getBackgroundColor());
		assertEquals("", cal3.getBackgroundColor());
		assertEquals("", cal4.getBackgroundColor());

		assertEquals("#000000", cal0.getForegroundColor());
		assertEquals("#000000", cal1.getForegroundColor());
		assertNull(cal2.getForegroundColor());
		assertEquals("", cal3.getForegroundColor());
		assertEquals("", cal4.getForegroundColor());

		assertEquals(false, cal0.isHidden());
		assertEquals(false, cal1.isHidden());
		assertEquals(false, cal2.isHidden());
		assertEquals(true, cal3.isHidden());
		assertEquals(true, cal4.isHidden());

		assertEquals(true, cal0.isSelected());
		assertEquals(true, cal1.isSelected());
		assertEquals(false, cal2.isSelected());
		assertEquals(false, cal3.isSelected());
		assertEquals(false, cal4.isSelected());

		assertEquals(PermissionRole.OWNER, cal0.getAccessRole());
		assertEquals(PermissionRole.READER, cal1.getAccessRole());
		assertNull(cal2.getAccessRole());
		assertEquals(PermissionRole.WRITER, cal3.getAccessRole());
		assertEquals(PermissionRole.FREE_BUSY_READER, cal4.getAccessRole());

		assertNotNull(cal0.getDefaultReminders());
		assertEquals(3, cal0.getDefaultReminders().size());
		assertEquals(NotificationMethod.EMAIL, cal0.getDefaultReminders().get(0).getMethod());
		assertEquals(20, cal0.getDefaultReminders().get(0).getMinutes());
		assertEquals(NotificationMethod.SMS, cal0.getDefaultReminders().get(1).getMethod());
		assertEquals(15, cal0.getDefaultReminders().get(1).getMinutes());
		assertEquals(NotificationMethod.POPUP, cal0.getDefaultReminders().get(2).getMethod());
		assertEquals(10, cal0.getDefaultReminders().get(2).getMinutes());
		assertNotNull(cal1.getDefaultReminders());
		assertEquals(0, cal1.getDefaultReminders().size());
		assertNull(cal2.getDefaultReminders());
		assertNotNull(cal3.getDefaultReminders());

		// defaultReminders JSON contains [ {} ] but {} doesn't get instantiated, so size 0.
		// But this should never occur in a real response.
		assertEquals(0, cal3.getDefaultReminders().size());

		assertEquals(0, cal4.getDefaultReminders().size());

		assertNotNull(cal0.getNotificationSettings());
		assertNotNull(cal0.getNotificationSettings().getNotifications());
		assertEquals(4, cal0.getNotificationSettings().getNotifications().size());
		assertEquals(NotificationType.EVENT_CREATION, cal0.getNotificationSettings().getNotifications().get(0).getType());
		assertEquals(NotificationMethod.EMAIL, cal0.getNotificationSettings().getNotifications().get(0).getMethod());
		assertEquals(NotificationType.EVENT_CHANGE, cal0.getNotificationSettings().getNotifications().get(1).getType());
		assertEquals(NotificationMethod.SMS, cal0.getNotificationSettings().getNotifications().get(1).getMethod());
		assertEquals(NotificationType.EVENT_CANCELLATION, cal0.getNotificationSettings().getNotifications().get(2).getType());
		assertEquals(NotificationMethod.EMAIL, cal0.getNotificationSettings().getNotifications().get(2).getMethod());
		assertEquals(NotificationType.EVENT_RESPONSE, cal0.getNotificationSettings().getNotifications().get(3).getType());
		assertEquals(NotificationMethod.EMAIL, cal0.getNotificationSettings().getNotifications().get(3).getMethod());

		// notificationSettings JSON contains {} and this time it does get instantiated, but no notifications.
		assertNotNull(cal1.getNotificationSettings());
		assertNull(cal1.getNotificationSettings().getNotifications());

		assertNull(cal2.getNotificationSettings());

		assertNotNull(cal3.getNotificationSettings());
		assertNotNull(cal3.getNotificationSettings().getNotifications());
		assertEquals(0, cal3.getNotificationSettings().getNotifications().size());

		assertNotNull(cal4.getNotificationSettings());
		assertNotNull(cal4.getNotificationSettings().getNotifications());
		// notifications JSON contains [ {} ] but this time it does get instantiated to an empty instance, so size 1.
		assertEquals(1, cal4.getNotificationSettings().getNotifications().size());
		assertNotNull(cal4.getNotificationSettings().getNotifications().get(0));
		assertNull(cal4.getNotificationSettings().getNotifications().get(0).getMethod());
		assertNull(cal4.getNotificationSettings().getNotifications().get(0).getType());

		assertEquals(true, cal0.isPrimary());
		assertEquals(false, cal1.isPrimary());
		assertEquals(false, cal2.isPrimary());
		assertEquals(false, cal3.isPrimary());
		assertEquals(false, cal4.isPrimary());

		assertEquals(false, cal0.isDeleted());
		assertEquals(false, cal1.isDeleted());
		assertEquals(false, cal2.isDeleted());
		assertEquals(true, cal3.isDeleted());
		assertEquals(true, cal4.isDeleted());
	}
}
