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
import org.springframework.social.google.api.calendar.impl.AttendeeStatusDeserializer;
import org.springframework.social.google.api.impl.ApiEnumSerializer;

/**
 * Enumeration representing an attendee's response status.
 *
 * @author Martin Wink
 */
@JsonSerialize(using = ApiEnumSerializer.class)
@JsonDeserialize(using = AttendeeStatusDeserializer.class)
public enum AttendeeStatus {
  /**
   * "needsAction" - The attendee has not responded to the invitation.
   */
  NEEDS_ACTION,

  /**
   * "declined" - The attendee has declined the invitation.
   */
  DECLINED,

  /**
   * "tentative" - The attendee has tentatively accepted the invitation.
   */
  TENTATIVE,

  /**
   * "accepted" - The attendee has accepted the invitation.
   */
  ACCEPTED
}
