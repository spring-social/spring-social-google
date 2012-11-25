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
package org.springframework.social.google.api.drive.impl;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.social.google.api.drive.DriveFileQueryBuilder;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.drive.UploadParameters;
import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.impl.PatchBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * {@link DriveOperations} implementation.
 * @author Gabriel Axel
 */
public class DriveTemplate extends AbstractGoogleApiOperations implements
		DriveOperations {
	
	static final String DRIVE_FILES_URL = "https://www.googleapis.com/drive/v2/files/";
	private static final String MULTIPART_UPLOAD_URL = 
		"https://www.googleapis.com/upload/drive/v2/files?uploadType=multipart"; 
	
	public DriveTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public DriveFile getFile(String id) {
		return getEntity(DRIVE_FILES_URL + id, DriveFile.class);
	}

	@Override
	public DriveFileQueryBuilder driveFileQuery() {
		return new DriveFileQueryBuilderImpl(restTemplate);
	}

	@Override
	public DriveFilesPage getRootFiles(String pageToken) {
		return getFiles("root", pageToken);
	}

	@Override
	public DriveFilesPage getFiles(String parent, String pageToken) {
		return driveFileQuery()
				.parentIs(parent)
				.fromPage(pageToken)
				.trashed(false)
				.hidden(false)
				.getPage();
	}

	@Override
	public DriveFilesPage getTrashedFiles(String pageToken) {
		return driveFileQuery()
				.trashed(true)
				.getPage();
	}

	@Override
	public void trash(String id) {
		restTemplate.postForLocation(DRIVE_FILES_URL + id + "/trash", null);
	}

	@Override
	public void untrash(String id) {
		restTemplate.postForLocation(DRIVE_FILES_URL + id + "/untrash", null);
	}

	@Override
	public void star(String id) {
		patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.starred", true).getMap());
	}

	@Override
	public void unstar(String id) {
		patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.starred", false).getMap());
	}

	@Override
	public void hide(String id) {
		patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.hidden", true).getMap());
	}

	@Override
	public void unhide(String id) {
		patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.hidden", false).getMap());
	}

	@Override
	public void delete(String id) {
		restTemplate.delete(DRIVE_FILES_URL + id);
	}

	@Override
	public DriveFile upload(Resource resource, DriveFile metadata, UploadParameters parameters) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("metadata", metadata);
		body.add("file", resource);
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String,Object>>(body, headers);
		
		ResponseEntity<DriveFile> response = restTemplate.exchange(MULTIPART_UPLOAD_URL + parameters, POST, entity, DriveFile.class);

		return response.getBody();
	}
}
