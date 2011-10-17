package org.springframework.social.quickstart.contact;

import org.springframework.social.quickstart.SearchForm;

public class ContactSearchForm extends SearchForm {

	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
