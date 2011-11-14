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
package org.springframework.social.google.api.gdata.picasa.impl;

import static org.springframework.social.google.api.gdata.impl.ElementBuilder.newAtomEntryBuilder;
import static org.springframework.social.google.api.gdata.impl.Namespaces.PicasaNamespace;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import nu.xom.Element;

import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.picasa.Album;
import org.springframework.social.google.api.gdata.picasa.AlbumQueryBuilder;
import org.springframework.social.google.api.gdata.picasa.PhotoQueryBuilder;
import org.springframework.social.google.api.gdata.picasa.PicasaOperations;
import org.springframework.web.client.RestTemplate;

/**
 * {@link PicasaOperations} implementation.
 * @author Gabriel Axel
 */
public class PicasaTemplate extends AbstractGDataOperations implements PicasaOperations {

	private static final String PICASA_PREFIX = "https://picasaweb.google.com/data/";
	private static final String PICASA_FEED = PICASA_PREFIX + "feed/api/user/";
	private static final String PICASA_ENTRY = PICASA_PREFIX + "entry/api/user/";
	private static final String ALBUM_ID = "/albumid/";
	private static final String DEFAULT_USER = "default";
	
	public PicasaTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public List<Album> getAlbums() {
		return getAlbums(DEFAULT_USER);
	}

	@Override
	public List<Album> getAlbums(String userId) {
		return extractFeedEntries(PICASA_FEED + userId, new AlbumExtractor());
	}

	@Override
	public Album getAlbum(String albumId) {
		return getAlbum(DEFAULT_USER, albumId);
	}

	@Override
	public Album getAlbum(String userId, String albumId) {
		return extractEntry(PICASA_ENTRY + userId + ALBUM_ID + albumId, new AlbumExtractor());
	}

	@Override
	public Album saveAlbum(Album album) {
		
		Element element = newAtomEntryBuilder()
			.setTitle(album.getTitle())
			.addSimpleAtomElement("summary", album.getSummary())
			.addSimpleAtomElement("rights", album.getVisibility())
			.addElement(PicasaNamespace, "gphoto:access", album.getVisibility())
			.setCategoryKind("http://schemas.google.com/photos/2007#album")
			.addElement(PicasaNamespace, "gphoto:id", album.getId())
			.getElement();
		
		if(hasText(album.getId())) {
			return putEntry(PICASA_ENTRY + DEFAULT_USER + ALBUM_ID + album.getId(), element, new AlbumExtractor());
		} else {
			return postEntry(PICASA_FEED + DEFAULT_USER, element, new AlbumExtractor());
		}
	}

	@Override
	public void deleteAlbum(String albumId) {
		deleteEntry(PICASA_ENTRY + DEFAULT_USER + ALBUM_ID + albumId);
	}

	@Override
	public AlbumQueryBuilder albumQuery() {
		return new AlbumQueryBuilderImpl(this);
	}

	@Override
	public PhotoQueryBuilder photoQuery() {
		return new PhotoQueryBuilderImpl(this);
	}
}
