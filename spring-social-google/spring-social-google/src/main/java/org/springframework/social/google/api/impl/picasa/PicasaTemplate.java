package org.springframework.social.google.api.impl.picasa;

import static org.springframework.social.google.api.impl.helper.ElementBuilder.newAtomEntryBuilder;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import nu.xom.Element;

import static org.springframework.social.google.api.impl.helper.Namespaces.PicasaNamespace;
import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.picasa.Album;
import org.springframework.social.google.api.picasa.PicasaOperations;
import org.springframework.web.client.RestTemplate;

public class PicasaTemplate extends AbstractGoogleOperations implements PicasaOperations {

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
		
		Element entry = newAtomEntryBuilder()
			.setTitle(album.getTitle())
			.addSimpleAtomElement("summary", album.getSummary())
			.addElement(PicasaNamespace, "gphoto:access", album.getVisibility())
			.setCategoryKind("http://schemas.google.com/photos/2007#album")
			.getElement();
		
		if(hasText(album.getId())) {
			return putEntry(PICASA_ENTRY + DEFAULT_USER + ALBUM_ID + album.getId(), entry, new AlbumExtractor());
		} else {
			return postEntry(PICASA_FEED + DEFAULT_USER, entry, new AlbumExtractor());
		}
	}

	@Override
	public void deleteAlbum(String albumId) {
		deleteEntry(PICASA_ENTRY + DEFAULT_USER + ALBUM_ID + albumId);
	}

}
