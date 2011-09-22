package org.springframework.social.google.api.activity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

@JsonTypeInfo(property="objectType", include=As.PROPERTY, use=Id.NAME)
@JsonSubTypes({@Type(Article.class), @Type(Photo.class), @Type(Video.class)})
public abstract class Attachment {

	public static class PreviewImage {
		
		protected final String url;
		protected final String contentType;
		
		@JsonCreator
		public PreviewImage(@JsonProperty("url") String url, @JsonProperty("type") String contentType) {
			this.url = url;
			this.contentType = contentType;
		}
		
	}
	
	private final String url;
	private final String displayName;
	private final String content;
	private final PreviewImage previewImage;
	
	protected Attachment(@JsonProperty("type") String url, @JsonProperty("displayName") String displayName,
			@JsonProperty("content") String content, @JsonProperty("image") PreviewImage previewImage) {
		this.url = url;
		this.displayName = displayName;
		this.content = content;
		this.previewImage = previewImage;
	}

	public String getUrl() {
		return url;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public String getContent() {
		return content;
	}
	
	public String getPreviewImageUrl() {
		return previewImage == null ? null : previewImage.url;
	}
	
	public String getPreviewImageContentType() {
		return previewImage == null ? null : previewImage.contentType;
	}
}
