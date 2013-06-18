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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Model class representing Google Drive general details for the current user
 * 
 * @author Gabriel Axel
 */
public class DriveAbout {

	public static class FormatMapping {

		@JsonProperty
		private String source;

		@JsonProperty
		private List<String> targets;

		public String getSource() {
			return source;
		}

		public List<String> getTargets() {
			return targets;
		}

	}

	public static class RoleSet {

		@JsonProperty
		private PermissionRole primaryRole;

		@JsonProperty
		private List<AdditionalRole> additionalRoles;

		public PermissionRole getPrimaryRole() {
			return primaryRole;
		}

		public List<AdditionalRole> getAdditionalRoles() {
			return additionalRoles;
		}

	}

	public static class RoleInfo {

		@JsonProperty
		private String type;

		@JsonProperty
		private List<RoleSet> roleSets;

		public String getType() {
			return type;
		}

		public List<RoleSet> getRoleSets() {
			return roleSets;
		}

	}

	private static class Feature {

		@JsonProperty
		String featureName;

		@JsonProperty
		Double featureRate;
	}

	private static class MaxUploadSize {

		@JsonProperty
		String type;

		@JsonProperty
		Long size;
	}

	private String name;

	private long quotaBytesTotal;

	private long quotaBytesUsed;

	private long quotaBytesUsedInTrash;

	private long largestChangeId;

	private long remainingChangeIds;

	private String rootFolderId;

	private String domainSharingPolicy;

	private List<FormatMapping> importFormats;

	private List<FormatMapping> exportFormats;

	private List<RoleInfo> additionalRoleInfo;

	private Map<String, Double> features;

	private Map<String, Long> maxUploadSizes;

	private String permissionId;

	@JsonProperty("isCurrentAppInstalled")
	private boolean currentAppInstalled;

	private DriveUser user;

	private long quotaBytesUsedAggregate;

	@JsonSetter
	private void setFeatures(List<Feature> featuresAsList) {
		features = new HashMap<String, Double>();
		for (Feature feature : featuresAsList) {
			features.put(feature.featureName, feature.featureRate);
		}
	}

	@JsonSetter
	private void setMaxUploadSizes(List<MaxUploadSize> maxUploadSizesAsList) {
		maxUploadSizes = new HashMap<String, Long>();
		for (MaxUploadSize maxUploadSize : maxUploadSizesAsList) {
			maxUploadSizes.put(maxUploadSize.type, maxUploadSize.size);
		}
	}

	public String getName() {
		return name;
	}

	public long getQuotaBytesTotal() {
		return quotaBytesTotal;
	}

	public long getQuotaBytesUsed() {
		return quotaBytesUsed;
	}

	public long getQuotaBytesUsedInTrash() {
		return quotaBytesUsedInTrash;
	}

	public long getLargestChangeId() {
		return largestChangeId;
	}

	public long getRemainingChangeIds() {
		return remainingChangeIds;
	}

	public String getRootFolderId() {
		return rootFolderId;
	}

	public String getDomainSharingPolicy() {
		return domainSharingPolicy;
	}

	public List<FormatMapping> getImportFormats() {
		return importFormats;
	}

	public List<FormatMapping> getExportFormats() {
		return exportFormats;
	}

	public List<RoleInfo> getAdditionalRoleInfo() {
		return additionalRoleInfo;
	}

	public Map<String, Double> getFeatures() {
		return features;
	}

	public Map<String, Long> getMaxUploadSizes() {
		return maxUploadSizes;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public boolean isCurrentAppInstalled() {
		return currentAppInstalled;
	}

	public DriveUser getUser() {
		return user;
	}

	public long getQuotaBytesUsedAggregate() {
		return quotaBytesUsedAggregate;
	}

}
