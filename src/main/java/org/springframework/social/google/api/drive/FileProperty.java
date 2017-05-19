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
package org.springframework.social.google.api.drive;

import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a custom property set on a file in Google Drive
 *
 * @author Will Abson
 */
public class FileProperty extends ApiEntity {

  private String key;

  private String value;

  private PropertyVisibility visibility;

  public FileProperty() {
  }

  public FileProperty(final String key, final String value) {
    this.key = key;
    this.value = value;
  }

  public FileProperty(final String key, final String value, final PropertyVisibility visibility) {
    this.key = key;
    this.value = value;
    this.visibility = visibility;
  }

  public String getKey() {
    return key;
  }

  public void setKey(final String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

  public PropertyVisibility getVisibility() {
    return visibility;
  }

  public void setVisibility(final PropertyVisibility visibility) {
    this.visibility = visibility;
  }

}
