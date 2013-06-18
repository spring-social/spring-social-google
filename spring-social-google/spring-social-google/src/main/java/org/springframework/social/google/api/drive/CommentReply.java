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

import java.util.Date;

import org.springframework.social.google.api.ApiEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing a reply to comment in a file in Google Drive
 * @author Gabriel Axel
 */
public class CommentReply extends ApiEntity {

	private Date createdDate;
	
	private Date modifiedDate;
	
	private DriveUser author;
	
	private String content;
	
	private String htmlContent;
	
	private boolean deleted;
	
	private ReplyVerb verb;
	
	public CommentReply() {
		
	}
	
	public CommentReply(String content) {
		this.content = content;
	}
	
	public CommentReply(String content, ReplyVerb verb) {
		this(content);
		this.verb = verb;
	}
	
	@Override
	@JsonProperty("replyId")
	public String getId() {
		return super.getId();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public DriveUser getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public ReplyVerb getVerb() {
		return verb;
	}
	
}
