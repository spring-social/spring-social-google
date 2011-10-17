package org.springframework.social.google.api.impl.contact;

import org.springframework.social.google.api.contact.Contact;
import org.springframework.social.google.api.contact.ContactGroup;
import org.springframework.social.google.api.contact.ContactQueryBuilder;
import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.impl.query.TextQueryBuilderImpl;

public class ContactQueryBuilderImpl extends TextQueryBuilderImpl<ContactQueryBuilder, Contact> implements ContactQueryBuilder {

	private ContactGroup group;
	
	public ContactQueryBuilderImpl(String feedUrl,
			AbstractGoogleOperations operations) {
		super(feedUrl, operations, new ContactExtractor());
	}

	@Override
	public ContactQueryBuilder onGroup(ContactGroup group) {
		this.group = group;
		return this;
	}
	
	@Override
	public StringBuilder build() {
		StringBuilder sb = super.build();
		if(group != null) {
			appendQueryParam(sb, "group", group.getId());
		}
		return sb;
	}

}
