package org.springframework.social.google.api.plus.person;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.springframework.social.google.api.plus.GooglePlusEntity;

//@JsonTypeName("plus#person")
public class BasePerson extends GooglePlusEntity {

	private final String displayName;
	private final String profileUrl;
	private final String imageUrl;
	
	public static class Image {
		
		private final String url;
		
		@JsonCreator
		public Image(@JsonProperty("url") String url) {
			this.url = url;
		}
	}	
	
	@JsonCreator
	public BasePerson(
			@JsonProperty("id") String id, 
			@JsonProperty("displayName") String displayName, 
			@JsonProperty("url") String profileUrl,
			@JsonProperty("image") Image image) {
		super(id);
		this.displayName = displayName;
		this.profileUrl = profileUrl;
		this.imageUrl = image.url;
	}
	
	@Override
	public String toString() {
		return displayName;
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