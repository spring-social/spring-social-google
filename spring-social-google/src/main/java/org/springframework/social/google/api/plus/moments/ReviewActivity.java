/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.plus.moments;

import static org.springframework.social.google.api.plus.moments.MomentTypes.REVIEW_ACTIVITY;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Activity representing a submission of a review, such as a review of a
 * product, creative work, restaurant or other service
 * 
 * @see {@link 
 * 	https://developers.google.com/+/api/moment-types/review-activity}
 * @author Gabriel Axel
 * 
 */
@JsonTypeName(REVIEW_ACTIVITY)
public class ReviewActivity extends Moment {

	protected static class Result {

		@JsonProperty
		String url;

		@JsonProperty
		String text;

		@JsonProperty
		String name;

		@JsonProperty
		Rating reviewRating;

		@JsonGetter
		String getType() {
			return "http://schema.org/Review";
		}

	}

	@JsonProperty
	private Result result;

	protected ReviewActivity() {
	}

	public ReviewActivity(String targetUrl, String url, String text) {
		super(targetUrl);
		result = new Result();
		result.url = url;
		result.text = text;
	}

	public ReviewActivity(String targetUrl, String url, String text,
			String name, Rating reviewRating) {
		this(targetUrl, url, text);
		result.name = name;
		result.reviewRating = reviewRating;
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

	@JsonIgnore
	public Rating getReviewRating() {
		return result.reviewRating;
	}
}
