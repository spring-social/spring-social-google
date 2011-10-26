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
package org.springframework.social.google.api.gdata.contact;

/**
 * Model class representing a contact's e-mail.
 * @author Gabriel Axel
 */
public class Email {

	private final String rel;
	private final String label;
	private final String address;
	private final boolean primary;
	
	public Email(String rel, String label, String address, boolean primary) {
		this.rel = rel;
		this.label = label;
		this.address = address;
		this.primary = primary;
	}
	
	@Override
	public String toString() {
		return address;
	}
	
	public String getRel() {
		return rel;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getAddress() {
		return address;
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
}
