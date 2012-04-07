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
package org.springframework.social.google.api.plus.person;

/**
 * Model class representing a contact's address
 * @author Suryakant Shinde
 * @author Gabriel Axel
 */
public class Address {

	private String type;
	private String streetAddress;
	private String locality;
	private String region;
	private String postalCode;
	private String country;
	private String formatted;
	
	@Override
	public String toString() {
		return formatted;
	}
	
	public String getType() {
		return type;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public String getRegion() {
		return region;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getFormatted() {
		return formatted;
	}
	
}
