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
 * Organization from a Google+ profile
 *
 * @author Gabriel Axel
 *
 */
public class Organization {

  private String name;

  private String title;

  private String type;

  private String startDate;

  private String endDate;

  private boolean primary;

  Organization() {

  }

  Organization(final String name, final String title, final String type, final String startDate,
               final String endDate, final boolean primary) {
    this.name = name;
    this.title = title;
    this.type = type;
    this.startDate = startDate;
    this.endDate = endDate;
    this.primary = primary;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + nullSafeHashCode(name);
    result = prime * result + nullSafeHashCode(title);
    result = prime * result + nullSafeHashCode(type);
    result = prime * result + nullSafeHashCode(startDate);
    result = prime * result + nullSafeHashCode(endDate);
    result = prime * result + nullSafeHashCode(primary);
    return result;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Organization)) {
      return false;
    }
    final Organization other = (Organization) o;
    return nullSafeEquals(name, other.name) &&
      nullSafeEquals(title, other.title) &&
      nullSafeEquals(type, other.type) &&
      nullSafeEquals(startDate, other.startDate) &&
      nullSafeEquals(endDate, other.endDate) &&
      primary == other.primary;
  }

  @Override
  public String toString() {
    return name;
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public boolean isPrimary() {
    return primary;
  }
}
