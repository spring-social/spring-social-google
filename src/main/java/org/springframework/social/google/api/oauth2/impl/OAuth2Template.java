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
package org.springframework.social.google.api.oauth2.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.oauth2.OAuth2Operations;
import org.springframework.social.google.api.oauth2.UserInfo;
import org.springframework.web.client.RestTemplate;

public class OAuth2Template extends AbstractGoogleApiOperations implements OAuth2Operations {

  private static final String USERINFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

  public OAuth2Template(final RestTemplate restTemplate, final boolean isAuthorized) {
    super(restTemplate, isAuthorized);
  }

  @Override
  public UserInfo getUserinfo() {
    return getEntity(USERINFO_URL, UserInfo.class);
  }

}
