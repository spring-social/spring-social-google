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
package org.springframework.social.google.api.drive;

public interface DriveOperations {

	/**
	 * Retrieves a file by its ID
	 * @param id the ID to retrieve by
	 * @return the retrieved {@link DriveFile}
	 */
	DriveFile getFile(String id);
	
	/**
	 * Creates a {@link DriveFileQueryBuilder}
	 * @return a new {@link DriveFileQueryBuilder}
	 */
	DriveFileQueryBuilder driveFileQuery();
	
	DriveFilesPage getRootFiles(String pageToken);
	
	DriveFilesPage getFiles(String parent, String pageToken);
	
	DriveFilesPage getTrashedFiles(String pageToken);
	
	void trash(String id);
	
	void untrash(String id);
	
	void star(String id);
	
	void unstar(String id);
	
	void hide(String id);
	
	void unhide(String id);
	
	void delete(String id);
}
