package org.springframework.social.google.api.impl.helper;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.time.DateUtils.parseDate;
import static org.springframework.social.google.api.impl.helper.Namespaces.NamespaceContext;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nu.xom.Element;
import nu.xom.Nodes;

import org.springframework.social.UncategorizedApiException;

public abstract class EntryExtractor<T> {

	private static final String[] datePattern = {"yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"};
	
	public abstract T extractEntry(Element entry);
	
	protected String find(Element element, String prefixedElementName, String filterAttributeName, 
			String filterAttributeValue, String attributeToFetch) {
		
		StringBuilder sb = new StringBuilder(prefixedElementName);
		
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
	
	protected String getElementValue(Element element, String prefixedElementName) {
		return find(element, prefixedElementName, null, null, null);
	}
	
	protected String findGdataAttribute(Element element, String elementName, String filterAttributeName, 
			String filterAttributeValue, String attributeToFetch) {
		return find(element, "gd:" + elementName, filterAttributeName, filterAttributeValue, attributeToFetch);
	}
	
	protected String getAtomElement(Element element, String elementName) {
		return find(element, "atom:" + elementName, null, null, null);
	}
	
	protected String getLinkHref(Element element, String rel) {
		return find(element, "atom:link", "rel", rel, "href");
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
	
	protected Date getDateValue(Element element, String elementName) {
		String stringValue = getAtomElement(element, elementName);
		if(isBlank(stringValue)) {
			return null;
		}
		try {
			return parseDate(stringValue, datePattern);
		} catch (ParseException e) {
			throw new UncategorizedApiException("Invalid date format ", e);
		}
	}
	
	protected Date getPublished(Element element) {
		return getDateValue(element, "published");
	}
	
	protected Date getUpdated(Element element) {
		return getDateValue(element, "updated");
	}
	
	@SuppressWarnings("hiding")
	protected <T extends Enum<T>> T parseEnum(Class<T> enumType, String value) {
		return Enum.valueOf(enumType, value.toUpperCase());
	}
}
