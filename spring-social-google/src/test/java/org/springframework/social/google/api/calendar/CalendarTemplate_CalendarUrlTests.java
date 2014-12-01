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
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

public class CalendarTemplate_CalendarUrlTests extends AbstractGoogleApiTest {

	@Test
	public void listCalendars_with_minAccessRole_freeBusyReader() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?minAccessRole=freeBusyReader"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().minAccessRole(PermissionRole.FREE_BUSY_READER).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_minAccessRole_owner() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?minAccessRole=owner"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().minAccessRole(PermissionRole.OWNER).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_minAccessRole_reader() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?minAccessRole=reader"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().minAccessRole(PermissionRole.READER).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_minAccessRole_writer() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?minAccessRole=writer"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().minAccessRole(PermissionRole.WRITER).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_showDeleted_true() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?showDeleted=true"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().showDeleted(true).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_showDeleted_false() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().showDeleted(false).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_showHidden_true() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList?showHidden=true"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().showHidden(true).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void listCalendars_with_showHidden_false() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_list_calendars"), APPLICATION_JSON));

		CalendarPage calPage = google.calendarOperations().calendarListQuery().showHidden(false).getPage();

		assertNotNull(calPage);
	}

	@Test
	public void getCalendar_primary() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList/primary"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_get_calendar_primary"), APPLICATION_JSON));

		Calendar cal = google.calendarOperations().getCalendar("primary");

		assertNotNull(cal);
		// NB queried for "primary" but actually get back the real ID.
		assertEquals("martxw@gmail.com", cal.getId());
		assertEquals("martxw@gmail.com", cal.getSummary());
		assertEquals(true, cal.isPrimary());
	}

	@Test
	public void getCalendar_escaped() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/calendar/v3/users/me/calendarList/abc123%21%22%C2%A3%24%25%5E%26*%28%29_%2B-%3D%5B%5D%7B%7D%3B%27%23%3A%40%7E%2C.%2F%3C%3E%3F"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("mock_get_calendar_primary"), APPLICATION_JSON));

		Calendar cal = google.calendarOperations().getCalendar("abc123!\"£$%^&*()_+-=[]{};'#:@~,./<>?");

		assertNotNull(cal);
		// NB queried for "primary" but actually get back the real ID.
		assertEquals("martxw@gmail.com", cal.getId());
		assertEquals("martxw@gmail.com", cal.getSummary());
		assertEquals(true, cal.isPrimary());
	}
}
