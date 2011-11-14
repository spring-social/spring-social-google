package org.springframework.social.google.api.gdata.picasa;

import org.springframework.social.google.api.gdata.query.GDataQueryBuilder;

public interface AlbumQueryBuilder extends GDataQueryBuilder<AlbumQueryBuilder, Album> {

	AlbumQueryBuilder withAccess(AccessFilter accessFilter);
	
}
