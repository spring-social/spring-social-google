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
package org.springframework.social.google.api.gdata.picasa;

import java.util.Date;

/**
 * Model representing Picasa Web Album.
 * @author Gabriel Axel
 */
public class Album {

	private final String id;
	private final Date published;
	private final Date updated;
	private final String title;
	private final String summary;
	private final Visibility visibility;
	private final String authorName;
	private final String authorId;
	private final int numberOfPhotos;
	private final String thumbnailUrl;
	
	public Album(String id, Date published, Date updated, String title,
			String summary, Visibility visibility, String authorId,
			String authorName, int numberOfPhotos, String thumbnailUrl) {
		this.id = id;
		this.published = published;
		this.updated = updated;
		this.title = title;
		this.summary = summary;
		this.visibility = visibility;
		this.authorId = authorId;
		this.authorName = authorName;
		this.numberOfPhotos = numberOfPhotos;
		this.thumbnailUrl = thumbnailUrl;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getPublished() {
		return published;
	}
	
	public Date getUpdated() {
		return updated;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}
	
	public String getAuthorId() {
		return authorId;
	}

	public String getAuthorName() {
		return authorName;
	}
	
	public int getNumberOfPhotos() {
		return numberOfPhotos;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

}
