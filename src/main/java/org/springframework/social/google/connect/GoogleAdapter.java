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
package org.springframework.social.google.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.oauth2.UserInfo;

/**
 * Google ApiAdapter implementation.
 *
 * @author Gabriel Axel
 */
public class GoogleAdapter implements ApiAdapter<Google> {
  /**
   * Attempt to see if the connection is valid.
   */
  public boolean test(final Google google) {
    try {
      google.oauth2Operations().getUserinfo();
      return true;
    } catch (final ApiException e) {
      return false;
    }
  }

  /**
   * Set a value on the connection.
   */
  public void setConnectionValues(final Google google, final ConnectionValues values) {
    final UserInfo userInfo = google.oauth2Operations().getUserinfo();
    values.setProviderUserId(userInfo.getId());
    values.setDisplayName(userInfo.getName());
    values.setProfileUrl(userInfo.getLink());
    values.setImageUrl(userInfo.getPicture());
  }

  /**
   * Return the current user profile.
   */
  public UserProfile fetchUserProfile(final Google google) {
    final UserInfo userInfo = google.oauth2Operations().getUserinfo();
    return new UserProfileBuilder().setUsername(userInfo.getId())
      .setEmail(userInfo.getEmail())
      .setName(userInfo.getName())
      .setFirstName(userInfo.getGivenName())
      .setLastName(userInfo.getFamilyName()).build();
  }

  public void updateStatus(final Google google, final String message) {
    throw new UnsupportedOperationException();
  }
}
