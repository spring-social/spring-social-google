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
package org.springframework.social.google.api.plus.activity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * An abstract superclass for model classes representing Google+ activity attachments.
 * @author Gabriel Axel
 */
@JsonTypeInfo(property="objectType", include=As.PROPERTY, use=Id.NAME)
@JsonSubTypes({@Type(Article.class), @Type(Photo.class), @Type(Video.class), @Type(Album.class)})
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
