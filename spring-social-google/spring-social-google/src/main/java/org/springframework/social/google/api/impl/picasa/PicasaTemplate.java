package org.springframework.social.google.api.impl.picasa;

import java.util.List;

import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.picasa.Album;
import org.springframework.social.google.api.picasa.PicasaOperations;
import org.springframework.web.client.RestTemplate;

public class PicasaTemplate extends AbstractGoogleOperations implements PicasaOperations {

	private static final String PICASA_PREFIX = "https://picasaweb.google.com/data/";
	private static final String PICASA_FEED = PICASA_PREFIX + "feed/api/user/";
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

}
