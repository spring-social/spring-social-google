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

package org.springframework.social.google.api.oauth2;

import org.springframework.social.google.api.plus.Person;

/**
 * Defines operations for Google Accounts Authentication and Authorization. Requires OAuth scope(s) from the following:
 * <ul>
 * <li>openid</li>
 * <li>profile</li>
 * <li>email</li>
 * </ul>
 * See <a
 * href="https://developers.google.com/accounts/docs/OpenIDConnect">https://developers.google.com/accounts/docs/OpenIDConnect</a> for details about the different scopes
 *
 * @author Charles Maheu
 */
public interface OAuth2Operations {
    /**
     * Retrieves the authenticated user's Google user.
     *
     * @return the retrieved {@link Person}
     */
    UserInfo getUserinfo();
}
