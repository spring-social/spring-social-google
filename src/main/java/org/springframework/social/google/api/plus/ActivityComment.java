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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a comment.
 *
 * @author Gabriel Axel
 */
public class ActivityComment extends ApiEntity {

  private Date published;
  private Date updated;
  @JsonProperty
  private CommentObject object;
  private Person actor;

  public Date getPublished() {
    return published;
  }

  public Date getUpdated() {
    return updated;
  }

  public String getContent() {
    return object != null ? object.content : null;
  }

  public Person getActor() {
    return actor;
  }

  public static class CommentObject {

    @JsonProperty
    private String content;
  }
}
