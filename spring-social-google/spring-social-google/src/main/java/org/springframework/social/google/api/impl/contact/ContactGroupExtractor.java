package org.springframework.social.google.api.impl.contact;

import java.util.Date;

import nu.xom.Element;

import org.springframework.social.google.api.contact.ContactGroup;
import org.springframework.social.google.api.impl.helper.EntryExtractor;

public class ContactGroupExtractor extends EntryExtractor<ContactGroup> {

	@Override
	public ContactGroup extractEntry(Element element) {
		String id = getId(element);
		String name = getTitle(element);
		String self = getSelf(element);
		Date updated = getUpdated(element);
		return new ContactGroup(id, name, self, updated);
	}

}
