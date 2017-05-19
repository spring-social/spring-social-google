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

import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.google.api.ApiEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract superclass for implementations that work with Google+ APIs.
 * @author Gabriel Axel
 */
public abstract class AbstractGoogleApiOperations {

  private final static Log logger = LogFactory.getLog(AbstractGoogleApiOperations.class);
  protected final RestTemplate restTemplate;
  protected final boolean isAuthorized;

  protected AbstractGoogleApiOperations(final RestTemplate restTemplate, final boolean isAuthorized) {
    this.restTemplate = restTemplate;
    this.isAuthorized = isAuthorized;

    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public void handleError(final ClientHttpResponse response) throws IOException {
        if (logger.isWarnEnabled()) {
          final String bodyText = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
          logger.warn("Google API REST response body:" + bodyText);
        }
      }
    });
  }

  protected void requireAuthorization() {
    if (!isAuthorized) {
      throw new MissingAuthorizationException("google");
    }
  }

  protected <T> T getEntity(final String url, final Class<T> type) {
    return restTemplate.getForObject(url, type);
  }

  @SuppressWarnings("unchecked")
  protected <T> T saveEntity(final String url, final T entity) {
    return (T) restTemplate.postForObject(url, entity, entity.getClass());
  }

  protected <T extends ApiEntity> T saveEntity(final String baseUrl, final T entity) {

    final String url;
    final HttpMethod method;

    if (hasText(entity.getId())) {
      url = baseUrl + '/' + entity.getId();
      method = PUT;
    } else {
      url = baseUrl;
      method = POST;
    }

    @SuppressWarnings("unchecked") final ResponseEntity<T> response =
      restTemplate.exchange(url, method, new HttpEntity<>(entity), (Class<T>) entity.getClass());

    return response.getBody();
  }

  protected void deleteEntity(final String baseUrl, final ApiEntity entity) {
    deleteEntity(baseUrl, entity.getId());
  }

  protected void deleteEntity(final String baseUrl, final String id) {
    restTemplate.delete(baseUrl + '/' + id);
  }

  protected <T> T patch(final String url, final Object request, final Class<T> responseType) {
    final ResponseEntity<T> response = restTemplate.exchange(url, PATCH, new HttpEntity<>(request), responseType);
    return response.getBody();
  }
}
