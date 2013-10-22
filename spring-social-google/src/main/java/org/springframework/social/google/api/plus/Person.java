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
package org.springframework.social.google.api.plus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.social.google.api.ApiEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Model class representing a full Google profile
 * 
 * @author Gabriel Axel
 */
public class Person extends ApiEntity {

	private static class Name {

		@JsonProperty
		private String givenName;

		@JsonProperty
		private String familyName;
	}

	public static class Image {

		@JsonProperty
		private String url;
	}
	
	private static class PlaceLived {

		@JsonProperty
		private String value;

		@JsonProperty
		private boolean primary;
	}
	
	@JsonProperty
	private Name name;

	private String displayName;

	@JsonProperty
	private Image image;

	private String thumbnailUrl;

	private Date birthday;

	private String gender;

	private String aboutMe;

	private String relationshipStatus;

	private List<ProfileUrl> urls;

	private List<Organization> organizations;

	private Map<String, Boolean> placesLived;

	@Override
	public String toString() {
		return displayName;
	}

	@JsonSetter
	private void setPlacesLived(List<PlaceLived> placesLivedAsList) {
		placesLived = new LinkedHashMap<String, Boolean>();
		if (placesLivedAsList != null) {
			for (PlaceLived placeLived : placesLivedAsList) {
				placesLived.put(placeLived.value, placeLived.primary);
			}
		}
	}

	public String getGivenName() {
		return name == null ? null : name.givenName;
	}

	public String getFamilyName() {
		return name == null ? null : name.familyName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getImageUrl() {
		if (thumbnailUrl != null) {
			return thumbnailUrl;
		}
		if (image != null) {
			return image.url;
		}
		return null;
	}

	public Date getBirthday() {
		return birthday;
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

	public List<ProfileUrl> getUrls() {
		return urls;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public Map<String, Boolean> getPlacesLived() {
		return placesLived;
	}

}
