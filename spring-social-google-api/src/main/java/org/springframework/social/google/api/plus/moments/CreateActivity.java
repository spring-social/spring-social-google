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

import static org.springframework.social.google.api.plus.moments.MomentTypes.CREATE_ACTIVITY;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Activity representing creation of creative work such as a photo, blog entry,
 * or microblog post
 * 
 * @see <a
 *      href="https://developers.google.com/+/api/moment-types/create-activity">Create
 *      Activity</a>
 * @author Gabriel Axel
 * 
 */
@JsonTypeName(CREATE_ACTIVITY)
public class CreateActivity extends Moment {

	protected CreateActivity() {
	}

	public CreateActivity(String targetUrl) {
		super(targetUrl);
	}
}
