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
package org.springframework.social.google.api.legacyprofile;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model class representing a legacy Google profile (predates Google+).
 * @author Gabriel Axel
 */
public class LegacyGoogleProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String id;
	private final String email;
	private final String name;
	private final String firstName;
	private final String lastName;
	private final String link;
	private final String profilePictureUrl;
	private final String gender;
	private final String locale;

	@JsonCreator
	public LegacyGoogleProfile(@JsonProperty("id") String id,
			@JsonProperty("email") String email,
			@JsonProperty("name") String name,
			@JsonProperty("given_name") String firstName,
			@JsonProperty("family_name") String lastName,
			@JsonProperty("link") String link,
			@JsonProperty("picture") String profilePictureUrl,
			@JsonProperty("gender") String gender,
			@JsonProperty("locale") String locale) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.link = link;
		this.profilePictureUrl = profilePictureUrl;
		this.gender = gender;
		this.locale = locale;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLink() {
		return link;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public String getGender() {
		return gender;
	}

	public String getLocale() {
		return locale;
	}

}
