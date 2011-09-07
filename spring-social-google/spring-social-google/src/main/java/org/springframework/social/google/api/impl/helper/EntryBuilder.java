package org.springframework.social.google.api.impl.helper;

import static org.springframework.social.google.api.impl.helper.Namespaces.AtomNamespace;
import nu.xom.Element;

public class EntryBuilder {

	private Element entry = new Element("entry", AtomNamespace);
	
	public EntryBuilder addAtomElement(String name, String value) {
		Element child = new Element(name, AtomNamespace);
		child.appendChild(value);
		entry.appendChild(child);
		return this;
	}
	
	public EntryBuilder setId(String id) {
		return addAtomElement("id", id);
	}
	
	public EntryBuilder setTitle(String title) {
		return addAtomElement("title", title);
	}
	
	public Element getEntry() {
		return entry;
	}
}
