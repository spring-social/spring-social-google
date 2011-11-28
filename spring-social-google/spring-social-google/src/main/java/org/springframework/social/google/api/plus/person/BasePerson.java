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
package org.springframework.social.google.api.plus.person;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * Model class representing basic person details (for a list of people or when 
 * a person is embedded in another entity).
 * @author Gabriel Axel
 */
public class BasePerson {

	public static class Image {
		@JsonProperty
		private String url;
	}
	
	private String id;
	private String displayName;
	private String url;
	
	@JsonProperty
	private Image image;
	
	@Override
	public String toString() {
		return displayName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getImageUrl() {
		return image.url;
	}

}