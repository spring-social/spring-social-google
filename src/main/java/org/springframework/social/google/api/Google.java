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
package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.google.api.calendar.CalendarOperations;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.oauth2.OAuth2Operations;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.api.tasks.TaskOperations;
import org.springframework.web.client.RestOperations;

/**
 * Interface specifying a basic set of operations for interacting with Google
 * APIs. Implemented by {@link GoogleTemplate}.
 *
 * @author Gabriel Axel
 */
public interface Google extends ApiBinding {

  /**
   * Retrieves {@link OAuth2Operations}, used for interacting with Google+ API.
   *
   * @return {@link PlusOperations} for the authenticated user if
   *         authenticated
   */
  OAuth2Operations oauth2Operations();

  /**
   * Retrieves {@link PlusOperations}, used for interacting with Google+ API.
   * Some methods require OAuth2 scope https://www.googleapis.com/auth/plus.me
   *
   * @return {@link PlusOperations} for the authenticated user if
   *         authenticated
   */
  PlusOperations plusOperations();

  /**
   * Retrieves {@link TaskOperations}, used for interacting with Google Tasks
   * API. Requires OAuth scope https://www.googleapis.com/auth/tasks or
   * https://www.googleapis.com/auth/tasks.readonly
   *
   * @return {@link TaskOperations} for the authenticated user
   */
  TaskOperations taskOperations();

  /**
   * Retrieves {@link DriveOperations}, used for interacting with Google Drive
   * API. Requires OAuth scope(s) from the following:
   * <ul>
   * <li>https://www.googleapis.com/auth/drive.file</li>
   * <li>https://www.googleapis.com/auth/drive</li>
   * <li>https://www.googleapis.com/auth/drive.apps.readonly</li>
   * <li>https://www.googleapis.com/auth/drive.readonly</li>
   * <li>https://www.googleapis.com/auth/drive.readonly.metadata</li>
   * </ul>
   * See <a
   * href="https://developers.google.com/drive/scopes">https://developers
   * .google.com/drive/scopes</a> for details about the different scopes
   *
   * @return {@link DriveOperations} for the authenticated user
   */
  DriveOperations driveOperations();

  /**
   * Retrieves {@link CalendarOperations}, used for interacting with Google Calendar API.
   * Some methods require OAuth2 scope from the following:
   * <ul>
   * <li>https://www.googleapis.com/auth/calendar.readonly</li>
   * <li>https://www.googleapis.com/auth/calendar</li>
   * </ul>
   *
   * @return {@link CalendarOperations} for the authenticated user if authenticated
   */
  CalendarOperations calendarOperations();

  /**
   * Returns the access token, allowing interoperability with other libraries
   *
   * @see <a href="http://code.google.com/p/google-api-java-client/">Google
   *      APIs Client Library for Java</a>
   *
   * @return The OAuth2 access token
   */
  String getAccessToken();

  RestOperations restOperations();
}
