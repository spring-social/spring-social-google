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

import org.springframework.social.google.api.calendar.impl.EventStatusDeserializer;
import org.springframework.social.google.api.impl.ApiEnumSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Enumeration representing an event's status.
 * 
 * @author Martin Wink
 */
@JsonSerialize(using=ApiEnumSerializer.class)
@JsonDeserialize(using=EventStatusDeserializer.class)
public enum EventStatus {
	/**
	 * "confirmed" - The event is confirmed. This is the default status.
	 */
	CONFIRMED,
	
	/**
	 * "tentative" - The event is tentatively confirmed.
	 */
	TENTATIVE,
	
	/**
	 * "cancelled" - The event is cancelled.
	 */
	CANCELLED
}
