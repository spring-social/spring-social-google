/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.gdata.contact.impl;

import org.springframework.social.google.api.gdata.contact.Contact;
import org.springframework.social.google.api.gdata.contact.ContactGroup;
import org.springframework.social.google.api.gdata.contact.ContactQueryBuilder;
import org.springframework.social.google.api.gdata.impl.AbstractGDataOperations;
import org.springframework.social.google.api.gdata.query.impl.TextQueryBuilderImpl;

/**
 * {@link ContactQueryBuilder} implementation.
 * @author Gabriel Axel
 */
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
