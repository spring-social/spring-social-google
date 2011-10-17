package org.springframework.social.google.api.gdata.contact.impl;

import org.springframework.social.google.api.gdata.contact.Contact;
import org.springframework.social.google.api.gdata.contact.ContactGroup;
import org.springframework.social.google.api.gdata.contact.ContactQueryBuilder;
import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.query.impl.TextQueryBuilderImpl;

public class ContactQueryBuilderImpl extends TextQueryBuilderImpl<ContactQueryBuilder, Contact> implements ContactQueryBuilder {

	private ContactGroup group;
	
	public ContactQueryBuilderImpl(String feedUrl,
			AbstractGDataOperations operations) {
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
