package org.springframework.social.google.api.gdata.picasa.impl;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.picasa.AccessFilter;
import org.springframework.social.google.api.gdata.picasa.BoundingBox;
import org.springframework.social.google.api.gdata.picasa.Photo;
import org.springframework.social.google.api.gdata.picasa.PhotoQueryBuilder;
import org.springframework.social.google.api.gdata.query.impl.PublishTextQueryBuilderImpl;

public class PhotoQueryBuilderImpl extends PublishTextQueryBuilderImpl<PhotoQueryBuilder, Photo> implements PhotoQueryBuilder {

	private static final String PHOTO_SEARCH_URL = "https://picasaweb.google.com/data/feed/api/user/default?kind=photo";
	
	public PhotoQueryBuilderImpl(AbstractGDataOperations operations) {
		super(PHOTO_SEARCH_URL, operations, new PhotoExtractor());
	}

	private AccessFilter accessFilter;
	private BoundingBox boundingBox;
	private int maxImageSize;
	private int thumbnailSize = 288;
	private String location;
	
	@Override
	public PhotoQueryBuilder withAccess(AccessFilter accessFilter) {
		this.accessFilter = accessFilter;
		return castThis();
	}
	
	@Override
	public PhotoQueryBuilder betweenCoordinates(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
		return castThis();
	}

	@Override
	public PhotoQueryBuilder betweenCoordinates(float west, float south,
			float east, float north) {
		return betweenCoordinates(west, south, east, north);
	}

	@Override
	public PhotoQueryBuilder withMaximumSize(int size) {
		this.maxImageSize = size;
		return castThis();
	}

	@Override
	public PhotoQueryBuilder fromLocation(String location) {
		this.location = location;
		return castThis();
	}

	@Override
	public PhotoQueryBuilder withThumbnailSize(int size) {
		this.thumbnailSize = size;
		return castThis();
	}
	
	@Override 
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		appendQueryParam(sb, "access", accessFilter);
		appendQueryParam(sb, "bbox", boundingBox);
		appendQueryParam(sb, "imgmax", maxImageSize);
		appendQueryParam(sb, "thumbsize", thumbnailSize);
		appendQueryParam(sb, "l", location);
		return sb;
	}
}
