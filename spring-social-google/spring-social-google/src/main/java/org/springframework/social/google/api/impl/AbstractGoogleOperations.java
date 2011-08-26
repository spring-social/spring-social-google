package org.springframework.social.google.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;

public class AbstractGoogleOperations {

	protected final RestTemplate restTemplate;
	private final boolean isAuthorized;

	public AbstractGoogleOperations(RestTemplate restTemplate, boolean isAuthorized) {
		this.restTemplate = restTemplate;
		this.isAuthorized = isAuthorized;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException();
		}
	}
	
	protected <E> List<E> extractFeedEntries(String url, EntryExtractor<E> extractor) {
		
		Feed feed = restTemplate.getForObject(url, Feed.class);
		
		@SuppressWarnings("unchecked")
		List<Entry> entries = (List<Entry>)feed.getEntries();
		
		List<E> list = new ArrayList<E>();
		for(Entry entry : entries) {
			list.add(extractor.extractEntry(entry));
		}
		
		return list;
	}
	
	protected static String getLinkHref(Entry entry, String rel) {
		
		@SuppressWarnings("unchecked")
		List<Link> links = (List<Link>)entry.getOtherLinks();
		for(Link link : links) {
			if(rel.equals(link.getRel())) {
				return link.getHref();
			}
		}
		return null;
	}
	
	protected static String getSelf(Entry entry) {
		return getLinkHref(entry, "self");
	}
	
	protected static String getForeignMarkupAttribute(Entry entry, String elementName, 
			String attributeName, String attributeValue, String fetchAttribute) {
		
		@SuppressWarnings("unchecked")
		List<Element> foreignMarkup = (List<Element>)entry.getForeignMarkup();
		for(Element gd : foreignMarkup) {
			if(gd.getName().equals(elementName) && 
					attributeValue.equals(gd.getAttributeValue(attributeName))) {
				if(fetchAttribute == null) {
					return gd.getValue();
				} else {
					return gd.getAttributeValue(fetchAttribute);
				}
			}
		}
		return null;
	}
	
}
