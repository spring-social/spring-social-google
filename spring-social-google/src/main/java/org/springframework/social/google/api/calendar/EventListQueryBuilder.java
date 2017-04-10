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

import java.util.Date;
import java.util.TimeZone;

import org.springframework.social.google.api.query.ApiQueryBuilder;

/**
 * Constructs queries for listing the events in a calendar, following the builder pattern.
 * The {@link #getPage()} method returns an {@link EventPage} containing {@link Event} instances.
 * <p>
 * See
 * <a href="http://developers.google.com/google-apps/calendar/v3/reference/events/list">
 * http://developers.google.com/google-apps/calendar/v3/reference/events/list</a>
 * for details of the parameters.
 * </p>
 * 
 * @author Martin Wink
 */
public interface EventListQueryBuilder extends ApiQueryBuilder<EventListQueryBuilder, EventPage> {
	/**
	 * Specifies whether to always include a value in the email field for the organizer,
	 * creator and attendees, even if no real email is available (i.e. a generated,
	 * non-working value will be provided). The use of this option is discouraged and
	 * should only be used by clients which cannot handle the absence of an email address
	 * value in the mentioned places. Optional. The default is {@code false}.
	 * @param alwaysIncludeEmail whether to always include email.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder alwaysIncludeEmail(boolean alwaysIncludeEmail);
	
	/**
	 * Specifies event ID in the iCalendar format to be included in the response. Optional.
	 * @param iCalUID the event ID in the iCalendar format.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder iCalUID(String iCalUID);
	
	/**
	 * The maximum number of attendees to include in the response. If there are more than
	 * the specified number of attendees, only the participant is returned. Optional.
	 * @param maxAttendees the maximum number of attendees to include.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder maxAttendees(int maxAttendees);
	
	/**
	 * The order of the events returned in the result. Optional. The default is an
	 * unspecified, stable order. 
	 * @param orderBy the required order.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder orderBy(OrderBy orderBy);
	
	/**
	 * Whether to include deleted events (with status equals "cancelled") in the result.
	 * Cancelled instances of recurring events (but not the underlying recurring event)
	 * will still be included if {@link #showDeleted} and {@link #singleEvents} are both
	 * {@code false}. If {@link #showDeleted} and {@link #singleEvents} are both
	 * {@code true}, only single instances of deleted events (but not the underlying
	 * recurring events) are returned. Optional. The default is {@code false}.
	 * @param showDeleted whether to include deleted events.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder showDeleted(boolean showDeleted);
	
	/**
	 * Whether to include hidden invitations in the result. Optional.
	 * The default is {@code false}.
	 * @param showHiddenInvitations whether to include hidden invitations.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder showHiddenInvitations(boolean showHiddenInvitations);
	
	/**
	 * Whether to expand recurring events into instances and only return single one-off
	 * events and instances of recurring events, but not the underlying recurring events
	 * themselves. Optional. The default is {@code false}.
	 * @param singleEvents whether to expand recurring events into single events. 
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder singleEvents(boolean singleEvents);
	
	/**
	 * Lower bound (inclusive) for an event's end time to filter by. Optional.
	 * The default is not to filter by end time.
	 * @param timeMin the detailed time.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder timeMin(Date timeMin);
	
	/**
	 * Lower bound (inclusive) for an event's end time to filter by. Optional.
	 * The default is not to filter by end time.
	 * @param year the full year, for example 2014.
	 * @param month the month, for example 1 for January.
	 * @param day the day in the month, starting at 1.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder timeMin(int year, int month, int day);
	
	/**
	 * Upper bound (exclusive) for an event's start time to filter by. Optional.
	 * The default is not to filter by start time.
	 * @param timeMax the detailed time.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder timeMax(Date timeMax);
	
	/**
	 * Upper bound (exclusive) for an event's start time to filter by. Optional.
	 * The default is not to filter by start time.
	 * @param year the full year, for example 2014.
	 * @param month the month, for example 1 for January.
	 * @param day the day in the month, starting at 1.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder timeMax(int year, int month, int day);
	
	/**
	 * Lower bound for an event's last modification time to filter by.
	 * When specified, entries deleted since this time will always be included regardless
	 * of showDeleted.
	 * Optional. The default is not to filter by last modification time.
	 * @param updatedMin the lower bound for an event's last modification time.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder updatedMin(Date updatedMin);
	
	/**
	 * Time zone used in the response. Optional. The default is the time zone of the calendar.
	 * @param timeZone the time zone.
	 * @return this {@code EventListQueryBuilder}, for refining the query.
	 */
	EventListQueryBuilder timeZone(TimeZone timeZone);
}
