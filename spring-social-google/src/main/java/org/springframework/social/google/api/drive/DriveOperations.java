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

import java.util.List;

import org.springframework.core.io.Resource;

/**
 * Defines operations for integrating with Google Drive. Requires OAuth scope(s)
 * from the following:
 * <ul>
 * <li>https://www.googleapis.com/auth/drive.file</li>
 * <li>https://www.googleapis.com/auth/drive</li>
 * <li>https://www.googleapis.com/auth/drive.apps.readonly</li>
 * <li>https://www.googleapis.com/auth/drive.readonly</li>
 * <li>https://www.googleapis.com/auth/drive.readonly.metadata</li>
 * </ul>
 * See <a
 * href="https://developers.google.com/drive/scopes">https://developers.google
 * .com/drive/scopes</a> for details about the different scopes
 * 
 * @author Gabriel Axel
 * 
 */
public interface DriveOperations {

	/**
	 * Get general Google Drive details for the current user
	 * 
	 * @return {@link DriveAbout} with details for the current user
	 */
	DriveAbout getAbout();

	/**
	 * Returns the applications authorized by the user to access Google Drive
	 * API
	 * 
	 * @return List of {@link DriveApp} for the current user
	 */
	List<DriveApp> getApps();

	/**
	 * Returns an application by its ID
	 * 
	 * @param id
	 *            The ID of the application
	 * @return {@link DriveApp} matching the ID
	 */
	DriveApp getApp(String id);

	/**
	 * Retrieves a file by its ID
	 * 
	 * @param id
	 *            the ID to retrieve by
	 * @return {@link DriveFile} matching the ID
	 */
	DriveFile getFile(String id);

	/**
	 * Creates a {@link DriveFileQueryBuilder}
	 * 
	 * @return a new {@link DriveFileQueryBuilder}
	 */
	DriveFileQueryBuilder driveFileQuery();

	/**
	 * Returns files and folders under the root folder
	 * 
	 * @param pageToken
	 *            Page token or null
	 * @return {@link DriveFilesPage}
	 */
	DriveFilesPage getRootFiles(String pageToken);

	/**
	 * Returns the files and folders under a specified folder
	 * 
	 * @param parent
	 *            folder ID or "root"
	 * @param pageToken
	 *            Page token or null
	 * @return {@link DriveFilesPage}
	 */
	DriveFilesPage getFiles(String parent, String pageToken);

	/**
	 * Returns trashed files and folders
	 * 
	 * @param pageToken
	 *            Page token or null
	 * @return {@link DriveFilesPage}
	 */
	DriveFilesPage getTrashedFiles(String pageToken);

	/**
	 * Moves a file to trash
	 * 
	 * @param id
	 *            The ID of the file to trash
	 * @return The updated {@link DriveFile}
	 */
	DriveFile trash(String id);

	/**
	 * Restores a file from trash
	 * 
	 * @param id
	 *            The ID of the file to untrash return The updated
	 *            {@link DriveFile}
	 * @return The trashed {@link DriveFile}
	 */
	DriveFile untrash(String id);

	/**
	 * Stars a file
	 * 
	 * @param id
	 *            The ID of the file to star
	 * @return The updated {@link DriveFile}
	 */
	DriveFile star(String id);

	/**
	 * Remove the star from a file
	 * 
	 * @param id
	 *            The ID of the file to unstar
	 * @return The updated {@link DriveFile}
	 */
	DriveFile unstar(String id);

	/**
	 * Hides a file
	 * 
	 * @param id
	 *            The ID of the file to hide
	 * @return The updated {@link DriveFile}
	 */
	DriveFile hide(String id);

	/**
	 * Unhides a file
	 * 
	 * @param id
	 *            The ID of the file to unhide
	 * @return The updated {@link DriveFile}
	 */
	DriveFile unhide(String id);

	/**
	 * Permanently deletes a file
	 * 
	 * @param id
	 *            The ID of the file to delete
	 */
	void delete(String id);
	
	/**
	 * Create a copy of a file
	 * 
	 * @param id
	 *            The ID of the source file
	 * @return The newly-created {@link DriveFile} copy
	 */
	DriveFile copy(String id);

	/**
	 * Create a copy of a file
	 * 
	 * @param id
	 *            The ID of the source file
	 * @param parentIds
	 *            Array of parent folder ID to place the file into, or "root"
	 * @return The newly-created {@link DriveFile} copy
	 */
	DriveFile copy(String id, String[] parentIds);

	/**
	 * Create a copy of a file
	 * 
	 * @param id
	 *            The ID of the source file
	 * @param parentIds
	 *            Array of parent folder ID to place the file into, or "root"
	 * @param title
	 *            The title to apply to the new file
	 * @return The newly-created {@link DriveFile} copy
	 */
	DriveFile copy(String id, String[] parentIds, String title);

	/**
	 * Move a file into a different folder
	 * 
	 * @param id
	 *            The ID of the file to move
	 * @param parentId
	 *            The parent folder ID to move the file to, or "root"
	 * @return The updated {@link DriveFile}
	 */
	DriveFile move(String id, String parentId);

