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
package org.springframework.social.google.api.calendar.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * {@link JsonDeserializer} for {@link Date}.
 * Required because date times returned in events sometimes include milliseconds, sometimes not,
 * so this implementation is more forgiving.
 * 
 * @author Martin Wink
 */
public class DateTimeDeserializer extends JsonDeserializer<Date> {
	
	private static final SimpleDateFormat[] DATE_FORMATS = new SimpleDateFormat[] {
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"),
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"),
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"),
		new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
	};

	static {
		final TimeZone timeZone = TimeZone.getTimeZone("GMT");
		for (SimpleDateFormat format : DATE_FORMATS) {
			format.setTimeZone(timeZone);
		}
	}

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws JsonParseException, IOException {
		String valueAsString = jp.getValueAsString();
		if (valueAsString != null && valueAsString.length() > 0) {
			for (SimpleDateFormat format : DATE_FORMATS) {
				try {
					Date parsedDate = format.parse(valueAsString);
					return parsedDate;
				} catch (ParseException e) {
					// Ignore, and try the next format.
				}
			}
			throw new JsonParseException("Date-time unparseable: \"" + valueAsString + "\".", jp.getCurrentLocation());
		}
		return null;
	}
}
