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
package org.springframework.social.google.api.legacyprofile;

/**
 * Defines a single operation for retrieving the authenticated user legacy profile.
 * Requires OAuth2 scopes https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email
 * @author Gabriel Axel
 */
public interface LegacyProfileOperations {

	/**
	 * Retrieves authenticated user legacy profile.
	 * @return {@link LegacyGoogleProfile} for the authenticated user
	 */
	LegacyGoogleProfile getUserProfile();
}
