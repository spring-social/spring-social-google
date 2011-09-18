package org.springframework.social.google.api.contact;

public class ContactGroup {

	private final String id;
	private final String name;
	private final String self;
	
	public ContactGroup(String id, String name, String self) {
		super();
		this.id = id;
		this.name = name;
		this.self = self;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSelf() {
		return self;
	}
	
}
