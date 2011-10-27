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
import org.springframework.social.google.api.gdata.contact.ContactOperations;
import org.springframework.social.google.api.gdata.picasa.PicasaOperations;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.social.google.api.plus.activity.ActivityOperations;
import org.springframework.social.google.api.plus.person.PersonOperations;

/**
* Interface specifying a basic set of operations for interacting with Google APIs.
* Implemented by {@link GoogleTemplate}.
* @author Gabriel Axel
*/
public interface Google extends ApiBinding {

	/**
	 * Retrieves {@link LegacyProfileOperations}, used for fetching user profile (predates Google+).
	 * Requires OAuth2 scopes https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email
	 * @return {@link LegacyProfileOperations} for the authenticated user
	 */
	LegacyProfileOperations userOperations();
	
	/**
	 * Retrieves {@link ContactOperations}, used for interacting with GData Contacts API.
	 * Requires OAuth2 scope https://www.google.com/m8/feeds
	 * @return {@link ContactOperations} for the authenticated user
	 */
	ContactOperations contactOperations();
	
	/**
	 * Retrieves {@link PersonOperations}, used for interacting with Google+ People API.
	 * Some methods require OAuth2 scope https://www.googleapis.com/auth/plus.me
	 * @return {@link PersonOperations} for the authenticated user if authenticated
	 */
	PersonOperations personOperations();
	
	/**
	 * Retrieves {@link PicasaOperations}, used for interacting with Picasa Web Albums API.
	 * Requires OAuth2 scope https://picasaweb.google.com/data/
	 * @return {@link PicasaOperations} for the authenticated user
	 */
	PicasaOperations picasaOperations();
	
	/**
	 * Retrieves {@link ActivityOperations}, used for interacting with Google+ Activities API.
	 * Some methods require OAuth2 scope https://www.googleapis.com/auth/plus.me
	 * @return {@link ActivityOperations} for the authenticated user if authenticated
	 */
	ActivityOperations activityOperations();
}
