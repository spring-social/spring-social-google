package org.springframework.social.google.api.gdata.picasa;

import org.springframework.social.google.api.gdata.query.PublishTextQueryBuilder;

public interface PhotoQueryBuilder extends PublishTextQueryBuilder<PhotoQueryBuilder, Photo> {
	PhotoQueryBuilder withAccess(AccessFilter accessFilter);
	PhotoQueryBuilder betweenCoordinates(BoundingBox boundingBox);
	PhotoQueryBuilder betweenCoordinates(float west, float south, float east, float north);
	PhotoQueryBuilder withMaximumSize(int size);
	PhotoQueryBuilder fromLocation(String location);
	PhotoQueryBuilder withThumbnailSize(int size);
}
