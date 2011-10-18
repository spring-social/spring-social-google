package org.springframework.social.google.api.gdata.contact.impl;

import org.springframework.social.google.api.gdata.contact.ContactGroup;
import org.springframework.social.google.api.gdata.contact.ContactGroupQueryBuilder;
import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.query.impl.GDataQueryBuilderImpl;

class ContactGroupQueryBuilderImpl extends GDataQueryBuilderImpl<ContactGroupQueryBuilder, ContactGroup> implements ContactGroupQueryBuilder {

	ContactGroupQueryBuilderImpl(String feedUrl, AbstractGDataOperations operations) {
		super(feedUrl, operations, new ContactGroupExtractor());
	}

}
