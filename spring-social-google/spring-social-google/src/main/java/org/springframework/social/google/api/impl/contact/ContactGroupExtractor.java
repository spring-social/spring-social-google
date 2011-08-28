package org.springframework.social.google.api.impl.contact;

import nu.xom.Element;

import org.springframework.social.google.api.ContactGroup;
import org.springframework.social.google.api.impl.helper.EntryExtractor;

public class ContactGroupExtractor extends EntryExtractor<ContactGroup> {

	@Override
	public ContactGroup extractEntry(Element element) {
		String id = getId(element);
		String name = getTitle(element);
		String self = getSelf(element);
		return new ContactGroup(id, name, self);
	}

}
