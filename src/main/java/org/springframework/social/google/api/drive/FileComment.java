/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.drive;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.MediaType;
import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a comment in a file in Google Drive
 * @author Gabriel Axel
 */
public class FileComment extends ApiEntity {
  private Date createdDate;
  private Date modifiedDate;
  private DriveUser author;
  private String htmlContent;
  private String content;
  private boolean deleted;
  private CommentStatus status;

  @JsonProperty
  private CommentContext context;

  private List<CommentReply> replies;

  public FileComment() {
    // empty
  }

  public FileComment(final String content) {
    this.content = content;
  }

  public FileComment(final String content, final MediaType contextType, final String contextValue) {
    this(content, contextType.toString(), contextValue);
  }

  /**
   * Create a new File comment.
   */
  public FileComment(final String content, final String contextType, final String contextValue) {
    this(content);
    context = new CommentContext();
    context.type = contextType;
    context.value = contextValue;
  }

  @Override
  @JsonProperty("commentId")
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

  public String getHtmlContent() {
    return htmlContent;
  }

  public String getContent() {
    return content;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public CommentStatus getStatus() {
    return status;
  }

  public CommentContext getContext() {
    return context;
  }

  public List<CommentReply> getReplies() {
    return replies;
  }

  private static class CommentContext {
    @JsonProperty
    private String type;

    @JsonProperty
    private String value;
  }
}
