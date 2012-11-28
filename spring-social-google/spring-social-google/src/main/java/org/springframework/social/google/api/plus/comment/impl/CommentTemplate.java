package org.springframework.social.google.api.plus.comment.impl;

import static org.springframework.util.StringUtils.*;
import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.plus.comment.Comment;
import org.springframework.social.google.api.plus.comment.CommentOperations;
import org.springframework.social.google.api.plus.comment.CommentsPage;
import org.springframework.web.client.RestTemplate;

public class CommentTemplate extends AbstractGoogleApiOperations implements
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
		StringBuilder sb = new StringBuilder(ACTIVITIES_URL)
			.append(activityId).append(COMMENTS);
		if(hasText(pageToken)) {
			sb.append("?pageToken=").append(pageToken);
		}
		return getEntity(sb.toString(), CommentsPage.class);
	}

}
