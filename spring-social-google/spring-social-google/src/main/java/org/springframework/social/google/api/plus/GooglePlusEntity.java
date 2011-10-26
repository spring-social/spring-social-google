package org.springframework.social.google.api.plus;

public abstract class GooglePlusEntity {

	private final String id;

	protected GooglePlusEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
