package org.springframework.social.google.api.impl.helper;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.social.google.api.impl.helper.Namespaces.AtomNamespace;
import static org.springframework.social.google.api.impl.helper.Namespaces.GdataNamespace;
import nu.xom.Attribute;
import nu.xom.Element;

public class ElementBuilder {

	public static ElementBuilder newAtomEntryBuilder() {
		return new ElementBuilder("entry", AtomNamespace);
	}
		
	public static ElementBuilder newGDataElementBuilder(String name) {
		return new ElementBuilder("gd:" + name, GdataNamespace);
	}
	
	private final Element element;
	
	public ElementBuilder(String name, String uri) {
		element = new Element(name, uri);
	}
	
	public Element getElement() {
		return element;
	}
	
	public ElementBuilder addElement(ElementBuilder child) {
		element.appendChild(child.getElement());
		return this;
	}
	
	public ElementBuilder addAttribute(String namespace, String name, String value) {
		element.addAttribute(new Attribute(name, namespace, value));
		return this;
	}
	
	public ElementBuilder addAttribute(String name, String value) {
		if(hasText(value)) {
			element.addAttribute(new Attribute(name, value));
		}
		return this;
	}
	
	public ElementBuilder addAttribute(String name, boolean value) {
		return addAttribute(name, Boolean.toString(value));
	}

	public ElementBuilder addSimpleAtomElement(String name, String value) {
		if(hasText(value)) {
			Element child = new Element(name, AtomNamespace);
			child.appendChild(value);
			element.appendChild(child);
		}
		return this;
	}
	
	public ElementBuilder addGDataElement(String name, String value) {
		if(hasText(value)) {
			Element child = new Element("gd:" + name, GdataNamespace);
			child.appendChild(value);
			element.appendChild(child);
		}
		return this;
	}

	public ElementBuilder setId(String id) {
		return addSimpleAtomElement("id", id);
	}

	public ElementBuilder setTitle(String title) {
		return addSimpleAtomElement("title", title);
	}
	
	public ElementBuilder setRel(String rel) {
		return addAttribute("rel", rel);
	}

	public ElementBuilder setValue(String value) {
		element.appendChild(value);
		return this;
	}
}
