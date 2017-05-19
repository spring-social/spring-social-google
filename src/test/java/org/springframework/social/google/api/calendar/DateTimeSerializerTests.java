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

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;
import org.springframework.social.google.api.calendar.impl.DateTimeSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import static org.mockito.Mockito.*;

/**
 * Tests to verify that the {@link DateTimeSerializer} works.
 * @author Martin Wink
 */
public class DateTimeSerializerTests extends AbstractGoogleApiTest {
	private static JsonGenerator jgen;
	private static SerializerProvider provider = null;
	private static DateTimeSerializer serializer;

	@Before
	public void beforeEachTest() {
		jgen = mock(JsonGenerator.class);
		serializer = new DateTimeSerializer();
	}

	@Test(expected=NullPointerException.class)
	public void serialize_null_exception() throws IOException {
		serializer.serialize(null, jgen, provider);
	}

	@Test
	public void serialize_zero() throws IOException {
		serializer.serialize(new Date(0), jgen, provider);
		verify(jgen).writeString("1970-01-01T00:00:00.000Z");
	}

	@Test
	public void serialize_one_millisecond() throws IOException {
		serializer.serialize(new Date(1), jgen, provider);
		verify(jgen).writeString("1970-01-01T00:00:00.001Z");
	}

	@Test
	public void serialize_one_second() throws IOException {
		serializer.serialize(new Date(1000), jgen, provider);
		verify(jgen).writeString("1970-01-01T00:00:01.000Z");
	}

	@Test
	public void serialize_1234() throws IOException {
		serializer.serialize(new Date(1234), jgen, provider);
		verify(jgen).writeString("1970-01-01T00:00:01.234Z");
	}

	@Test
	public void serialize_leap_day_2012() throws IOException {
		serializer.serialize(new Date(1330473600000L), jgen, provider);
		verify(jgen).writeString("2012-02-29T00:00:00.000Z");
	}

	@Test
	public void serialize_20140101_120000001() throws IOException {
		serializer.serialize(new Date(1388577600001L), jgen, provider);
		verify(jgen).writeString("2014-01-01T12:00:00.001Z");
	}

	@Test
	public void serialize_20140601_101010000() throws IOException {
		serializer.serialize(new Date(1401617410000L), jgen, provider);
		verify(jgen).writeString("2014-06-01T10:10:10.000Z");
	}

	@Test
	public void serialize_20141231_235959000() throws IOException {
		serializer.serialize(new Date(1420070399000L), jgen, provider);
		verify(jgen).writeString("2014-12-31T23:59:59.000Z");
	}
}
