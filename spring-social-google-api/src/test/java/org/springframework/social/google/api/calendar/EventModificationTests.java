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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.social.google.api.AbstractGoogleApiTest;
import org.springframework.social.google.api.calendar.Event.DateTimeTimezone;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Tests to verify that the Event JSON is correctly updated through the Event class.
 * @author Martin Wink
 */
public class EventModificationTests extends AbstractGoogleApiTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	private File originalFile;
	private File newFile;
	private ObjectMapper mapper;
	private Event event;

	@Before
	public void createTestData() throws IOException {
		originalFile = jsonResource("pre_modification_event").getFile();
		newFile = folder.newFile();
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// kind etc not used in Event.
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);	// So output matches expected formatting.
		event = mapper.readValue(originalFile, Event.class);
	}

	@After
	public void cleanUp() {
	}

	private String loadFileToString(File file) throws IOException, FileNotFoundException {
		return StreamUtils.copyToString(new FileInputStream(file), Charset.defaultCharset());
	}
	
	private String loadJsonResourceToString(String name) throws FileNotFoundException, IOException {
		return loadFileToString(jsonResource(name).getFile());
	}

	@Test
	public void verify_unmodified() throws JsonParseException, JsonMappingException, IOException {
		mapper.writeValue(newFile, event);
		assertEquals(loadJsonResourceToString("post_unmodified_event"), loadFileToString(newFile));
	}

	@Test
	public void set_null_values() throws JsonParseException, JsonMappingException, IOException {
		event.setGuestsCanInviteOthers(null);
		assertEquals(null, event.isGuestsCanInviteOthers());

		event.setGuestsCanSeeOtherGuests(null);
		assertEquals(null, event.isGuestsCanSeeOtherGuests());

		event.setLocation(null);
		assertEquals(null, event.getLocation());

		event.setStatus(null);
		assertEquals(null, event.getStatus());

		event.setSummary(null);
		assertEquals(null, event.getSummary());

		final DateTimeTimezone start = event.getStart();
		start.setDate(null);
		assertNull(start.getDate());

		start.setDateTime(null);
		assertNull(start.getDateTime());

		start.setTimeZone(null);
		assertNull(start.getTimeZone());

		final DateTimeTimezone end = event.getEnd();
		end.setDate(null);
		assertNull(end.getDate());

		end.setDateTime(null);
		assertNull(end.getDateTime());

		end.setTimeZone(null);
		assertNull(end.getTimeZone());
		
		event.setRecurrence(null);
		assertNull(event.getRecurrence());

		mapper.writeValue(newFile, event);
		assertEquals(loadJsonResourceToString("post_null_values_event"), loadFileToString(newFile));
	}

	@Test
	public void set_non_null_values_1() throws JsonParseException, JsonMappingException, IOException {
		event.setGuestsCanInviteOthers(true);
		assertEquals(true, event.isGuestsCanInviteOthers());

		event.setGuestsCanSeeOtherGuests(false);
		assertEquals(false, event.isGuestsCanSeeOtherGuests());

		event.setLocation("Somewhere else");
		assertEquals("Somewhere else", event.getLocation());

		event.setStatus(EventStatus.CANCELLED);
		assertEquals(EventStatus.CANCELLED, event.getStatus());

		event.setSummary("New summary");
		assertEquals("New summary", event.getSummary());

		final DateTimeTimezone start = event.getStart();
		final Date date1 = DateUtils.makeDate(2014, 11, 27);
		start.setDate(date1);
		assertEquals(date1, start.getDate());

		final Date date2 = DateUtils.makeDate(2014, 11, 28);
		start.setDateTime(date2);
		assertEquals(date2, start.getDateTime());

		final TimeZone timeZone1 = TimeZone.getTimeZone("UTC");
		start.setTimeZone(timeZone1);
		assertEquals(timeZone1, start.getTimeZone());

		final DateTimeTimezone end = event.getEnd();
		final Date date3 = DateUtils.makeDate(2014, 11, 29);
		end.setDate(date3);
		assertEquals(date3, end.getDate());

		final Date date4 = DateUtils.makeDate(2014, 11, 30);
		end.setDateTime(date4);
		assertEquals(date4, end.getDateTime());

		final TimeZone timeZone2 = TimeZone.getTimeZone("PST");
		end.setTimeZone(timeZone2);
		assertEquals(timeZone2, end.getTimeZone());

		event.setRecurrence(new ArrayList<String>());
		assertNotNull(event.getRecurrence());
		assertEquals(0, event.getRecurrence().size());

		mapper.writeValue(newFile, event);
		assertEquals(loadJsonResourceToString("post_non_null_values_1_event"), loadFileToString(newFile));
	}

	@Test
	public void set_non_null_values_2() throws JsonParseException, JsonMappingException, IOException {
		event.setGuestsCanInviteOthers(false);
		assertEquals(false, event.isGuestsCanInviteOthers());

		event.setGuestsCanSeeOtherGuests(true);
		assertEquals(true, event.isGuestsCanSeeOtherGuests());

		event.setLocation("Another place");
		assertEquals("Another place", event.getLocation());

		event.setStatus(EventStatus.TENTATIVE);
		assertEquals(EventStatus.TENTATIVE, event.getStatus());

		event.setSummary("Another title");
		assertEquals("Another title", event.getSummary());

		final DateTimeTimezone start = event.getStart();
		final Date date1 = DateUtils.makeDate(2013, 11, 27);
		start.setDate(date1);
		assertEquals(date1, start.getDate());

		final Date date2 = DateUtils.makeDate(2013, 11, 28);
		start.setDateTime(date2);
		assertEquals(date2, start.getDateTime());

		final TimeZone timeZone1 = TimeZone.getTimeZone("CET");
		start.setTimeZone(timeZone1);
		assertEquals(timeZone1, start.getTimeZone());

		final DateTimeTimezone end = event.getEnd();
		final Date date3 = DateUtils.makeDate(2013, 11, 29);
		end.setDate(date3);
		assertEquals(date3, end.getDate());

		final Date date4 = DateUtils.makeDate(2013, 11, 30);
		end.setDateTime(date4);
		assertEquals(date4, end.getDateTime());

		final TimeZone timeZone2 = TimeZone.getTimeZone("MST");
		end.setTimeZone(timeZone2);
		assertEquals(timeZone2, end.getTimeZone());

		final ArrayList<String> list = new ArrayList<String>();
		list.add("RRULE:FREQ=MONTHLY;INTERVAL=1");
		list.add("RRULE:FREQ=MONTHLY;INTERVAL=3");
		event.setRecurrence(list);
		assertNotNull(event.getRecurrence());
		assertEquals(2, event.getRecurrence().size());

		mapper.writeValue(newFile, event);
		assertEquals(loadJsonResourceToString("post_non_null_values_2_event"), loadFileToString(newFile));
	}

}
