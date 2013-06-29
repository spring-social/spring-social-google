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

import static org.springframework.social.google.api.plus.moments.MomentTypes.ADD_ACTIVITY;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Model class representing a generic fallback activity type.
 * 
 * @see {@link https://developers.google.com/+/api/moment-types/add-activity}
 * @author Gabriel Axel
 * 
 */
@JsonTypeName(ADD_ACTIVITY)
public class AddActivity extends Moment {

	public AddActivity() {
	}

	public AddActivity(String targetUrl) {
		super(targetUrl);
	}
}
