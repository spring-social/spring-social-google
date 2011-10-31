package org.springframework.social.google.api.gdata.picasa.impl;

import org.springframework.social.google.api.gdata.picasa.Album;
import org.springframework.social.google.api.gdata.picasa.AlbumQueryBuilder;
import org.springframework.social.google.api.gdata.picasa.BoundingBox;
import org.springframework.social.google.api.gdata.picasa.Visibility;
import org.springframework.social.google.api.gdata.query.impl.PublishTextQueryBuilderImpl;

public class AlbumQueryBuilderImpl extends PublishTextQueryBuilderImpl<AlbumQueryBuilder, Album> implements AlbumQueryBuilder {

	private static final String ALBUM_SEARCH_URL = "https://picasaweb.google.com/data/feed/api/user/default";
	
	private Visibility visibility;
	private BoundingBox boundingBox;
	private int maxImageSize;
	private int thumbnailSize;
	private String location;
	
	public AlbumQueryBuilderImpl(PicasaTemplate operations) {
		super(ALBUM_SEARCH_URL, operations, new AlbumExtractor());
	}

	@Override
	public AlbumQueryBuilder withAccess(Visibility visibility) {
		this.visibility = visibility;
		return castThis();
	}

	@Override
	public AlbumQueryBuilder betweenCoordinates(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
		return castThis();
	}

	@Override
	public AlbumQueryBuilder betweenCoordinates(float west, float south,
			float east, float north) {
		return betweenCoordinates(west, south, east, north);
	}

	@Override
	public AlbumQueryBuilder withMaximumSize(int size) {
		this.maxImageSize = size;
		return castThis();
	}

	@Override
	public AlbumQueryBuilder fromLocation(String location) {
		this.location = location;
		return castThis();
	}

	@Override
	public AlbumQueryBuilder withThumbnailSize(int size) {
		this.thumbnailSize = size;
		return castThis();
	}

	@Override 
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		appendQueryParam(sb, "visibility", visibility);
		appendQueryParam(sb, "bbox", boundingBox);
		appendQueryParam(sb, "imgmax", maxImageSize);
		appendQueryParam(sb, "thumbsize", thumbnailSize);
		appendQueryParam(sb, "l", location);
		return sb;
	}
}
