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
package org.springframework.social.google.api.query;

import java.util.List;

/**
 * API Query list result.
 * @author Gabriel Axel
 * @param <T> item type
 */
public abstract class ApiList<T> {

  private List<T> items;
  private String etag;

  protected ApiList() {
  }

  protected ApiList(final List<T> items) {
    this.items = items;
  }

  public List<T> getItems() {
    return items;
  }

  public String getEtag() {
    return etag;
  }

}
