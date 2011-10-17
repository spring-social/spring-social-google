package org.springframework.social.google.api.gdata.contact;

import org.springframework.social.google.api.gdata.query.TextQueryBuilder;

public interface ContactQueryBuilder extends TextQueryBuilder<ContactQueryBuilder, Contact> {
	ContactQueryBuilder onGroup(ContactGroup group);
}
