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

import static org.springframework.social.google.api.plus.moments.MomentTypes.WANT_ACTIVITY;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Activity representing an indecation by the user that they want something,
 * such as if they add a product to a wishlist, a movie to a watchlist, or a
 * book to a reading list
 *
 * @see <a
 *      href="https://developers.google.com/+/api/moment-types/want-activity">Want
 *      Activity</a>
 * @author Gabriel Axel
 *
 */
@JsonTypeName(WANT_ACTIVITY)
public class WantActivity extends Moment {

  protected WantActivity() {
  }

  public WantActivity(final String targetUrl) {
    super(targetUrl);
  }
}
