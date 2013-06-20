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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(property = "type", include = As.PROPERTY, use = Id.NAME)
@JsonSubTypes({ @Type(AddActivity.class), @Type(BuyActivity.class),
		@Type(CheckInActivity.class), @Type(CommentActivity.class),
		@Type(CreateActivity.class), @Type(DiscoverActivity.class),
		@Type(ListenActivity.class), @Type(ReserveActivity.class),
		@Type(ReviewActivity.class), @Type(WantActivity.class) })
public abstract class Moment {

	public static class Target {
		
		@JsonProperty
		String url;
		
		@JsonProperty
		String id;
		
		@JsonProperty
		public String name;
		
		@JsonProperty
		public String description;
		
		@JsonProperty
		public String image;
	}
	
	@JsonIgnore
	public void setId(String id) {
		target.id = id;
	}

	@JsonProperty
	public Target target;

	@JsonIgnore
	public String getTargetUrl() {
		return target.url;
	}
	
	@JsonIgnore
	public String getId() {
		return target.id;
	}

	public Moment() {
	}

	public Moment(String targetUrl) {
		target = new Target();
		target.url = targetUrl;
	}
}
