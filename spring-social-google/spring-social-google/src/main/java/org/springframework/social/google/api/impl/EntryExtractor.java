package org.springframework.social.google.api.impl;

import com.sun.syndication.feed.atom.Entry;

interface EntryExtractor<T> {

	T extractEntry(Entry entry);
}
