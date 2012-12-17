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
package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.social.google.api.plus.activity.ActivityOperations;
import org.springframework.social.google.api.plus.comment.CommentOperations;
import org.springframework.social.google.api.plus.person.PersonOperations;
import org.springframework.social.google.api.tasks.TaskOperations;

/**
 * Interface specifying a basic set of operations for interacting with Google
 * APIs. Implemented by {@link GoogleTemplate}.
 * 
 * @author Gabriel Axel
 */
public interface Google extends ApiBinding {

	/**
	 * Retrieves {@link LegacyProfileOperations}, used for fetching user profile
	 * (predates Google+). Requires OAuth2 scopes
	 * https://www.googleapis.com/auth/userinfo.profile
	 * https://www.googleapis.com/auth/userinfo#email
	 * 
	 * @return {@link LegacyProfileOperations} for the authenticated user
	 */
	LegacyProfileOperations userOperations();

	/**
	 * Retrieves {@link PersonOperations}, used for interacting with Google+
	 * People API. Some methods require OAuth2 scope
	 * https://www.googleapis.com/auth/plus.me
	 * 
	 * @return {@link PersonOperations} for the authenticated user if
	 *         authenticated
	 */
	PersonOperations personOperations();

	/**
	 * Retrieves {@link ActivityOperations}, used for interacting with Google+
	 * Activities API. Some methods require OAuth2 scope
	 * https://www.googleapis.com/auth/plus.me
	 * 
	 * @return {@link ActivityOperations} for the authenticated user if
	 *         authenticated
	 */
	ActivityOperations activityOperations();

	/**
	 * Retrieves {@link CommentOperations}, used for interacting with Google+
	 * Comments API. Optionally uses OAuth2 scope
	 * https://www.googleapis.com/auth/plus.me
	 * 
	 * @return {@link ActivityOperations} for the authenticated user if
	 *         authenticated
	 */
	CommentOperations commentOperations();

	/**
	 * Retrieves {@link TaskOperations}, used for interacting with Google Tasks
	 * API. Requires OAuth scope https://www.googleapis.com/auth/tasks or
	 * https://www.googleapis.com/auth/tasks.readonly
	 * 
	 * @return {@link TaskOperations} for the authenticated user
	 */
	TaskOperations taskOperations();
	
	/**
	 * Retrieves {@link DriveOperations}, used for interacting with Google Drive API.
	 * Requires OAuth scope(s) from the following:
	 * <ul>
	 * <li>https://www.googleapis.com/auth/drive.file</li>
	 * <li>https://www.googleapis.com/auth/drive</li>
	 * <li>https://www.googleapis.com/auth/drive.apps.readonly</li>
	 * <li>https://www.googleapis.com/auth/drive.readonly</li>
	 * <li>https://www.googleapis.com/auth/drive.readonly.metadata</li>
	 * See <a href="https://developers.google.com/drive/scopes">https://developers.google.com/drive/scopes</a>
	 * for details about the different scopes
	 * @return {@link DriveOperations} for the authenticated user
	 */
	DriveOperations driveOperations();

	/**
	 * Applies OAuth2 authentication to an existing GData client implementation
	 * by adding "Authorization" header. Use this method if you want to use
	 * Spring Social Google with existing GData client code, or if you want to
	 * work with APIs which are not yet implemented in the library.
	 * 
	 * @see <a href="http://code.google.com/p/gdata-java-client/">GData Java Client</a>
	 * 
	 * @param client
	 *            subclass of externally instantiated {@link GoogleService} to
	 *            which OAuth2 authentication will be applied
	 *            http://code.google.com/p/gdata-java-client/
	 */
	void applyAuthentication(Object client);
	
	/**
	 * Returns the access token, allowing interoperability with other libraries
	 * 
	 * @see <a href="http://code.google.com/p/google-api-java-client/">Google APIs Client Library for Java</a>
	 * 
	 * @return The OAuth2 access token
	 */
	String getAccessToken();
}
