package org.springframework.social.google.api;

import java.util.List;

public interface ContactOperations {

	List<Contact> getContactList();
	
	List<Contact> getGroupContacts(String groupId);

	List<ContactGroup> getContactGroupList();

	ContactGroup createContactGroup(String name);

	ContactGroup getContactGroup(String url);

	ContactGroup updateContactGroup(ContactGroup contactGroup);

	void deleteContactGroup(String url);

	Contact getContact(String url);
	
	Contact saveContact(Contact contact);
	
	byte[] getProfilePicture(String url);
	
	void uploadProfilePicture(String url, byte[] content, String contentType);
}
