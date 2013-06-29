/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.google.api.plus.moments;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static org.springframework.social.google.api.plus.moments.MomentTypes.RESERVE_ACTIVITY;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Activity representing a reservation at a business such as a restaurant
 * 
 * @see {@link 
 * 	https://developers.google.com/+/api/moment-types/reserve-activity}
 * @author Gabriel Axel
 * 
 */
@JsonTypeName(RESERVE_ACTIVITY)
public class ReserveActivity extends Moment {

	protected static class Result {

		@JsonProperty
		@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
		Date startDate;

		@JsonProperty
		int attendeeCount;

		@JsonGetter
		String getType() {
			return "http://schemas.google.com/Reservation";
		}

	}

	@JsonProperty
	private Result result;

	protected ReserveActivity() {
	}

	public ReserveActivity(String targetUrl) {
		super(targetUrl);
		result = new Result();
	}

	public ReserveActivity(String targetUrl, Date startDate, int attendeeCount) {
		this(targetUrl);
		result.startDate = startDate;
		result.attendeeCount = attendeeCount;
	}
}
