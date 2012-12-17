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
import static org.springframework.social.google.api.drive.DriveFile.FOLDER;
import static org.springframework.util.StringUtils.hasText;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.social.google.api.drive.DriveFileQueryBuilder;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.drive.UploadParameters;
import org.springframework.social.google.api.drive.UserPermission;
import org.springframework.social.google.api.drive.UserPermissionsList;
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
	private static final String PERMISSIONS = "/permissions/";
	private static final String SEND_NOTIFICATION = "?sendNotificationEmails=";
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
	public DriveFile trash(String id) {
		return restTemplate.postForObject(DRIVE_FILES_URL + id + "/trash", null, DriveFile.class);
	}

	@Override
	public DriveFile untrash(String id) {
		return restTemplate.postForObject(DRIVE_FILES_URL + id + "/untrash", null, DriveFile.class);
	}

	@Override
	public DriveFile star(String id) {
		return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.starred", true).getMap(), DriveFile.class);
	}

	@Override
	public DriveFile unstar(String id) {
		return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.starred", false).getMap(), DriveFile.class);
	}

	@Override
	public DriveFile hide(String id) {
		return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.hidden", true).getMap(), DriveFile.class);
	}

	@Override
	public DriveFile unhide(String id) {
		return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.hidden", false).getMap(), DriveFile.class);
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

	@Override
	public DriveFile createFileMetadata(DriveFile metadata) {
		return saveEntity(DRIVE_FILES_URL, metadata);
	}

	@Override
	public DriveFile createFolder(String parentId, String name) {
		return createFileMetadata(new DriveFile.Builder()
			.setMimeType(FOLDER)
			.setTitle(name)
			.setParents(hasText(parentId) ? parentId : "root")
			.build());
	}

	@Override
	public UserPermissionsList getPermissions(String fileId) {
		return getEntity(DRIVE_FILES_URL + fileId + PERMISSIONS, UserPermissionsList.class);
	}

	@Override
	public UserPermission addPermission(String fileId,
			UserPermission permission, boolean sendNotificationEmails) {
		return saveEntity(DRIVE_FILES_URL + fileId + PERMISSIONS + SEND_NOTIFICATION + sendNotificationEmails, permission);
	}

	@Override
	public UserPermission updatesPermission(String fileId, String permissionId,
			UserPermission permission) {
		Object patch = new PatchBuilder()
			.set("role", permission.getRole())
			.set("additionalRoles", permission.getAdditionalRoles())
			.getMap();
		return patch(DRIVE_FILES_URL + fileId + PERMISSIONS + permissionId, patch, UserPermission.class);
	}

	@Override
	public void removePermission(String fileId, String permissionId) {
		restTemplate.delete(DRIVE_FILES_URL + fileId + PERMISSIONS + permissionId);
	}
}
