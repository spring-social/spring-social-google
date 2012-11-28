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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a file in Google Drive
 * @author Gabriel Axel
 */
public class DriveFile extends ApiEntity {

	public static final String FOLDER = "application/vnd.google-apps.folder";
	
	public static class Builder {

		private String title;
		private String description;
		private String indexableText;
		private boolean starred;
		private boolean hidden;
		private boolean trashed;
		private boolean restricted;
		private boolean viewed;
		private Date lastViewedByMeDate;
		private String mimeType;
		private Date modifiedDate;
		private Collection<String> parentIds = new HashSet<String>();
		
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public Builder setIndexableText(String indexableText) {
			this.indexableText = indexableText;
			return this;
		}
		
		public Builder setStarred(boolean starred) {
			this.starred = starred;
			return this;
		}
		
		public Builder setHidden(boolean hidden) {
			this.hidden = hidden;
			return this;
		}
		
		public Builder setTrashed(boolean trashed) {
			this.trashed = trashed;
			return this;
		}
		
		public Builder setRestricted(boolean restricted) {
			this.restricted = restricted;
			return this;
		}
		
		public Builder setViewed(boolean viewed) {
			this.viewed = viewed;
			return this;
		}
		
		public Builder setLastViewedByMeDate(Date lastViewedByMeDate) {
			this.lastViewedByMeDate = lastViewedByMeDate;
			return this;
		}
		
		public Builder setMimeType(String mimeType) {
			this.mimeType = mimeType;
			return this;
		}
		
		public Builder setModifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
			return this;
		}
		
		public Builder setParents(Collection<String> parentIds) {
			this.parentIds = parentIds;
			return this;
		}
		
		public Builder setParents(String... parentIds) {
			return setParents(Arrays.asList(parentIds));
		}
		
		public DriveFile build() {
			DriveFile file = new DriveFile();
			file.title = title;
			file.description = description;
			file.indexableText = new IndexableTextObject(indexableText);
			file.labels = new Labels();
			file.labels.starred = starred;
			file.labels.hidden = hidden;
			file.labels.trashed = trashed;
			file.labels.restricted = restricted;
			file.labels.viewed = viewed;
			file.lastViewedByMeDate = lastViewedByMeDate;
			file.mimeType = mimeType;
			file.modifiedDate = modifiedDate;
			file.parents = new ArrayList<DriveFileParent>();
			for(String parentId : parentIds) {
				file.parents.add(new DriveFileParent(parentId));
			}
			return file;
		}
	}
	
	private static class Labels {
		
		@JsonProperty
		private boolean starred;
		
		@JsonProperty
		private boolean hidden;
		
		@JsonProperty
		private boolean trashed;
		
		@JsonProperty
		private boolean restricted;
		
		@JsonProperty
		private boolean viewed;
	}
	
	private static class IndexableTextObject {
		
		@SuppressWarnings("unused")
		private String text;
		
		@JsonCreator
		private IndexableTextObject(@JsonProperty String text) {
			this.text = text;
		}
	}
	
	private String title;
	
	private String description;
	
	private String selfLink;
	
	private String alternateLink;
	
	private String embedLink;
	
	private String thumbnailLink;
	
	private String mimeType;
	
	@JsonProperty
	private Labels labels;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Date lastViewedByMeDate;
	
	private List<DriveFileParent> parents;
	
	private Map<String, String> exportLinks;
	
	private UserPermission userPermission;
	
	private long quotaBytesUsed;
	
	private List<String> ownerNames;
	
	private String lastModifyingUserName;
	
	private boolean editable;
	
	private boolean writersCanShare;
	
	private boolean appDataContents;
	
	/**
	 * This field is write-only
	 */
	@JsonProperty
	private IndexableTextObject indexableText;

	public boolean isFolder() {
		return FOLDER.equals(mimeType);
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public String getAlternateLink() {
		return alternateLink;
	}

	public String getEmbedLink() {
		return embedLink;
	}

	public String getThumbnailLink() {
		return thumbnailLink;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public Date getLastViewedByMeDate() {
		return lastViewedByMeDate;
	}

	public List<DriveFileParent> getParents() {
		return parents;
	}

	public Map<String, String> getExportLinks() {
		return exportLinks;
	}

	public UserPermission getUserPermission() {
		return userPermission;
	}

	public long getQuotaBytesUsed() {
		return quotaBytesUsed;
	}

	public List<String> getOwnerNames() {
		return ownerNames;
	}

	public String getLastModifyingUserName() {
		return lastModifyingUserName;
	}

	public boolean isEditable() {
		return editable;
	}

	public boolean isWritersCanShare() {
		return writersCanShare;
	}

	public boolean isAppDataContents() {
		return appDataContents;
	}
	
	public boolean isStarred() {
		return labels.starred;
	}
	
	public boolean isHidden() {
		return labels.hidden;
	}
	
	public boolean isTrashed() {
		return labels.trashed;
	}
	
	public boolean isRestricted() {
		return labels.restricted;
	}
	
	public boolean isViewed() {
		return labels.viewed;
	}
	
}
