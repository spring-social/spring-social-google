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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.person.BasePerson;

/**
 * Model class representing a Google+ activity
 * @author Gabriel Axel
 */
public class Activity {
	
	public static class ActivityObject {
		
		public static class TotalItemsWrapper {
			
			private final int totalItems;
			
			@JsonCreator
			public TotalItemsWrapper(@JsonProperty("totalItems") int totalItems) {
				this.totalItems = totalItems;
			}
		}
		
		private final String content;
		private final List<Attachment> attachments;
		private final int plusOners;
		private final int resharers;
		private final int replies;

		@JsonCreator
		public ActivityObject(@JsonProperty("content") String content,
				@JsonProperty("attachments") List<Attachment> attachments,
				@JsonProperty("plusoners") TotalItemsWrapper plusOnersWrapper,
				@JsonProperty("resharers") TotalItemsWrapper resharersWrapper,
				@JsonProperty("replies") TotalItemsWrapper repliesWrapper) {
			this.content = content;
			this.attachments = attachments;
			this.plusOners = plusOnersWrapper.totalItems;
			this.resharers = resharersWrapper.totalItems;
			this.replies = repliesWrapper.totalItems;
		}
	}
	
	

	private final String id;
	private final String title;
	private final Date published;
	private final Date updated;
	private final String url;
	private final BasePerson actor;
	private final ActivityObject object;
	
		
	@JsonCreator
	public Activity(
			@JsonProperty("id") String id, 
			@JsonProperty("title") String title, 
			@JsonProperty("published") Date published, 
			@JsonProperty("updated") Date updated,
			@JsonProperty("url") String url,
			@JsonProperty("actor") BasePerson actor,
			@JsonProperty("object") ActivityObject object) {
		this.id = id;
		this.title = title;
		this.published = published;
		this.updated = updated;
		this.url = url;
		this.actor = actor;
		this.object = object;
	}
	
	public String getId() {
		return id;
	}

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

	public BasePerson getActor() {
		return actor;
	}
	
	public String getContent() {
		return object.content;
	}
	
	public List<Attachment> getAttachments() {
		return object.attachments == null ? new ArrayList<Attachment>() : object.attachments;
	}

	public int getPlusOners() {
		return object.plusOners;
	}

	public int getResharers() {
		return object.resharers;
	}
	
	public int getReplies() {
		return object.replies;
	}
}
