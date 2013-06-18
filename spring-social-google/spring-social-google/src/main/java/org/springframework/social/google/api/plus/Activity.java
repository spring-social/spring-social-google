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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.google.api.ApiEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Model class representing a Google+ activity
 * @author Gabriel Axel
 */
public class Activity extends ApiEntity {
	
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
	
	@JsonTypeInfo(property = "objectType", include = As.PROPERTY, use = Id.NAME)
	@JsonSubTypes({ @Type(Article.class), @Type(Photo.class), @Type(Video.class),
			@Type(Album.class), @Type(Event.class), @Type(Hangout.class),
			@Type(Place.class) })
	public static abstract class Attachment {

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
	
	@JsonTypeName("article")
	public static class Article extends Attachment {
	}
	
	@JsonTypeName("album")
	public static class Album extends Attachment {
	}
	
	@JsonTypeName("event")
	public static class Event extends Attachment {
	}
	
	@JsonTypeName("hangout")
	public static class Hangout extends Attachment {
	}
	
	@JsonTypeName("photo")
	public static class Photo extends Attachment {
	}
	
	@JsonTypeName("place")
	public static class Place extends Attachment {
	}
	
	@JsonTypeName("video")
	public static class Video extends Attachment {
	}
	
	public static class ActivityObject {
		
		public static class TotalItemsWrapper {
			
			@JsonProperty
			private int totalItems;
		}
		
		@JsonProperty
		private String content;
		
		@JsonProperty
		private List<Attachment> attachments;
		
		@JsonProperty
		private TotalItemsWrapper plusoners;
		
		@JsonProperty
		private TotalItemsWrapper resharers;
		
		@JsonProperty
		private TotalItemsWrapper replies;

	}
	
	private String title;
	
	private Date published;
	
	private Date updated;
	
	private String url;
	
	@JsonProperty
	private Person actor;
	
	@JsonProperty
	private ActivityObject object;
	
	public String getTitle() {
		return title;
	}

	public Date getPublished() {
		return published;
	}

	public Date getUpdated() {
		return updated;
	}
	
	public String getUrl() {
		return url;
	}

	public Person getActor() {
		return actor;
	}
	
	public String getContent() {
		return object.content;
	}
	
	public List<Attachment> getAttachments() {
		return object.attachments == null ? new ArrayList<Attachment>() : object.attachments;
	}

	public int getPlusOners() {
		return object.plusoners.totalItems;
	}

	public int getResharers() {
		return object.resharers.totalItems;
	}
	
	public int getReplies() {
		return object.replies.totalItems;
	}
}
