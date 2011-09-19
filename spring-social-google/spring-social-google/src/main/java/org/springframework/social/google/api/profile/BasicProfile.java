package org.springframework.social.google.api.profile;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class BasicProfile {

	private final String id;
	private final String displayName;
	private final String profileUrl;
	private final String imageUrl;
	
	static class Image {
		
		private final String url;
		
		@JsonCreator
		Image(@JsonProperty("url") String url) {
			this.url = url;
		}
	}	
	
	@JsonCreator
	public BasicProfile(
			@JsonProperty("id") String id, 
			@JsonProperty("displayName") String displayName, 
			@JsonProperty("url") String profileUrl,
			@JsonProperty("image") Image image) {
		this.id = id;
		this.displayName = displayName;
		this.profileUrl = profileUrl;
		this.imageUrl = image.url;
	}
	
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
	
	public String getProfileUrl() {
		return profileUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

}