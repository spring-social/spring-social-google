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
package org.springframework.social.google.api.plus.moments;

import static org.springframework.social.google.api.plus.moments.MomentTypes.COMMENT_ACTIVITY;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Activity representing a comment on an article, blog entry, or other creative
 * work
 *
 * @see <a
 *      href="https://developers.google.com/+/api/moment-types/comment-activity">Comment
 *      Activity</a>
 * @author Gabriel Axel
 *
 */
@JsonTypeName(COMMENT_ACTIVITY)
public class CommentActivity extends Moment {

  @JsonProperty
  private Result result;

  protected CommentActivity() {
  }

  public CommentActivity(final String targetUrl, final String resultUrl, final String resultText) {
    super(targetUrl);
    result = new Result();
    result.url = resultUrl;
    result.text = resultText;
  }

  public CommentActivity(final String targetUrl, final String resultUrl,
                         final String resultText, final String resultName) {
    this(targetUrl, resultUrl, resultText);
    result.name = resultName;
  }

  @JsonIgnore
  public String getResultUrl() {
    return result.url;
  }

  @JsonIgnore
  public String getResultText() {
    return result.text;
  }

  @JsonIgnore
  public String getResultName() {
    return result.name;
  }

  protected static class Result {

    @JsonProperty
    String url;

    @JsonProperty
    String text;

    @JsonProperty
    String name;

    @JsonGetter
    String getType() {
      return "http://schema.org/Comment";
    }

  }
}
