package org.springframework.social.google.api;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GoogleProfile implements Serializable {

	private String id;
	private String username;
	private String firstName;
	private String lastName;
	
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
