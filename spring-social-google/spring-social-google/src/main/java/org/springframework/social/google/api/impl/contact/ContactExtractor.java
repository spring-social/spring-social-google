package org.springframework.social.google.api.impl.contact;

import nu.xom.Element;

import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.impl.helper.EntryExtractor;

public class ContactExtractor extends EntryExtractor<Contact> {

	@Override
	public Contact extractEntry(Element element) {
		String id = getId(element);
		String name = getTitle(element);
		String email = findGdataAttribute(element, "email", "primary", "true", "address");
		String pictureUrl = getLinkHref(element, "http://schemas.google.com/contacts/2008/rel#photo");
		String self = getSelf(element);
		return new Contact(id, name, email, pictureUrl, self);
	}

}
