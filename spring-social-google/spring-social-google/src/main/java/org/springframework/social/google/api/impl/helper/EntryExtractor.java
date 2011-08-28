package org.springframework.social.google.api.impl.helper;

import static org.springframework.social.google.api.impl.helper.Namespaces.*;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.XPathContext;

public abstract class EntryExtractor<T> {

	public static final XPathContext NamespaceContext;
	
	static {
		NamespaceContext = new XPathContext();
		NamespaceContext.addNamespace("atom", AtomNamespace);
		NamespaceContext.addNamespace("gd", GdataNamespace);
	}
	
	public abstract T extractEntry(Element entry);
	
	protected String find(Element element, String namespacePrefix, String elementName, String filterAttributeName, 
			String filterAttributeValue, String attributeToFetch) {
		
		StringBuilder sb = new StringBuilder(namespacePrefix).append(':').append(elementName);
		
		if(filterAttributeName != null) {
			sb.append("[@").append(filterAttributeName).append("='").append(filterAttributeValue).append("']");
		}
		
		if(attributeToFetch != null) {
			sb.append("/@").append(attributeToFetch);
		}
		
		Nodes nodes = element.query(sb.toString(), NamespaceContext);
		if(nodes.size() == 0) {
			return null;
		}
		return nodes.get(0).getValue();
	}
	
	protected String findGdataAttribute(Element element, String elementName, String filterAttributeName, 
			String filterAttributeValue, String attributeToFetch) {
		return find(element, "gd", elementName, filterAttributeName, filterAttributeValue, attributeToFetch);
	}
	
	protected String getAtomElement(Element element, String elementName) {
		return find(element, "atom", elementName, null, null, null);
	}
	
	protected String getLinkHref(Element element, String rel) {
		return find(element, "atom", "link", "rel", rel, "href");
	}
	
	protected String getSelf(Element element) {
		return getLinkHref(element, "self");
	}
	
	protected String getId(Element element) {
		return getAtomElement(element, "id");
	}
	
	protected String getTitle(Element element) {
		return getAtomElement(element, "title");
	}
}
