package org.springframework.social.google.api.gdata.picasa;

import org.springframework.social.google.api.gdata.query.PublishTextQueryBuilder;

public interface AlbumQueryBuilder extends PublishTextQueryBuilder<AlbumQueryBuilder, Album> {

	AlbumQueryBuilder withAccess(Visibility visibility);
	AlbumQueryBuilder betweenCoordinates(BoundingBox boundingBox);
	AlbumQueryBuilder betweenCoordinates(float west, float south, float east, float north);
	AlbumQueryBuilder withMaximumSize(int size);
	AlbumQueryBuilder fromLocation(String location);
	AlbumQueryBuilder withThumbnailSize(int size);
}
