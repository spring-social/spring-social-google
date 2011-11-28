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

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.person.BasePerson;

/**
 * Model class representing a Google+ activity
 * @author Gabriel Axel
 */
public class Activity {
	
	public static class ActivityObject {
		
		public static class TotalItemsWrapper {
			
			@JsonProperty
			private int totalItems;
		}
		
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
	
	private String id;
	private String title;
	private Date published;
	private Date updated;
	private String url;
	
	@JsonProperty
	private BasePerson actor;
	
	@JsonProperty
	private ActivityObject object;
	
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
		return object.plusoners.totalItems;
	}

	public int getResharers() {
		return object.resharers.totalItems;
	}
	
	public int getReplies() {
		return object.replies.totalItems;
	}
}
