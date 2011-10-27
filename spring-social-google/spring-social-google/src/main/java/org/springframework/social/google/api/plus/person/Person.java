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

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model class representing a full Google profile
 * @author Gabriel Axel
 */
public class Person extends BasePerson {

	private final String gender;
	private final String aboutMe;
	private final String relationshipStatus;
	private final List<ProfileURL> urls;
	private final List<Organization> organizations;
	private final List<PlaceLived> placesLived;
	
	@JsonCreator
	public Person(
			@JsonProperty("id") String id, 
			@JsonProperty("displayName") String displayName, 
			@JsonProperty("gender") String gender,
			@JsonProperty("aboutMe") String aboutMe, 
			@JsonProperty("relationshipStatus") String relationshipStatus, 
			@JsonProperty("url") String profileUrl,
			@JsonProperty("image") Image image,
			@JsonProperty("urls") List<ProfileURL> urls,
			@JsonProperty("organizations") List<Organization> organizations,
			@JsonProperty("placesLived") List<PlaceLived> placesLived) {
		
		super(id, displayName, profileUrl, image);
		
		this.gender = gender;
		this.aboutMe = aboutMe;
		this.relationshipStatus = relationshipStatus;
		this.urls = urls;
		this.organizations = organizations;
		this.placesLived = placesLived;
	}
	
	public String getGender() {
		return gender;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public List<ProfileURL> getUrls() {
		return urls;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public List<PlaceLived> getPlacesLived() {
		return placesLived;
	}
	
}
