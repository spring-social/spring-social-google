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

import org.springframework.social.google.api.calendar.impl.OrderByDeserializer;
import org.springframework.social.google.api.impl.ApiEnumSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Enumeration representing the ordering of results.
 * 
 * @author Martin Wink
 */
@JsonSerialize(using=ApiEnumSerializer.class)
@JsonDeserialize(using=OrderByDeserializer.class)
public enum OrderBy {
	/**
	 * "startTime": Order by the start date/time (ascending).
	 * This is only available when querying single events (i.e. the parameter singleEvents
	 * is {@code true}) otherwise a BAD REQUEST will result.
	 */
	START_TIME,
	
	/**
	 * "updated": Order by last modification time (ascending).
	 */
	UPDATED
}
