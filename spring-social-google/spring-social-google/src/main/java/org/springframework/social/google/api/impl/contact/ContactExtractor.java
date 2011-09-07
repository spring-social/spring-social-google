package org.springframework.social.google.api.impl.contact;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.Email;
import org.springframework.social.google.api.Phone;
import org.springframework.social.google.api.impl.helper.EntryExtractor;

public class ContactExtractor extends EntryExtractor<Contact> {

	@Override
	public Contact extractEntry(Element element) {
		String id = getId(element);
		String pictureUrl = getLinkHref(element, "http://schemas.google.com/contacts/2008/rel#photo");
		String self = getSelf(element);
		
		String namePrefix = getNestedGDataValue(element, "name", "namePrefix");
		String firstName = getNestedGDataValue(element, "name", "givenName");
		String middleName = getNestedGDataValue(element, "name", "additionalName");
		String lastName = getNestedGDataValue(element, "name", "familyName");
		String nameSuffix = getNestedGDataValue(element, "name", "nameSuffix");
		
		List<Element> emailElements = getGDataElements(element, "email");
		List<Email> emails = new ArrayList<Email>();
		for(Element emailElement : emailElements) {
			String rel = emailElement.getAttributeValue("rel");
			String label = emailElement.getAttributeValue("label");
			String address = emailElement.getAttributeValue("address");
			boolean primary = Boolean.valueOf(emailElement.getAttributeValue("primary"));
			emails.add(new Email(rel, label, address, primary));
		}

		List<Element> phoneElements = getGDataElements(element, "phoneNumber");
		List<Phone> phones = new ArrayList<Phone>();
		for(Element phoneElement : phoneElements) {
			String rel = phoneElement.getAttributeValue("rel");
			String label = phoneElement.getAttributeValue("label");
			String number = phoneElement.getValue();
			boolean primary = Boolean.valueOf(phoneElement.getAttributeValue("primary"));
			phones.add(new Phone(rel, label, number, primary));
		}
		
		return new Contact(id, self, namePrefix, firstName, middleName, lastName, nameSuffix, pictureUrl, emails, phones);
	}

}
