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
package org.springframework.social.google.api.drive;

import java.util.Date;
import java.util.Map;

import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a file revision in Google Drive
 * @author Gabriel Axel
 */
public class FileRevision extends ApiEntity {
  private String mimeType;
  private Date modifiedDate;
  private boolean pinned;
  private boolean published;
  private String publishedLink;
  private boolean publishAuto;
  private boolean publishedOutsideDomain;
  private String downloadUrl;
  private Map<String, String> exportLinks;
  private String lastModifyingUserName;
  private String originalFilename;
  private String md5Checksum;
  private long fileSize;

  public String getMimeType() {
    return mimeType;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public boolean isPinned() {
    return pinned;
  }

  public boolean isPublished() {
    return published;
  }

  public String getPublishedLink() {
    return publishedLink;
  }

  public boolean isPublishAuto() {
    return publishAuto;
  }

  public boolean isPublishedOutsideDomain() {
    return publishedOutsideDomain;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public Map<String, String> getExportLinks() {
    return exportLinks;
  }

  public String getLastModifyingUserName() {
    return lastModifyingUserName;
  }

  public String getOriginalFilename() {
    return originalFilename;
  }

  public String getMd5Checksum() {
    return md5Checksum;
  }

  public long getFileSize() {
    return fileSize;
  }

  public static class FileRevisionBuilder {
    private boolean pinned;
    private boolean publishAuto;
    private boolean published;
    private boolean publishedOutsideDomain;

    public FileRevisionBuilder setPinned(final boolean pinned) {
      this.pinned = pinned;
      return this;
    }

    public FileRevisionBuilder setPublishAuto(final boolean publishAuto) {
      this.publishAuto = publishAuto;
      return this;
    }

    public FileRevisionBuilder setPublished(final boolean published) {
      this.published = published;
      return this;
    }

    public FileRevisionBuilder setPublishedOutsideDomain(final boolean publishedOutsideDomain) {
      this.publishedOutsideDomain = publishedOutsideDomain;
      return this;
    }

    /**
     * Build a new FileRevision object from the given POJO.
     */
    public FileRevision build() {
      final FileRevision revision = new FileRevision();
      revision.pinned = pinned;
      revision.publishAuto = publishAuto;
      revision.published = published;
      revision.publishedOutsideDomain = publishedOutsideDomain;
      return revision;
    }
  }
}
