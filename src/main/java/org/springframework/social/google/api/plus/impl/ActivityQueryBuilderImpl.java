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
package org.springframework.social.google.api.plus.impl;

import org.springframework.social.google.api.plus.ActivitiesOrder;
import org.springframework.social.google.api.plus.ActivitiesPage;
import org.springframework.social.google.api.plus.ActivityQueryBuilder;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link ActivityQueryBuilder} implementation.
 * @author Gabriel Axel
 */
public class ActivityQueryBuilderImpl extends ApiQueryBuilderImpl<ActivityQueryBuilder, ActivitiesPage>
  implements ActivityQueryBuilder {

  public ActivityQueryBuilderImpl(final RestTemplate restTemplate) {
    super("https://www.googleapis.com/plus/v1/activities", ActivitiesPage.class, restTemplate);
  }

  @Override
  public ActivityQueryBuilder searchFor(final String text) {
    return appendQueryParam("query", encode(text));
  }

  @Override
  public ActivityQueryBuilder orderBy(final ActivitiesOrder order) {
    return appendQueryParam("orderBy", order);
  }
}
