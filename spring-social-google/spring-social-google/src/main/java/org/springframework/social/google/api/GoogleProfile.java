package org.springframework.social.google.api;

public class GoogleProfile {

	private final String username;
	private final String firstName;
	private final String lastName;
	
	public GoogleProfile(String username, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
}
