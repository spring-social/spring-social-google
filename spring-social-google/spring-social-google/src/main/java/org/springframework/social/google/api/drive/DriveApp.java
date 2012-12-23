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

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing an application that is using Google Drive API
 * 
 * @author Gabriel Axel
 */
public class DriveApp extends ApiEntity {

	public static class AppIcon {
		
		@JsonProperty
		private IconCategory category;
		
		@JsonProperty
		private int size;
		
		@JsonProperty
		private String iconUrl;
	}
	
	private String name;

	private String objectType;

	private boolean supportsCreate;

	private boolean supportsImport;

	private boolean installed;

	private boolean authorized;

	private boolean useByDefault;

	private String productUrl;

	private List<String> primaryMimeTypes;

	private List<String> secondaryMimeTypes;

	private List<String> primaryFileExtensions;

	private List<String> secondaryFileExtensions;

	private List<AppIcon> icons;

	public String getName() {
		return name;
	}

	public String getObjectType() {
		return objectType;
	}

	public boolean isSupportsCreate() {
		return supportsCreate;
	}

	public boolean isSupportsImport() {
		return supportsImport;
	}

	public boolean isInstalled() {
		return installed;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public boolean isUseByDefault() {
		return useByDefault;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public List<String> getPrimaryMimeTypes() {
		return primaryMimeTypes;
	}

	public List<String> getSecondaryMimeTypes() {
		return secondaryMimeTypes;
	}

	public List<String> getPrimaryFileExtensions() {
		return primaryFileExtensions;
	}

	public List<String> getSecondaryFileExtensions() {
		return secondaryFileExtensions;
	}

	public List<AppIcon> getIcons() {
		return icons;
	}
	
}
