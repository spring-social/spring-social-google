package org.springframework.social.google.api.gdata.contact;

import java.util.List;

/**
 * Defines operations for reading and managing contacts and contact groups.
 * Requires OAuth2 scope https://www.google.com/m8/feeds
 * @author Gabriel Axel
 *
 */
public interface ContactOperations {

	/**
	 * Retrieves all the authenticated user's contacts.
	 * @return a list of {@link Contact}s of the user
	 */
	List<Contact> getContactList();
	
	/**
	 * Retrieves all the contacts of from a specific group.
	 * @param group the group to get the users from
	 * @return a list of {@link Contact}s from the group
	 */
	List<Contact> getGroupContacts(ContactGroup group);

	/**
	 * Retrieves all the authenticated user's contact groups.
	 * @return a list of {@link ContactGroup}s of the user
	 */
	List<ContactGroup> getContactGroupList();

	/**
	 * Creates or updates a contact group.
	 * @param contactGroup a {@link ContactGroup} to save or update
	 * @return the saved {@link ContactGroup}s
	 */
	ContactGroup saveContactGroup(ContactGroup contactGroup);

	/**
	 * Retrieves a contact group by its URL.
	 * @param url the URL of the contact group
	 * @return the retrieved {@link ContactGroup}
	 */
	ContactGroup getContactGroup(String url);

	/**
	 * Deletes a contact group.
	 * @param url the URL of the contact group to delete
	 */
	void deleteContactGroup(String url);

	/**
	 * Retrieves a contact by its URL
	 * @param url the URL of the contact to retrieve
	 * @return the retrieved {@link Contact}
	 */
	Contact getContact(String url);
	
	/**
	 * Creates of updates a contact.
	 * @param contact the {@link Contact} to create or update
	 * @return the saved {@link Contact}
	 */
	Contact saveContact(Contact contact);
	
	/**
	 * Deletes a contact.
	 * @param url The URL of the contact to delete
	 */
	void deleteContact(String url);
	
	/**
	 * Retrieves a contact's profile picture.
	 * @param url The profile picture URL
	 * @return an array of bytes containing the profile picture
	 */
	byte[] getProfilePicture(String url);
	
	/**
	 * Upload a new profile picture URL for a contact
	 * @param url The profile picture URL
	 * @param content an array of bytes containing the new picture
	 */
	void uploadProfilePicture(String url, byte[] content);
	
	/**
	 * Creates a {@link ContactQueryBuilder}
	 * @return a new {@link ContactQueryBuilder}
	 */
	ContactQueryBuilder contactQuery();
	
	/**
	 * Creates a {@link ContactGroupQueryBuilder}
	 * @return a new {@link ContactQueryBuilder}
	 */
	ContactGroupQueryBuilder contactGroupQuery();
}
