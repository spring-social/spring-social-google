package org.springframework.social.google.api.impl;

import java.util.List;

import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.ContactGroup;
import org.springframework.social.google.api.ContactOperations;
import org.springframework.web.client.RestTemplate;

import com.sun.syndication.feed.atom.Entry;

public class ContactTemplate extends AbstractGoogleOperations implements ContactOperations {

	public ContactTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public List<Contact> getContactList() {
		
		return extractFeedEntries("https://www.google.com/m8/feeds/contacts/default/full?max-results=999999", 
			new EntryExtractor<Contact>() {

				@Override
				public Contact extractEntry(Entry entry) {
					String id = entry.getId();
					String name = entry.getTitle();
					String email = getForeignMarkupAttribute(entry, "email", "primary", "true", "address");
					String pictureUrl = getLinkHref(entry, "http://schemas.google.com/contacts/2008/rel#photo");
					String self = getSelf(entry);
					return new Contact(id, name, email, pictureUrl, self);
				}
			});
	}

	@Override
	public List<ContactGroup> getContactGroupList() {
		
		return extractFeedEntries("https://www.google.com/m8/feeds/groups/default/full",
			new EntryExtractor<ContactGroup>() {

				@Override
				public ContactGroup extractEntry(Entry entry) {
					String id = entry.getId();
					String name = entry.getTitle();
					String self = getSelf(entry);
					return new ContactGroup(id, name, self);
				}
			});
		
	}
}
