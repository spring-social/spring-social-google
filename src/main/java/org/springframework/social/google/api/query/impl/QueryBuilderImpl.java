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
package org.springframework.social.google.api.query.impl;

import static org.springframework.util.StringUtils.hasText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.springframework.social.google.api.query.QueryBuilder;

/**
 * Abstract superclass for {@link QueryBuilder} implementations.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public abstract class QueryBuilderImpl<Q extends QueryBuilder<?, T>, T> implements QueryBuilder<Q, T> {

  private static final Format dateFormatter;

  static {
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    dateFormatter = simpleDateFormat;
  }

  protected String feedUrl;
  private Map<String, String> params = new LinkedHashMap<>();

  protected QueryBuilderImpl() {
  }

  protected QueryBuilderImpl(final String feedUrl) {
    this.feedUrl = feedUrl;
  }

  protected static String encode(final String text) {
    try {
      return URLEncoder.encode(text, "UTF-8");
    } catch (final UnsupportedEncodingException e) {
      throw new IllegalStateException(e);
    }
  }

  @SuppressWarnings("unchecked")
  protected Q castThis() {
    return (Q) this;
  }

  @Override
  public Q maxResultsNumber(final int maxResults) {
    appendQueryParam("maxResults", maxResults);
    return castThis();
  }

  protected Q appendQueryParam(final String name, final Date value) {
    if (value != null) {
      appendQueryParam(name, dateFormatter.format(value));
    }
    return castThis();
  }

  protected Q appendQueryParam(final String name, final int value) {
    if (value > 0) {
      appendQueryParam(name, String.valueOf(value));
    }
    return castThis();
  }

  protected Q appendQueryParam(final String name, final boolean value) {
    if (value) {
      appendQueryParam(name, "true");
    }
    return castThis();
  }

  protected Q appendQueryParam(final String name, final Object value) {
    if (value != null) {
      appendQueryParam(name, value.toString());
    }
    return castThis();
  }

  protected Q appendQueryParam(final StringBuilder sb, final String name, final Enum<?> value) {
    if (value != null) {
      appendQueryParam(name, value.name().toLowerCase());
    }
    return castThis();
  }

  protected Q appendQueryParam(final String name, final String value) {
    if (hasText(value)) {
      params.put(name, value);
    }
    return castThis();
  }

  protected String build() {

    final StringBuilder sb = new StringBuilder(feedUrl);
    if (!params.isEmpty() && feedUrl.indexOf('?') < 0) {
      sb.append('?');
    }

    for (final Entry<String, String> param : params.entrySet()) {
      if (sb.charAt(sb.length() - 1) != '?') {
        sb.append('&');
      }
      sb.append(param.getKey()).append('=').append(param.getValue());
    }

    return sb.toString();
  }
}
