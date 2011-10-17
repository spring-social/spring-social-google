package org.springframework.social.google.api.gdata.picasa;

import java.util.List;

public interface PicasaOperations {

	List<Album> getAlbums();
	List<Album> getAlbums(String userId);
	Album getAlbum(String albumId);
	Album getAlbum(String userId, String albumId);
	Album saveAlbum(Album album);
	void deleteAlbum(String albumId);
}
