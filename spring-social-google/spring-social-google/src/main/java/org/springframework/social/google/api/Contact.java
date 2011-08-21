package org.springframework.social.google.api;

public class Contact {

	private final String id;
	private final String name;
	private final String email;
	private final String pictureUrl;
	
	public Contact(String id, String name, String email, String pictureUrl) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.pictureUrl = pictureUrl;
	}
	
	@Override
	public String toString() {
		return name + " <" + email + ">";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}
	
}
