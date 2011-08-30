package org.springframework.social.google.api.impl.contact;

import java.util.List;

import nu.xom.Element;

import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.ContactGroup;
import org.springframework.social.google.api.ContactOperations;
import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.impl.helper.EntryBuilder;
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
		Element requestEntry = new EntryBuilder()
			.setTitle(name)
			.getEntry();
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

}