	/**
	 * Uploads a file using multipart
	 * 
	 * @param resource
	 *            Reference to the file's content
	 * @param metadata
	 *            The file's metadata
	 * @param parameters
	 *            Parameters for uploading and processing the file
	 * @return The file resource that was created
	 */
	DriveFile upload(Resource resource, DriveFile metadata,
			UploadParameters parameters);

	/**
	 * Creates an empty file with metadata
	 * 
	 * @param metadata
	 *            The file's properties
	 * @return {@link DriveFile} representing the created file
	 */
	DriveFile createFileMetadata(DriveFile metadata);

	/**
	 * Creates a folder
	 * 
	 * @param parentId
	 *            The parent folder ID or "root"
	 * @param name
	 *            The name of the folder to create
	 * @return {@link DriveFile} representing the created folder
	 */
	DriveFile createFolder(String parentId, String name);

	/**
	 * Returns the permissions of a file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @return List of {@link UserPermission} for the file
	 */
	List<UserPermission> getPermissions(String fileId);

	/**
	 * Adds a permission to a file
	 * 
	 * @param fileId
	 *            The file ID
	 * @param permission
	 *            {@link UserPermission} with the permission settings
	 * @param sendNotificationEmails
	 *            Whether to send notification e-mails
	 * @return The created {@link UserPermission}
	 */
	UserPermission addPermission(String fileId, UserPermission permission,
			boolean sendNotificationEmails);

	/**
	 * Updates a permission to a file
	 * 
	 * @param fileId
	 *            The file ID
	 * @param permissionId
	 *            the ID of the permission
	 * @param permission
	 *            {@link UserPermission} with new role and additionalRoles
	 *            properties
	 * @return The updated {@link UserPermission}
	 */
	UserPermission updatesPermission(String fileId, String permissionId,
			UserPermission permission);

	/**
	 * Removes a permission from a file
	 * 
	 * @param fileId
	 *            The file ID
	 * @param permissionId
	 *            The ID of the permission
	 */
	void removePermission(String fileId, String permissionId);

	/**
	 * Returns the revisions of a file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @return List of {@link FileRevision}
	 */
	List<FileRevision> getRevisions(String fileId);

	/**
	 * Updates a file revision
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param revisionId
	 *            The ID of the revision
	 * @param revision
	 *            {@link FileRevision} with new pinned, publishAuto, published
	 *            and publishedOutsideDomain properties
	 * @return The updated {@link FileRevision}
	 */
	FileRevision updateRevision(String fileId, String revisionId,
			FileRevision revision);

	/**
	 * Returns a {@link FileCommentQueryBuilder} for listing the comments of a
	 * file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @return {@link FileCommentQueryBuilder} for the file
	 */
	FileCommentQueryBuilder fileCommentQueryBuilder(String fileId);

	/**
	 * Returns the first default page of comments on a file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param pageToken
	 *            Page token or null
	 * @return Page of comments
	 */
	FileCommentsPage getComments(String fileId, String pageToken);

	/**
	 * Adds a comment to a file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param comment
	 *            The new comment
	 * @return The created {@link FileComment}
	 */
	FileComment addComment(String fileId, FileComment comment);

	/**
	 * Updates a comment to a file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param commentId
	 *            The ID of the comment
	 * @param comment
	 *            Comment with the new content
	 * @return The updated {@link FileComment}
	 */
	FileComment updateComment(String fileId, String commentId,
			FileComment comment);

	/**
	 * Removes a comment from a file
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param commentId
	 *            The ID of the comment
	 */
	void removeComment(String fileId, String commentId);

	/**
	 * Adds a reply to a file comment
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param commentId
	 *            The ID of the comment
	 * @param reply
	 *            The new reply
	 * @return The created {@link CommentReply}
	 */
	CommentReply addReply(String fileId, String commentId, CommentReply reply);

	/**
	 * Updates a reply to a file comment
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param commentId
	 *            The ID of the comment
	 * @param replyId
	 *            The ID of the reply
	 * @param reply
	 *            Reply with new content
	 * @return The updated {@link CommentReply}
	 */
	CommentReply updateReply(String fileId, String commentId, String replyId,
			CommentReply reply);

	/**
	 * Removes a reply from a file comment
	 * 
	 * @param fileId
	 *            The ID of the file
	 * @param commentId
	 *            The ID of the comment
	 * @param replyId
	 *            The ID of the reply
	 */
	void removeReply(String fileId, String commentId, String replyId);

	/**
	 * Downloads a file from Google Drive. This method first sends a request to
	 * get the download URL, and then another request to download the file.
	 * 
	 * @param id
	 *            The file ID
	 * @return Resource abstraction for the file
	 */
	Resource downloadFile(String id);

	/**
	 * Downloads a file from Google Drive. This method uses
	 * {@link DriveFile#getDownloadUrl()}, which may have expired since the file
	 * metadata was fetched.
	 * 
	 * @param file
	 *            The file metadata
	 * @return Resource abstraction for the file
	 */
	Resource downloadFile(DriveFile file);
}
