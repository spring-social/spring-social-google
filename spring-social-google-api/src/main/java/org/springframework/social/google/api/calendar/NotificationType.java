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

import org.springframework.social.google.api.calendar.impl.NotificationTypeDeserializer;
import org.springframework.social.google.api.impl.ApiEnumSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Enumeration representing a notification type.
 * 
 * @author Martin Wink
 */
@JsonSerialize(using=ApiEnumSerializer.class)
@JsonDeserialize(using=NotificationTypeDeserializer.class)
public enum NotificationType {
	/**
	 * "eventCreation" - Notification sent when a new event is put on the calendar.
	 */
	EVENT_CREATION,
	
	/**
	 * "eventChange" - Notification sent when an event is changed.
	 */
	EVENT_CHANGE,
	
	/**
	 * "eventCancellation" - Notification sent when an event is cancelled.
	 */
	EVENT_CANCELLATION,
	
	/**
	 * "eventResponse" - Notification sent when an event is changed.
	 */
	EVENT_RESPONSE,
	
	/**
	 * "agenda" - An agenda with the events of the day (sent out in the morning).
	 */
	AGENDA
}
