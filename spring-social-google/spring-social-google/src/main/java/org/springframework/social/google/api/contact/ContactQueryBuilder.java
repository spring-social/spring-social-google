package org.springframework.social.google.api.contact;

import org.springframework.social.google.api.query.TextQueryBuilder;

public interface ContactQueryBuilder extends TextQueryBuilder<ContactQueryBuilder, Contact> {
	ContactQueryBuilder onGroup(ContactGroup group);
}
