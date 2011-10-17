package org.springframework.social.google.api.impl.contact;

import org.springframework.social.google.api.contact.ContactGroup;
import org.springframework.social.google.api.contact.ContactGroupQueryBuilder;
import org.springframework.social.google.api.impl.AbstractGoogleOperations;
import org.springframework.social.google.api.impl.query.QueryBuilderImpl;

public class ContactGroupQueryBuilderImpl extends QueryBuilderImpl<ContactGroupQueryBuilder, ContactGroup> implements ContactGroupQueryBuilder {

	public ContactGroupQueryBuilderImpl(String feedUrl,
			AbstractGoogleOperations operations) {
		super(feedUrl, operations, new ContactGroupExtractor());
	}

}
