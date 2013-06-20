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

import static org.springframework.social.google.api.plus.moments.MomentTypes.RESERVE_ACTIVITY;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(RESERVE_ACTIVITY)
public class ReviewActivity extends Moment {
	
	protected class Result {

		String url;
		
		String text;
		
		String name;
		
		ReviewRating reviewRating;
		
		String getType() {
			return "http://schema.org/Review";
		}
		
	}
	
	public static class ReviewRating {
		
		private float ratingValue;
		
		private float bestRating;
		
		private float worstRating;
		
		protected ReviewRating() {}

		public ReviewRating(float ratingValue, float bestRating,
				float worstRating) {
			this.ratingValue = ratingValue;
			this.bestRating = bestRating;
			this.worstRating = worstRating;
		}

		public float getRatingValue() {
			return ratingValue;
		}

		public float getBestRating() {
			return bestRating;
		}

		public float getWorstRating() {
			return worstRating;
		}

	}
	
	private Result result;

	protected ReviewActivity() {}
	
	public ReviewActivity(String targetUrl, String resultUrl, String resultText) {
		super(targetUrl);
		result = new Result();
		result.url = resultUrl;
		result.text = resultText;
	}
	
	public ReviewActivity(String targetUrl, String resultUrl, String resultText, String resultName, ReviewRating reviewRating) {
		this(targetUrl, resultUrl, resultText);
		result.name = resultName;
		result.reviewRating = reviewRating;
	}
	
	public String getResultUrl() {
		return result.url;
	}
	
	public String getResultText() {
		return result.text;
	}
	
	public String getResultName() {
		return result.name;
	}
	
	public ReviewRating getReviewRating() {
		return result.reviewRating;
	}
}
