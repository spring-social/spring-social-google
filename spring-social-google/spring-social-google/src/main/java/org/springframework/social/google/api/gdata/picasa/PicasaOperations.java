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
package org.springframework.social.google.api.gdata.picasa;

import java.util.List;

/**
 * Defines operations for reading and managing Picasa Web albums and their content.
 * @author Gabriel Axel
 */
public interface PicasaOperations {

	/**
	 * Retrieves all the authenticated user's albums.
	 * @return a list of {@link Album}s of the user
	 */
	List<Album> getAlbums();
	
	/**
	 * Retrieves all albums of a user.
	 * @param userId the user profile ID
	 * @return a list of {@link Album}s of the user
	 */
	List<Album> getAlbums(String userId);
	
	/**
	 * Retrieves an album of the authenticated user by its ID.
	 * @param albumId album ID
	 * @return {@link Album} to retrieve
	 */
	Album getAlbum(String albumId);
	
	/**
	 * Retrieves an album by its ID and a user ID.
	 * @param userId user ID
	 * @param albumId album ID
	 * @return {@link Album} to retrieve
	 */
	Album getAlbum(String userId, String albumId);
	
	/**
	 * Creates of updates an album.
	 * @param album {@link Album} to create or update
	 * @return the saved {@link Album}
	 */
	Album saveAlbum(Album album);
	
	/**
	 * Deletes an album.
	 * @param albumId album ID
	 */
	void deleteAlbum(String albumId);

	/**
	 * Creates an {@link AlbumQueryBuilder}
	 * @return a new {@link AlbumQueryBuilder}
	 */
	AlbumQueryBuilder albumQuery();

	/**
	 * Creates a {@link PhotoQueryBuilder}
	 * @return a new {@link PhotoQueryBuilder}
	 */
	PhotoQueryBuilder photoQuery();
}
