/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
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
  static final String DRIVE_FILES_URL = DRIVE_BASE_URL + "/files/";
  private static final String DRIVE_ABOUT_URL = DRIVE_BASE_URL + "/about";
  private static final String DRIVE_APPS_URL = DRIVE_BASE_URL + "/apps/";
  private static final String PERMISSIONS = "/permissions/";

  private static final String REVISIONS = "/revisions/";

  private static final String COMMENTS = "/comments/";

  private static final String REPLIES = "/replies/";

  private static final String PROPERTIES = "/properties/";

  private static final String SEND_NOTIFICATION = "?sendNotificationEmails=";

  private static final String MULTIPART_UPLOAD_URL =
    "https://www.googleapis.com/upload/drive/v2/files?uploadType=multipart";

  public DriveTemplate(final RestTemplate restTemplate, final boolean isAuthorized) {
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
  public DriveApp getApp(final String id) {
    return getEntity(DRIVE_APPS_URL + id, DriveApp.class);
  }

  @Override
  public DriveFile getFile(final String id) {
    return getEntity(DRIVE_FILES_URL + id, DriveFile.class);
  }

  @Override
  public DriveFileQueryBuilder driveFileQuery() {
    return new DriveFileQueryBuilderImpl(restTemplate);
  }

  @Override
  public DriveFilesPage getRootFiles(final String pageToken) {
    return getFiles("root", pageToken);
  }

  @Override
  public DriveFilesPage getFiles(final String parent, final String pageToken) {
    return driveFileQuery()
      .parentIs(parent)
      .fromPage(pageToken)
      .trashed(false)
      .hidden(false)
      .getPage();
  }

  @Override
  public DriveFilesPage getTrashedFiles(final String pageToken) {
    return driveFileQuery()
      .trashed(true)
      .getPage();
  }

  @Override
  public DriveFile trash(final String id) {
    return restTemplate.postForObject(DRIVE_FILES_URL + id + "/trash", null, DriveFile.class);
  }

  @Override
  public DriveFile untrash(final String id) {
    return restTemplate.postForObject(DRIVE_FILES_URL + id + "/untrash", null, DriveFile.class);
  }

  @Override
  public DriveFile star(final String id) {
    return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.starred", true).getMap(), DriveFile.class);
  }

  @Override
  public DriveFile unstar(final String id) {
    return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.starred", false).getMap(), DriveFile.class);
  }

  @Override
  public DriveFile hide(final String id) {
    return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.hidden", true).getMap(), DriveFile.class);
  }

  @Override
  public DriveFile unhide(final String id) {
    return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("labels.hidden", false).getMap(), DriveFile.class);
  }

  @Override
  public void delete(final String id) {
    restTemplate.delete(DRIVE_FILES_URL + id);
  }

  @Override
  public DriveFile copy(final String id) {
    return restTemplate.postForObject(DRIVE_FILES_URL + id + "/copy", null, DriveFile.class);
  }

  @Override
  public DriveFile copy(final String id, final String[] parentIds) {
    final DriveFile file = new DriveFile.Builder()
      .setParents(parentIds)
      .build();
    return saveEntity(DRIVE_FILES_URL + id + "/copy", file);
  }

  @Override
  public DriveFile copy(final String id, final String[] parentIds, final String title) {
    final DriveFile file = new DriveFile.Builder()
      .setTitle(title)
      .setParents(parentIds)
      .build();
    return saveEntity(DRIVE_FILES_URL + id + "/copy", file);
  }

  @Override
  public DriveFile move(final String id, final String parentId) {
    final List<DriveFileParent> parents = new ArrayList<>(1);
    parents.add(new DriveFileParent(parentId));
    return patch(DRIVE_FILES_URL + id, new PatchBuilder().set("parents", parents).getMap(), DriveFile.class);
  }

  @Override
  public DriveFile upload(final Resource resource, final DriveFile metadata, final UploadParameters parameters) {

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MULTIPART_FORM_DATA);

    final MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("metadata", metadata);
    body.add("file", resource);

    final HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

    final ResponseEntity<DriveFile> response = restTemplate.exchange(MULTIPART_UPLOAD_URL + parameters, POST, entity, DriveFile.class);

    return response.getBody();
  }

  @Override
  public DriveFile createFileMetadata(final DriveFile metadata) {
    return saveEntity(DRIVE_FILES_URL, metadata);
  }

  @Override
  public DriveFile createFolder(final String parentId, final String name) {
    return createFileMetadata(DriveFile.builder()
      .setMimeType(FOLDER)
      .setTitle(name)
      .setParents(hasText(parentId) ? parentId : "root")
      .build());
  }

  @Override
  public List<UserPermission> getPermissions(final String fileId) {
    return getEntity(DRIVE_FILES_URL + fileId + PERMISSIONS, UserPermissionsList.class).getItems();
  }

  @Override
  public UserPermission addPermission(final String fileId,
                                      final UserPermission permission, final boolean sendNotificationEmails) {
    return saveEntity(DRIVE_FILES_URL + fileId + PERMISSIONS + SEND_NOTIFICATION + sendNotificationEmails, permission);
  }

  @Override
  public UserPermission updatesPermission(final String fileId, final String permissionId,
                                          final UserPermission permission) {
    final Object patch = new PatchBuilder()
      .set("role", permission.getRole())
      .set("additionalRoles", permission.getAdditionalRoles())
      .getMap();
    return patch(DRIVE_FILES_URL + fileId + PERMISSIONS + permissionId, patch, UserPermission.class);
  }

  @Override
  public void removePermission(final String fileId, final String permissionId) {
    restTemplate.delete(DRIVE_FILES_URL + fileId + PERMISSIONS + permissionId);
  }

  @Override
  public List<FileRevision> getRevisions(final String fileId) {
    return getEntity(DRIVE_FILES_URL + fileId + REVISIONS, FileRevisionsList.class).getItems();
  }

  @Override
  public FileRevision updateRevision(final String fileId, final String revisionId,
                                     final FileRevision revision) {
    final Object patch = new PatchBuilder()
      .set("pinned", revision.isPinned())
      .set("publishAuto", revision.isPublishAuto())
      .set("published", revision.isPublished())
      .set("publishedOutsideDomain", revision.isPublishedOutsideDomain())
      .getMap();
    return patch(DRIVE_FILES_URL + fileId + REVISIONS + revisionId, patch, FileRevision.class);
  }

  @Override
  public FileCommentQueryBuilder fileCommentQueryBuilder(final String fileId) {
    return new FileCommentQueryBuilderImpl(DRIVE_FILES_URL + fileId + COMMENTS, FileCommentsPage.class, restTemplate);
  }

  @Override
  public FileCommentsPage getComments(final String fileId, final String pageToken) {
    return fileCommentQueryBuilder(fileId)
      .fromPage(pageToken)
      .getPage();
  }

  @Override
  public FileComment addComment(final String fileId, final FileComment comment) {
    return saveEntity(DRIVE_FILES_URL + fileId + COMMENTS, comment);
  }

  @Override
  public FileComment updateComment(final String fileId, final String commentId,
                                   final FileComment comment) {
    final Object patch = new PatchBuilder()
      .set("content", comment.getContent())
      .getMap();
    return patch(DRIVE_FILES_URL + fileId + COMMENTS + commentId, patch, FileComment.class);
  }

  @Override
  public void removeComment(final String fileId, final String commentId) {
    restTemplate.delete(DRIVE_FILES_URL + fileId + COMMENTS + commentId);
  }

  @Override
  public CommentReply addReply(final String fileId, final String commentId,
                               final CommentReply reply) {
    return saveEntity(DRIVE_FILES_URL + fileId + COMMENTS + commentId + REPLIES, reply);
  }

  @Override
  public CommentReply updateReply(final String fileId, final String commentId,
                                  final String replyId, final CommentReply reply) {
    final Object patch = new PatchBuilder()
      .set("content", reply.getContent())
      .set("verb", reply.getVerb())
      .getMap();
    return patch(DRIVE_FILES_URL + fileId + COMMENTS + commentId + REPLIES + replyId, patch, CommentReply.class);
  }

  @Override
  public void removeReply(final String fileId, final String commentId, final String replyId) {
    restTemplate.delete(DRIVE_FILES_URL + fileId + COMMENTS + commentId + REPLIES + replyId);
  }

  @Override
  public Resource downloadFile(final String id) {
    return downloadFile(getFile(id));
  }

  @Override
  public Resource downloadFile(final DriveFile file) {
    return restTemplate.getForObject(file.getDownloadUrl(), Resource.class);
  }

  @Override
  public List<FileProperty> getProperties(final String fileId) {
    return getEntity(DRIVE_FILES_URL + fileId + PROPERTIES, FilePropertiesList.class).getItems();
  }

  @Override
  public FileProperty getProperty(final String fileId, final String propertyKey) {
    return getEntity(DRIVE_FILES_URL + fileId + PROPERTIES + propertyKey, FileProperty.class);
  }

  @Override
  public FileProperty addProperty(final String fileId, final FileProperty property) {
    return saveEntity(DRIVE_FILES_URL + fileId + PROPERTIES, property);
  }

  @Override
  public FileProperty updateProperty(final String fileId, final FileProperty property) {
    final Object patch = new PatchBuilder()
      .set("value", property.getValue())
      .set("visibility", property.getVisibility())
      .getMap();
    return patch(DRIVE_FILES_URL + fileId + PROPERTIES + property.getKey(), patch, FileProperty.class);
  }

  @Override
  public void removeProperty(final String fileId, final String propertyKey) {
    restTemplate.delete(DRIVE_FILES_URL + fileId + PROPERTIES + propertyKey);
  }
}
