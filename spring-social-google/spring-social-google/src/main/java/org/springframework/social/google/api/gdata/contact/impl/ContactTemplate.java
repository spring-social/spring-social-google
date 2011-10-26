package org.springframework.social.google.api.gdata.contact.impl;

import static org.springframework.social.google.api.gdata.impl.ElementBuilder.newAtomEntryBuilder;
import static org.springframework.social.google.api.gdata.impl.ElementBuilder.newGDataElementBuilder;
import static org.springframework.social.google.api.gdata.impl.Namespaces.ContactNamespace;

import java.util.List;

import nu.xom.Element;

import org.springframework.social.google.api.gdata.contact.Contact;
import org.springframework.social.google.api.gdata.contact.ContactGroup;
import org.springframework.social.google.api.gdata.contact.ContactGroupQueryBuilder;
import org.springframework.social.google.api.gdata.contact.ContactOperations;
import org.springframework.social.google.api.gdata.contact.ContactQueryBuilder;
import org.springframework.social.google.api.gdata.contact.Email;
import org.springframework.social.google.api.gdata.contact.Phone;
import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.impl.ElementBuilder;
import org.springframework.web.client.RestTemplate;

public class ContactTemplate extends AbstractGDataOperations implements ContactOperations {

	private static final String FEED_PREFIX = "https://www.google.com/m8/feeds/";
	static final String CONTACTS_FEED = FEED_PREFIX + "contacts/default/full";
	static final String GROUPS_FEED = FEED_PREFIX + "groups/default/full";
	
	public ContactTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public List<Contact> getContactList() {
		return contactQuery().maxResultsNumber(Integer.MAX_VALUE).getList();
	}

	@Override
	public List<ContactGroup> getContactGroupList() {
		return contactGroupQuery().maxResultsNumber(Integer.MAX_VALUE).getList();
	}
	
	@Override
	public ContactGroup saveContactGroup(ContactGroup contactGroup) {
		Element entry = newAtomEntryBuilder()
			.setTitle(contactGroup.getName())
			.getElement();
		if(contactGroup.getSelf() == null) {
			return postEntry(GROUPS_FEED, entry, new ContactGroupExtractor());
		} else {
			return putEntry(contactGroup.getSelf(), entry, new ContactGroupExtractor());
		}
	}
	
	@Override
	public ContactGroup getContactGroup(String url) {
		return extractEntry(url, new ContactGroupExtractor());
	}

	@Override
	public List<Contact> getGroupContacts(ContactGroup group) {
		return contactQuery().onGroup(group).maxResultsNumber(Integer.MAX_VALUE).getList();
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
			.addElement(newGDataElementBuilder("name")
				.addGDataElement("namePrefix", contact.getNamePrefix())
				.addGDataElement("givenName", contact.getFirstName())
				.addGDataElement("additionalName", contact.getMiddleName())
				.addGDataElement("familyName", contact.getLastName())
				.addGDataElement("nameSuffix", contact.getNameSuffix()));
		
		for(String groupId : contact.getGroupIds()) {
			builder.addElement(
				new ElementBuilder("gContact:groupMembershipInfo", ContactNamespace)
					.setHref(groupId));
		}
		
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
			return postEntry(CONTACTS_FEED, builder.getElement(), new ContactExtractor());
		}
	}
	
	@Override
	public void deleteContact(String url) {
		deleteEntry(url);
	}

	@Override
	public byte[] getProfilePicture(String url) {
		return getBinaryContent(url);
	}

	@Override
	public void uploadProfilePicture(String url, byte[] content) {
		
		// Service returns 403 on POST/PUT to photo URL, so we work around this by 
		// replacing the email part of the URL with "default"
		int index = url.lastIndexOf('/') + 1;
		String idSuffix = url.substring(index);
		String fixedUrl = "https://www.google.com/m8/feeds/photos/media/default/" + idSuffix;
		
		putBinaryContent(fixedUrl, content, "image/*");
	}

	@Override
	public ContactQueryBuilder contactQuery() {
		return new ContactQueryBuilderImpl(CONTACTS_FEED, this);
	}

	@Override
	public ContactGroupQueryBuilder contactGroupQuery() {
		return new ContactGroupQueryBuilderImpl(GROUPS_FEED, this);
	}

}
