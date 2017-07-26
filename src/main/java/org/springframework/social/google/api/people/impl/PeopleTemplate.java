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
package org.springframework.social.google.api.people.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.people.PeopleOperations;
import org.springframework.social.google.api.people.PeoplePerson;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * {@link PeopleOperations} implementation.
 * @author Oscar Carballo
 */
public class PeopleTemplate extends AbstractGoogleApiOperations implements PeopleOperations {

  private static final String PEOPLE_URL = "https://people.googleapis.com/v1/people/";

  public PeopleTemplate(final RestTemplate restTemplate, final boolean isAuthorized) {
    super(restTemplate, isAuthorized);
  }

  @Override
  public PeoplePerson getPerson(String id) {
    return getEntity(buildUrl(PEOPLE_URL + id, ALL_FIELDS), PeoplePerson.class);
  }

  @Override
  public PeoplePerson getPerson(String id, String fields) {
    return getEntity(buildUrl(PEOPLE_URL + id, fields), PeoplePerson.class);
  }

  @Override
  public PeoplePerson getGoogleProfile() {
    return getPerson("me");
  }

  @Override
  public PeoplePerson getGoogleProfile(String fields) {
    return getPerson("me", fields);
  }

  private static String buildUrl(String url, String personFieldsValue) {
    return UriComponentsBuilder.fromHttpUrl(url)
      .queryParam("personFields", personFieldsValue)
      .build().toString();
  }
}
