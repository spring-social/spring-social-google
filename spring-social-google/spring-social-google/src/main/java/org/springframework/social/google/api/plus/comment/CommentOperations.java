package org.springframework.social.google.api.plus.comment;

/**
 * Defines operations for reading Google+ comments.
 * @author Gabriel Axel
 */
public interface CommentOperations {

	/**
	 * Retrieves a comment by its ID.
	 * @param id comment ID
	 * @return the retrieved {@link Comment}
	 */
	Comment getComment(String id);
	
	/**
	 * Retrieves the comments of an activity.
	 * @param activityId activity ID
	 * @param pageToken page to retrieve or null for first page
	 * @return
	 */
	CommentsPage getComments(String activityId, String pageToken);
}
