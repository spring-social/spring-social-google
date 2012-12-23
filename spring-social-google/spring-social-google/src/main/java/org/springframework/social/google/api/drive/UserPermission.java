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
 * Model class representing a user permission to a file in Google Drive
 * @author Gabriel Axel
 */
public class UserPermission extends ApiEntity {

	private String name;
	
	private PermissionRole role;
	
	private PermissionType type;
	
	private List<AdditionalRole> additionalRoles;
	
	/**
	 * This field is write-only
	 */
	@JsonProperty
	private String value;
	
	public UserPermission() {
	}

	public UserPermission(PermissionRole role, PermissionType type, List<AdditionalRole> additionalRoles, String value) {
		this.role = role;
		this.type = type;
		this.additionalRoles = additionalRoles;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public PermissionRole getRole() {
		return role;
	}

	public PermissionType getType() {
		return type;
	}

	public List<AdditionalRole> getAdditionalRoles() {
		return additionalRoles;
	}
	
}
