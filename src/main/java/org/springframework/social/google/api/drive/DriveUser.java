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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing a Google Drive user
 * @author Gabriel Axel
 */
public class DriveUser {

  private String displayName;
  @JsonProperty("isAuthenticatedUser")
  private boolean authenticatedUser;
  @JsonProperty
  private Picture picture;

  public String getDisplayName() {
    return displayName;
  }

  public String getPictureUrl() {
    return picture != null ? picture.url : null;
  }

  public boolean isAuthenticatedUser() {
    return authenticatedUser;
  }

  private static class Picture {

    @JsonProperty
    private String url;
  }
}
