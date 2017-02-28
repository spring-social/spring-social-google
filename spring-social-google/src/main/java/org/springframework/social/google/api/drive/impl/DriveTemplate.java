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

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.api.drive.CommentReply;
import org.springframework.social.google.api.drive.DriveAbout;
import org.springframework.social.google.api.drive.DriveApp;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.social.google.api.drive.DriveFileParent;
import org.springframework.social.google.api.drive.DriveFileQueryBuilder;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.drive.FileComment;
import org.springframework.social.google.api.drive.FileCommentQueryBuilder;
import org.springframework.social.google.api.drive.FileCommentsPage;
import org.springframework.social.google.api.drive.FileProperty;
import org.springframework.social.google.api.drive.FileRevision;
import org.springframework.social.google.api.drive.UploadParameters;
import org.springframework.social.google.api.drive.UserPermission;
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
	
	private static final String DRIVE_BASE_URL = "https://www.googleapis.com/drive/v2";
	
	private static final String DRIVE_ABOUT_URL = DRIVE_BASE_URL + "/about";
	
	private static final String DRIVE_APPS_URL = DRIVE_BASE_URL + "/apps/";
	
	static final String DRIVE_FILES_URL = DRIVE_BASE_URL + "/files/";
	
	private static final String PERMISSIONS = "/permissions/";
	
	private static final String REVISIONS = "/revisions/";
	
	private static final String COMMENTS = "/comments/";
	
	private static final String REPLIES = "/replies/";
	
	private static final String PROPERTIES = "/properties/";
	
	private static final String SEND_NOTIFICATION = "?sendNotificationEmails=";
	
	private static final String MULTIPART_UPLOAD_URL = 
		"https://www.googleapis.com/upload/drive/v2/files?uploadType=multipart"; 
	
	public DriveTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public DriveAbout getAbout() {
		return getEntity(DRIVE_ABOUT_URL, DriveAbout.class);
	}
	
	@Override
	public List<DriveApp> getApps() {
		return getEntity(DRIVE_APPS_URL, DriveAppsList.class).getItems();
	}

	@Override
	public DriveApp getApp(String id) {
		return getEntity(DRIVE_APPS_URL + id,  DriveApp.class);
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
	public DriveFile copy(String id) {
		return restTemplate.postForObject(DRIVE_FILES_URL + id + "/copy", null, DriveFile.class);
	}

	@Override
	public DriveFile copy(String id, String[] parentIds) {
		DriveFile file = new DriveFile.Builder()
		.setParents(parentIds)
		.build();
		return saveEntity(DRIVE_FILES_URL + id + "/copy", file);
	}

	@Override
	public DriveFile copy(String id, String[] parentIds, String title) {
		DriveFile file = new DriveFile.Builder()
		.setTitle(title)
		.setParents(parentIds)
		.build();
		return saveEntity(DRIVE_FILES_URL + id + "/copy", file);
	}

	@Override
	public DriveFile move(String id, String parentId) {
		List<DriveFileParent> parents = new ArrayList<DriveFileParent>(1);
		parents.add(new DriveFileParent(parentId));
		return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("parents", parents).getMap(), DriveFile.class);
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
		return createFileMetadata(DriveFile.builder()
			.setMimeType(FOLDER)
			.setTitle(name)
			.setParents(hasText(parentId) ? parentId : "root")
			.build());
	}

	@Override
	public List<UserPermission> getPermissions(String fileId) {
		return getEntity(DRIVE_FILES_URL + fileId + PERMISSIONS, UserPermissionsList.class).getItems();
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

	@Override
	public List<FileRevision> getRevisions(String fileId) {
		return getEntity(DRIVE_FILES_URL + fileId + REVISIONS, FileRevisionsList.class).getItems();
	}

	@Override
	public FileRevision updateRevision(String fileId, String revisionId,
			FileRevision revision) {
		Object patch = new PatchBuilder()
			.set("pinned", revision.isPinned())
			.set("publishAuto", revision.isPublishAuto())
			.set("published", revision.isPublished())
			.set("publishedOutsideDomain", revision.isPublishedOutsideDomain())
			.getMap();
		return patch(DRIVE_FILES_URL + fileId + REVISIONS + revisionId, patch, FileRevision.class);
	}

	@Override
	public FileCommentQueryBuilder fileCommentQueryBuilder(String fileId) {
		return new FileCommentQueryBuilderImpl(DRIVE_FILES_URL + fileId + COMMENTS, FileCommentsPage.class, restTemplate);
	}

	@Override
	public FileCommentsPage getComments(String fileId, String pageToken) {
		return fileCommentQueryBuilder(fileId)
				.fromPage(pageToken)
				.getPage();
	}

	@Override
	public FileComment addComment(String fileId, FileComment comment) {
		return saveEntity(DRIVE_FILES_URL + fileId + COMMENTS, comment);
	}

	@Override
	public FileComment updateComment(String fileId, String commentId,
			FileComment comment) {
		Object patch = new PatchBuilder()
			.set("content", comment.getContent())
			.getMap();
		return patch(DRIVE_FILES_URL + fileId + COMMENTS + commentId, patch, FileComment.class);
	}

	@Override
	public void removeComment(String fileId, String commentId) {
		restTemplate.delete(DRIVE_FILES_URL + fileId + COMMENTS + commentId);
	}

	@Override
	public CommentReply addReply(String fileId, String commentId,
			CommentReply reply) {
		return saveEntity(DRIVE_FILES_URL + fileId + COMMENTS + commentId + REPLIES, reply);
	}

	@Override
	public CommentReply updateReply(String fileId, String commentId,
			String replyId, CommentReply reply) {
		Object patch = new PatchBuilder()
			.set("content", reply.getContent())
			.set("verb", reply.getVerb())
			.getMap();
		return patch(DRIVE_FILES_URL + fileId + COMMENTS + commentId + REPLIES + replyId, patch, CommentReply.class);
	}

	@Override
	public void removeReply(String fileId, String commentId, String replyId) {
		restTemplate.delete(DRIVE_FILES_URL + fileId + COMMENTS + commentId + REPLIES + replyId);
	}

	@Override
	public Resource downloadFile(String id) {
		return downloadFile(getFile(id));
	}

	@Override
	public Resource downloadFile(DriveFile file) {
		return restTemplate.getForObject(file.getDownloadUrl(), Resource.class);
	}

	@Override
	public List<FileProperty> getProperties(String fileId)
	{
		return getEntity(DRIVE_FILES_URL + fileId + PROPERTIES, FilePropertiesList.class).getItems();
	}

	@Override
	public FileProperty getProperty(String fileId, String propertyKey)
	{
		return getEntity(DRIVE_FILES_URL + fileId + PROPERTIES + propertyKey, FileProperty.class);
	}

	@Override
	public FileProperty addProperty(String fileId, FileProperty property)
	{
		return saveEntity(DRIVE_FILES_URL + fileId + PROPERTIES, property);
	}

	@Override
	public FileProperty updateProperty(String fileId, FileProperty property)
	{
		Object patch = new PatchBuilder()
			.set("value", property.getValue())
			.set("visibility", property.getVisibility())
			.getMap();
		return patch(DRIVE_FILES_URL + fileId + PROPERTIES + property.getKey(), patch, FileProperty.class);
	}

	@Override
	public void removeProperty(String fileId, String propertyKey)
	{
		restTemplate.delete(DRIVE_FILES_URL + fileId + PROPERTIES + propertyKey);
	}
}
