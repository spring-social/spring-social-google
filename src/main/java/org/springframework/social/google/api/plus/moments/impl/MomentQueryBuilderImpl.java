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
package org.springframework.social.google.api.plus.moments.impl;

import org.springframework.social.google.api.plus.moments.MomentQueryBuilder;
import org.springframework.social.google.api.plus.moments.MomentsPage;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/**
 * {@link MomentQueryBuilder} implementation.
 *
 * @author Gabriel Axel
 */
public class MomentQueryBuilderImpl extends
  ApiQueryBuilderImpl<MomentQueryBuilder, MomentsPage> implements
  MomentQueryBuilder {

  public MomentQueryBuilderImpl(final String feedUrl, final RestTemplate restTemplate) {
    super(feedUrl, MomentsPage.class, restTemplate);
  }
}
