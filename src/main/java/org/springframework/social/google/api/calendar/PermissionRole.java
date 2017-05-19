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

import org.springframework.social.google.api.calendar.impl.PermissionRoleDeserializer;
import org.springframework.social.google.api.impl.ApiEnumSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Enumeration representing a user permission role in Google Calendar.
 * 
 * @author Martin Wink
 */
@JsonSerialize(using=ApiEnumSerializer.class)
@JsonDeserialize(using=PermissionRoleDeserializer.class)
public enum PermissionRole {
	/**
	 * "freeBusyReader" - Provides read access to free/busy information.
	 */
	FREE_BUSY_READER,
	
	/**
	 * "reader" - Provides read access to the calendar.
	 * Private events will appear to users with reader access, but event details will be
	 * hidden.
	 */
	READER,
	
	/**
	 * "writer" - Provides read and write access to the calendar.
	 * Private events will appear to users with writer access, and event details will be
	 * visible.
	 */
	WRITER,
	
	/**
	 * "owner" - Provides ownership of the calendar.
	 * This role has all of the permissions of the writer role with the additional ability
	 * to see and manipulate ACLs.
	 */
	OWNER
}
