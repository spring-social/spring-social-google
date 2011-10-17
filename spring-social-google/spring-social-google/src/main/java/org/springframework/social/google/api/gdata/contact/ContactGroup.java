package org.springframework.social.google.api.gdata.contact;

import java.util.Date;

public class ContactGroup {

	private final String id;
	private final String name;
	private final String self;
	private final Date updated;
	
	public ContactGroup(String id, String name, String self, Date updated) {
		super();
		this.id = id;
		this.name = name;
		this.self = self;
		this.updated = updated;
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
	
	public Date getUpdated() {
		return updated;
	}
}
