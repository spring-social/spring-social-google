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
package org.springframework.social.google.api.tasks;

import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a task list
 * @author Gabriel Axel
 */
public class TaskList extends ApiEntity {

  private String title;

  public TaskList() {
  }

  public TaskList(final String id, final String title) {
    super(id);
    this.title = title;
  }

  public TaskList(final String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }
}
