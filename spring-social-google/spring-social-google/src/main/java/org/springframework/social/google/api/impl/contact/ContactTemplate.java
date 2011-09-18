package org.springframework.social.google.api.impl.contact;

import static org.springframework.social.google.api.impl.helper.ElementBuilder.*;
import java.util.List;

import nu.xom.Element;

import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.ContactGroup;
import org.springframework.social.google.api.ContactOperations;
import org.springframework.social.google.api.Email;
import org.springframework.social.google.api.Phone;
import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.impl.helper.ElementBuilder;
import org.springframework.web.client.RestTemplate;

public class ContactTemplate extends AbstractGoogleOperations implements ContactOperations {

	private static final String FEED_PREFIX = "https://www.google.com/m8/feeds/";
	private static final String CONTACTS_FEED = FEED_PREFIX + "contacts/default/full";
	private static final String GROUPS_FEED = FEED_PREFIX + "groups/default/full";
	
	public ContactTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public List<Contact> getContactList() {
		return extractFeedEntries(CONTACTS_FEED + "?max-results=999999", new ContactExtractor());
	}

	@Override
	public List<ContactGroup> getContactGroupList() {
		return extractFeedEntries(GROUPS_FEED, new ContactGroupExtractor());
	}
	
	@Override
	public ContactGroup createContactGroup(String name) {
		Element requestEntry = newAtomEntryBuilder()
			.setTitle(name)
			.getElement();
		return postEntry(GROUPS_FEED, requestEntry, new ContactGroupExtractor());
	}
	
	@Override
	public ContactGroup getContactGroup(String url) {
		return extractEntry(url, new ContactGroupExtractor());
	}

	@Override
	public List<Contact> getGroupContacts(String groupId) {
		return extractFeedEntries(CONTACTS_FEED + "?max-results=999999&group=" + groupId, new ContactExtractor());
	}

	@Override
	public ContactGroup updateContactGroup(ContactGroup contactGroup) {
		Element entry = newAtomEntryBuilder()
			.setTitle(contactGroup.getName())
			.getElement();
		return putEntry(contactGroup.getSelf(), entry, new ContactGroupExtractor());
	}

	@Override
	public void deleteContactGroup(String url) {
		deleteEntry(url);
	}

	@Override
	public Contact getContact(String url) {
		return extractEntry(url, new ContactExtractor());
	}

	@Override
	public Contact saveContact(Contact contact) {

		//TODO: validate id + self

		ElementBuilder builder = newAtomEntryBuilder()
			.setId(contact.getId())
			.addElement(newGDataElementBuilder("name")
				.addGDataElement("namePrefix", contact.getNamePrefix())
				.addGDataElement("givenName", contact.getFirstName())
				.addGDataElement("additionalName", contact.getMiddleName())
				.addGDataElement("familyName", contact.getLastName())
				.addGDataElement("nameSuffix", contact.getNameSuffix()));
		
		for(Email email : contact.getEmails()) {
			builder.addElement(newGDataElementBuilder("email")
				.setRel(email.getRel())
				.addAttribute("label", email.getLabel())
				.addAttribute("address", email.getAddress())
				.addAttribute("primary", email.isPrimary()));
		}
		
		for(Phone phone : contact.getPhones()) {
			builder.addElement(newGDataElementBuilder("phoneNumber")
				.setRel(phone.getRel())
				.addAttribute("label", phone.getLabel())
				.setValue(phone.getNumber())
				.addAttribute("primary", phone.isPrimary()));
		}
				
		if(contact.getSelf() != null) {
			return putEntry(contact.getSelf(), builder.getElement(), new ContactExtractor());
		} else {
			return postEntry(contact.getSelf(), builder.getElement(), new ContactExtractor());
		}
	}

	@Override
	public byte[] getProfilePicture(String url) {
		return getBinaryContent(url);
	}

	@Override
	public void uploadProfilePicture(String url, byte[] content, String contentType) {
		putBinaryContent(url, content, "image/*");
	}


}
