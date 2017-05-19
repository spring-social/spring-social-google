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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.google.api.calendar.impl.VisibilityDeserializer;
import org.springframework.social.google.api.impl.ApiEnumSerializer;

/**
 * Enumeration representing the visibility of an event.
 *
 * @author Martin Wink
 */
@JsonSerialize(using = ApiEnumSerializer.class)
@JsonDeserialize(using = VisibilityDeserializer.class)
public enum Visibility {
  /**
   * "default" - Uses the default visibility for events on the calendar.
   * This is the default value.
   */
  DEFAULT,

  /**
   * "public" - The event is public and event details are visible to all readers of
   * the calendar.
   */
  PUBLIC,

  /**
   * "private" - The event is private and only event attendees may view event details.
   */
  PRIVATE,

  /**
   * "confidential" - The event is private. This value is provided for compatibility
   * reasons.
   */
  CONFIDENTIAL
}
