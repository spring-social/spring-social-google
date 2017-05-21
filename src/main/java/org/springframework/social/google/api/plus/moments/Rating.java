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

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Review rating
 *
 * @see <a href="http://schema.org/Rating">Rating</a>
 * @author Gabriel Axel
 *
 */
public class Rating {

  private String ratingValue;

  private String bestRating;

  private String worstRating;

  protected Rating() {
  }

  public Rating(final String ratingValue, final String bestRating, final String worstRating) {
    this.ratingValue = ratingValue;
    this.bestRating = bestRating;
    this.worstRating = worstRating;
  }

  @JsonGetter
  protected String getType() {
    return "http://schema.org/Rating";
  }

  public String getRatingValue() {
    return ratingValue;
  }

  public String getBestRating() {
    return bestRating;
  }

  public String getWorstRating() {
    return worstRating;
  }
}
