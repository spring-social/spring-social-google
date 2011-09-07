package org.springframework.social.quickstart.contact;

import org.hibernate.validator.constraints.NotBlank;

public class ContactGroupForm {

	private String id;
	
	@NotBlank(message="Group name may not be empty")
	private String name;
	
	private String url;
	
	public ContactGroupForm() {
	}

	public ContactGroupForm(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
