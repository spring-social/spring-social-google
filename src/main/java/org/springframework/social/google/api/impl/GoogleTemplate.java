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
package org.springframework.social.google.api.impl;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.calendar.CalendarOperations;
import org.springframework.social.google.api.calendar.impl.CalendarTemplate;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.drive.impl.DriveTemplate;
import org.springframework.social.google.api.oauth2.OAuth2Operations;
import org.springframework.social.google.api.oauth2.impl.OAuth2Template;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.api.plus.impl.PlusTemplate;
import org.springframework.social.google.api.tasks.TaskOperations;
import org.springframework.social.google.api.tasks.impl.TaskTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestOperations;

/**
 * <p>
 * The central class for interacting with Google APIs.
 * </p>
 * <p>
 * Most of the APIs, specifically all GData APIs and Google+ usage of "me", require OAuth2 authentication.
 * To use methods that require OAuth2 authentication, {@link GoogleTemplate} must be constructed with
 * an access token which is authorized for the appropriate scope.
 * For using Google+ without authenticating, {@link GoogleTemplate} default constructor can be used.
 * </p>
 * @author Gabriel Axel
 */
public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

  private String accessToken;
  private OAuth2Operations oauth2Operations;
  private PlusOperations plusOperations;
  private TaskOperations taskOperations;
  private DriveOperations driveOperations;
  private CalendarOperations calendarOperations;

  /**
   * Creates a new instance of GoogleTemplate.
   * This constructor creates a new GoogleTemplate able to perform unauthenticated operations against Google+.
   */
  public GoogleTemplate() {
    initialize();
  }

  /**
   * Creates a new instance of GoogleTemplate.
   * This constructor creates the FacebookTemplate using a given access token.
   * @param accessToken an access token granted by Google after OAuth2 authentication
   */
  public GoogleTemplate(final String accessToken) {
    super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    this.accessToken = accessToken;
    initialize();
  }

  private void initialize() {
    oauth2Operations = new OAuth2Template(getRestTemplate(), isAuthorized());
    plusOperations = new PlusTemplate(getRestTemplate(), isAuthorized());
    taskOperations = new TaskTemplate(getRestTemplate(), isAuthorized());
    driveOperations = new DriveTemplate(getRestTemplate(), isAuthorized());
    calendarOperations = new CalendarTemplate(getRestTemplate(), isAuthorized());
  }

  @Override
  protected List<HttpMessageConverter<?>> getMessageConverters() {

    final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    objectMapper.setSerializationInclusion(NON_NULL);
    jsonConverter.setObjectMapper(objectMapper);

    final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    formHttpMessageConverter.addPartConverter(jsonConverter);

    final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(jsonConverter);
    messageConverters.add(new ByteArrayHttpMessageConverter());
    messageConverters.add(formHttpMessageConverter);
    messageConverters.add(new ResourceHttpMessageConverter());
    return messageConverters;
  }

  @Override
  protected OAuth2Version getOAuth2Version() {
    return OAuth2Version.BEARER;
  }

  @Override
  public PlusOperations plusOperations() {
    return plusOperations;
  }

  @Override
  public TaskOperations taskOperations() {
    return taskOperations;
  }

  @Override
  public DriveOperations driveOperations() {
    return driveOperations;
  }

  @Override
  public CalendarOperations calendarOperations() {
    return calendarOperations;
  }

  @Override
  public String getAccessToken() {
    return accessToken;
  }

  @Override
  public OAuth2Operations oauth2Operations() {
    return oauth2Operations;
  }

  @Override
  public RestOperations restOperations() {
    return getRestTemplate();
  }
}
