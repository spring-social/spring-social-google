package org.springframework.social.google.api.impl.helper;

import static org.springframework.social.google.api.impl.helper.Namespaces.NamespaceContext;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;
import nu.xom.Nodes;

public abstract class EntryExtractor<T> {

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
	
	protected List<Element> getGDataElements(Element element, String elementName) {
		Nodes nodes = element.query("gd:" + elementName, NamespaceContext);
		List<Element> elements = new ArrayList<Element>();
		for(int i = 0; i < nodes.size(); i++) {
			elements.add((Element)nodes.get(i));
		}
		return elements;
	}
	
	protected String getNestedGDataValue(Element element, String elementName, String nestedElementName) {
		Nodes nodes = element.query("gd:" + elementName + "/gd:" + nestedElementName, NamespaceContext);
		return nodes.size() == 0 ? null : nodes.get(0).getValue();
	}
	
	protected List<String> getValues(Element element, String prefixedElementName, 
			String filterAttributeName, String filterAttributeValue, String attrributeToFetch) {
		Nodes nodes = element.query(
			prefixedElementName + "[@" + filterAttributeName + "='" + filterAttributeValue + "']/@" + attrributeToFetch, 
			NamespaceContext);
		List<String> values = new ArrayList<String>();
		for(int i = 0; i < nodes.size(); i++) {
			values.add(nodes.get(i).getValue());
		}
		return values;
	}
}
