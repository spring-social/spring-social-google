package org.springframework.social.google.api;

import java.util.List;

public interface ContactOperations {

	List<Contact> getContactList();
	
	List<Contact> getGroupContacts(String groupId);

	List<ContactGroup> getContactGroupList();

	ContactGroup createContactGroup(String name);

	ContactGroup getContactGroup(String url);
}
