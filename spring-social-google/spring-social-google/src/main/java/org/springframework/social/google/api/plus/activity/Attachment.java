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

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * An abstract superclass for model classes representing Google+ activity attachments.
 * @author Gabriel Axel
 */
@JsonTypeInfo(property="objectType", include=As.PROPERTY, use=Id.NAME)
@JsonSubTypes({@Type(Article.class), @Type(Photo.class), @Type(Video.class), 
	@Type(Album.class), @Type(Event.class)})
public abstract class Attachment {

	public static class PreviewImage {
		
		@JsonProperty
		protected String url;
		
		@JsonProperty
		protected String type;
		
		@JsonProperty
		protected int height;
		
		@JsonProperty
		protected int width;
	}
	
	private String url;
	private String displayName;
	private String content;
	
	@JsonProperty
	private PreviewImage image;
	
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
		return image == null ? null : image.url;
	}
	
	public String getPreviewImageContentType() {
		return image == null ? null : image.type;
	}
}
