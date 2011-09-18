package org.springframework.social.google.api.contact;

import java.util.List;

public interface ContactOperations {

	List<Contact> getContactList();
	
	List<Contact> getGroupContacts(String groupId);

	List<ContactGroup> getContactGroupList();

	ContactGroup saveContactGroup(ContactGroup contactGroup);

	ContactGroup getContactGroup(String url);

	void deleteContactGroup(String url);

	Contact getContact(String url);
	
	Contact saveContact(Contact contact);
	
	byte[] getProfilePicture(String url);
	
	void uploadProfilePicture(String url, byte[] content);
}
