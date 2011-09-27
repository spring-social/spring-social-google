package org.springframework.social.google.api.picasa;

import java.util.Date;

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
