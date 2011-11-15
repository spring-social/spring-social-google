package org.springframework.social.google.api.gdata.picasa;

import java.util.Date;

public class Photo {

	private final String id;
	private final String albumId;
	private final Date published;
	private final Date updated;
	private final String title;
	private final String summary;
	private final Visibility visibility;
	private final int width;
	private final int height;
	private final int size;
	private final String contentType;
	private final String contentUrl;
	private final int thumbnailWidth;
	private final int thumbnailHeight;
	private final String thumbnailUrl;
	
	public Photo(String id, String albumId, Date published, Date updated,
			String title, String summary, Visibility visibility, int width,
			int height, int size, String contentType, String contentUrl,
			int thumbnailWidth, int thumbnailHeight, String thumbnailUrl) {
		this.id = id;
		this.albumId = albumId;
		this.published = published;
		this.updated = updated;
		this.title = title;
		this.summary = summary;
		this.visibility = visibility;
		this.width = width;
		this.height = height;
		this.size = size;
		this.contentType = contentType;
		this.contentUrl = contentUrl;
		this.thumbnailWidth = thumbnailWidth;
		this.thumbnailHeight = thumbnailHeight;
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getId() {
		return id;
	}
	
	public String getAlbumId() {
		return albumId;
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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public String getContentUrl() {
		return contentUrl;
	}
	
	public int getThumbnailWidth() {
		return thumbnailWidth;
	}
	
	public int getThumbnailHeight() {
		return thumbnailHeight;
	}
	
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	
}
