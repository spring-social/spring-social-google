package org.springframework.social.google.api.profile;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class GoogleProfile extends BasicProfile {

	private final String gender;
	private final String aboutMe;
	private final String relationshipStatus;
	private final List<ProfileURL> urls;
	private final List<Organization> organizations;
	private final List<PlaceLived> placesLived;
	
	
	
	@JsonCreator
	public GoogleProfile(
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
