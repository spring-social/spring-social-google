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

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.util.Date;

import org.springframework.social.google.api.ApiEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Abstract superclass for specific activity types.
 * 
 * @see <a href="https://developers.google.com/+/api/moment-types/">Moment
 *      Types</a>
 * @author Gabriel Axel
 * 
 */
@JsonTypeInfo(property = "type", include = As.PROPERTY, use = Id.NAME)
@JsonSubTypes({ @Type(AddActivity.class), @Type(BuyActivity.class),
		@Type(CheckInActivity.class), @Type(CommentActivity.class),
		@Type(CreateActivity.class), @Type(DiscoverActivity.class),
		@Type(ListenActivity.class), @Type(ReserveActivity.class),
		@Type(ReviewActivity.class), @Type(WantActivity.class) })
public abstract class Moment extends ApiEntity {

	@JsonProperty
	private MomentTarget target;

	@JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	private Date startDate;

	public Moment() {
	}

	public Moment(String targetUrl) {
		target = new MomentTarget(targetUrl);
	}

	public MomentTarget getTarget() {
		return target;
	}

	public Date getStartDate() {
		return startDate;
	}

}
