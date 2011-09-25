package org.springframework.social.google.api.picasa;

import java.util.List;

public interface PicasaOperations {

	List<Album> getAlbums();
	List<Album> getAlbums(String userId);
}
