package org.springframework.social.google.api.gdata.picasa.impl;

import org.springframework.social.google.api.gdata.picasa.AccessFilter;
import org.springframework.social.google.api.gdata.picasa.Album;
import org.springframework.social.google.api.gdata.picasa.AlbumQueryBuilder;
import org.springframework.social.google.api.gdata.query.impl.GDataQueryBuilderImpl;

public class AlbumQueryBuilderImpl extends GDataQueryBuilderImpl<AlbumQueryBuilder, Album> implements AlbumQueryBuilder {

	private static final String ALBUM_SEARCH_URL = "https://picasaweb.google.com/data/feed/api/user/default?kind=album";
	
	private AccessFilter accessFilter;
	
	public AlbumQueryBuilderImpl(PicasaTemplate operations) {
		super(ALBUM_SEARCH_URL, operations, new AlbumExtractor());
	}

	@Override
	public AlbumQueryBuilder withAccess(AccessFilter accessFilter) {
		this.accessFilter = accessFilter;
		return castThis();
	}

	@Override 
	protected StringBuilder build() {
		StringBuilder sb = super.build();
		appendQueryParam(sb, "access", accessFilter);
		return sb;
	}
}
