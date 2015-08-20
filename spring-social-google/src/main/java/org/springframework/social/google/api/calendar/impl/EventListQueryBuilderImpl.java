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

import java.text.MessageFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.social.google.api.calendar.EventPage;
import org.springframework.social.google.api.calendar.EventListQueryBuilder;
import org.springframework.social.google.api.calendar.OrderBy;
import org.springframework.social.google.api.impl.ApiEnumSerializer;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link EventListQueryBuilder} implementation.
 * 
 * @author Martin Wink
 */
public class EventListQueryBuilderImpl extends ApiQueryBuilderImpl<EventListQueryBuilder, EventPage> implements EventListQueryBuilder {
	
	public EventListQueryBuilderImpl(String urlTemplate, String calendarId, Class<EventPage> type, RestTemplate restTemplate) {
		super(MessageFormat.format(urlTemplate, encode(calendarId)), type, restTemplate);
	}

	private static final java.util.Calendar utcCalendar = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"));;
	private Date makeDate(int year, int month, int day) {
		utcCalendar.setTimeInMillis(0);
		utcCalendar.set(year, month - 1, day);
		return utcCalendar.getTime();
	}
	
	@Override
	public EventListQueryBuilder fromPage(String pageToken) {
		// Override because super class's implementation doesn't encode.
		return super.fromPage(pageToken == null ? null : encode(pageToken));
	}

	@Override
	public EventListQueryBuilder timeMin(Date timeMin) {
		return appendQueryParam("timeMin", timeMin);
	}

	@Override
	public EventListQueryBuilder timeMin(int year, int month, int day) {
		return timeMin(makeDate(year, month, day));
	}

	@Override
	public EventListQueryBuilder timeMax(Date timeMax) {
		return appendQueryParam("timeMax", timeMax);
	}

	@Override
	public EventListQueryBuilder timeMax(int year, int month, int day) {
		return timeMax(makeDate(year, month, day));
	}

	@Override
	public EventListQueryBuilder orderBy(OrderBy orderBy) {
		return appendQueryParam("orderBy", ApiEnumSerializer.enumToString(orderBy));
	}

	@Override
	public EventListQueryBuilder singleEvents(boolean singleEvents) {
		return appendQueryParam("singleEvents", singleEvents);
	}

	@Override
	public EventListQueryBuilder showDeleted(boolean showDeleted) {
		return appendQueryParam("showDeleted", showDeleted);
	}

	@Override
	public EventListQueryBuilder showHiddenInvitations(boolean showHiddenInvitations) {
		return appendQueryParam("showHiddenInvitations", showHiddenInvitations);
	}

	@Override
	public EventListQueryBuilder timeZone(TimeZone timeZone) {
		return appendQueryParam("timeZone", timeZone.getID());
	}

	@Override
	public EventListQueryBuilder updatedMin(Date updatedMin) {
		return appendQueryParam("updatedMin", updatedMin);
	}

	@Override
	public EventListQueryBuilder alwaysIncludeEmail(boolean alwaysIncludeEmail) {
		return appendQueryParam("alwaysIncludeEmail", alwaysIncludeEmail);
	}

	@Override
	public EventListQueryBuilder iCalUID(String iCalUID) {
		return appendQueryParam("iCalUID", encode(iCalUID));
	}

	@Override
	public EventListQueryBuilder maxAttendees(int maxAttendees) {
		return appendQueryParam("maxAttendees", maxAttendees);
	}
}
