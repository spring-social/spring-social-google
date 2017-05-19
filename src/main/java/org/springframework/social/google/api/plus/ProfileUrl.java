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
package org.springframework.social.google.api.plus;

import static org.springframework.util.ObjectUtils.nullSafeEquals;
import static org.springframework.util.ObjectUtils.nullSafeHashCode;

/**
 * URL in a person's Google+ profile
 * @author Gabriel Axel
 *
 */
public class ProfileUrl {

  private String value;

  private String label;

  private UrlType type;

  ProfileUrl() {

  }

  ProfileUrl(final String value, final String label, final UrlType type) {
    this.value = value;
    this.label = label;
    this.type = type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + nullSafeHashCode(value);
    result = prime * result + nullSafeHashCode(label);
    result = prime * result + nullSafeHashCode(type);
    return result;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProfileUrl)) {
      return false;
    }
    final ProfileUrl other = (ProfileUrl) o;
    return nullSafeEquals(value, other.value) &&
      nullSafeEquals(label, other.label) &&
      nullSafeEquals(type, other.type);

  }

  @Override
  public String toString() {
    return value;
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    return label;
  }

  public UrlType getType() {
    return type;
  }

}
