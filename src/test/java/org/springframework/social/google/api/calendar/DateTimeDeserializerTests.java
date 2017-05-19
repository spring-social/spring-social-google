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
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;
import org.springframework.social.google.api.calendar.impl.DateTimeDeserializer;

/**
 * Tests to verify that the {@link DateTimeDeserializer} works.
 * @author Martin Wink
 */
public class DateTimeDeserializerTests extends AbstractGoogleApiTest {

  private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US);
  private static DeserializationContext deserializationContext = null;
  private static JsonParser jsonParser;
  private static DateTimeDeserializer deserializer;

  private static String formatDate(final Date date) {
    format.setTimeZone(DateUtils.TEST_TIMEZONE);
    return format.format(date);
  }

  @Before
  public void beforeEachTest() {
    jsonParser = mock(JsonParser.class);
    deserializer = new DateTimeDeserializer();
  }

  @Test
  public void verifyMakeDate_1970_1_1() {
    final Date d = DateUtils.makeDate(1970, 1, 1);
    assertEquals(0, d.getTime());
  }

  @Test
  public void verifyMakeDate_2012_2_29() {
    final Date d = DateUtils.makeDate(2012, 2, 29);
    assertEquals(1330473600000L, d.getTime());
  }

  @Test
  public void deserialize_empty_is_null() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("");

    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertNull(result);
  }

  @Test(expected = JsonParseException.class)
  public void deserialize_garbage_exception() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("jhgfjkasdg");
    @SuppressWarnings("unused") final Date result = deserializer.deserialize(jsonParser, deserializationContext);
  }

  @Test
  public void deserialize_1970_01_01T00_00_00_000Z_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:00.000Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_00_000_plus_0_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:00.000+00:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_00_000_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:00.000");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_00Z_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:00Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T01_00_00_TZ_plus_1_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T01:00:00+01:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T02_00_00_TZ_plus_2_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T02:00:00+02:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_00_is_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(0, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01_is_1000_with_millis_Z() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01.000Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1000, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01_is_1000_without_millis_Z() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1000, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01_is_1000_with_millis_plus_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01.000+00:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1000, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01_is_1000_without_millis_plus_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01+00:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1000, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01_is_1000_with_millis_minus_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01.000-00:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1000, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01_is_1000_without_millis_minus_0() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01-00:00");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1000, result.getTime());
  }

  @Test
  public void deserialize_1970_01_01T00_00_01246_is_1246() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("1970-01-01T00:00:01.234Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1234, result.getTime());
  }

  @Test
  public void deserialize_leap_day_2012() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("2012-02-29T00:00:00.000Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1330473600000L, result.getTime());

    // Sanity check
    assertEquals(jsonParser.getValueAsString(), formatDate(result));
  }

  @Test
  public void deserialize_20140101_120000001() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("2014-01-01T12:00:00.001Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1388577600001L, result.getTime());

    // Sanity check
    assertEquals(jsonParser.getValueAsString(), formatDate(result));
  }

  @Test
  public void deserialize_20140601_101010000() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("2014-06-01T10:10:10.000Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1401617410000L, result.getTime());

    // Sanity check
    assertEquals(jsonParser.getValueAsString(), formatDate(result));
  }

  @Test
  public void deserialize_20141231_235959000() throws IOException {
    when(jsonParser.getValueAsString()).thenReturn("2014-12-31T23:59:59.000Z");
    final Date result = deserializer.deserialize(jsonParser, deserializationContext);
    assertEquals(1420070399000L, result.getTime());

    // Sanity check
    assertEquals(jsonParser.getValueAsString(), formatDate(result));
  }
}
