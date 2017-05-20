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
package org.springframework.social.google.api.tasks.impl;

import static org.springframework.social.google.api.tasks.impl.TaskTemplate.TASK_LISTS_URL;

import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.social.google.api.tasks.TaskListQueryBuilder;
import org.springframework.social.google.api.tasks.TaskListsPage;
import org.springframework.web.client.RestTemplate;

/**
 * {@link TaskListQueryBuilder} implementation.
 * @author Gabriel Axel
 */
class TaskListQueryBuilderImpl extends ApiQueryBuilderImpl<TaskListQueryBuilder, TaskListsPage> implements
  TaskListQueryBuilder {

  public TaskListQueryBuilderImpl(final RestTemplate restTemplate) {
    super(TASK_LISTS_URL, TaskListsPage.class, restTemplate);
  }
}
