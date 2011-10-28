package org.springframework.social.google.api.plus.comment.impl;

import org.springframework.social.google.api.plus.comment.Comment;
import org.springframework.social.google.api.plus.comment.CommentOperations;
import org.springframework.social.google.api.plus.comment.CommentsPage;
import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.web.client.RestTemplate;

public class CommentTemplate extends AbstractGooglePlusOperations implements
		CommentOperations {

	private static final String COMMENTS_URL = "https://www.googleapis.com/plus/v1/comments/";
	private static final String ACTIVITIES_URL = "https://www.googleapis.com/plus/v1/activities/";
	private static final String COMMENTS = "/comments";
	
	public CommentTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public Comment getComment(String id) {
		return getEntity(COMMENTS_URL + id, Comment.class);
	}

	@Override
	public CommentsPage getComments(String activityId, String pageToken) {
		return getEntity(ACTIVITIES_URL + activityId + COMMENTS, CommentsPage.class);
	}

}
