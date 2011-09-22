package org.springframework.social.google.api.activity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("photo")
public class Photo extends Attachment {

	public static class FullImage extends PreviewImage {
		
		private final int height;
		private final int width;
		
		public FullImage(@JsonProperty("url") String url, @JsonProperty("type") String contentType, 
				@JsonProperty("height") int height, @JsonProperty("width") int width) {
			super(url, contentType);
			this.height = height;
			this.width = width;
		}
		
	}
	
	private final FullImage fullImage;

	public Photo(@JsonProperty("url") String url, @JsonProperty("displayName") String displayName, 
			@JsonProperty("content") String content, @JsonProperty("image") PreviewImage previewImage, 
			@JsonProperty("fullImage") FullImage fullImage) {
		super(url, displayName, content, previewImage);
		this.fullImage = fullImage;
	}
	
	public String getFullImageUrl() {
		return fullImage.url;
	}
	
	public String getFullImageContentType() {
		return fullImage.contentType;
	}
	
	public int getFullImageHeight() {
		return fullImage.height;
	}
	
	public int getFullImageWidth() {
		return fullImage.width;
	}
}
