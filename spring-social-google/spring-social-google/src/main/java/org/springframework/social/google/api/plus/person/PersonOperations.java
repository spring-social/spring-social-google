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
package org.springframework.social.google.api.plus.person;

/**
 * Defines operations for reading Google+ people.
 * To use "me" as user ID, requires OAuth2 scope https://www.googleapis.com/auth/plus.me
 * @author Gabriel Axel
 */
public interface PersonOperations {

	/**
	 * Retrieves a user's Google profile.
	 * @param id user ID or "me"
	 * @return the retrieved {@link Person}
	 */
	Person getPerson(String id);
	
	/**
	 * Retrieves the authenticated user's Google profile.
	 * @return the retrieved {@link Person}
	 */
	Person getGoogleProfile();
	
	/**
	 * Creates a {@link PersonQueryBuilder}.
	 * @return a new {@link PersonQueryBuilder}
	 */
	PersonQueryBuilder personQuery();
}
