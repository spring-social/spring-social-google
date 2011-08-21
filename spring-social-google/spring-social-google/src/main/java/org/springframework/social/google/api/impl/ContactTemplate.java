package org.springframework.social.google.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.ContactOperations;
import org.springframework.web.client.RestTemplate;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;

public class ContactTemplate extends AbstractGoogleOperations implements ContactOperations {

	public ContactTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getContactList() {
		
		Feed feed = restTemplate.getForObject(
			"https://www.google.com/m8/feeds/contacts/default/full?max-results=999999", Feed.class);
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		for(Entry entry : (List<Entry>)feed.getEntries()) { 
			
			String id = entry.getId();
			String name = entry.getTitle();
			String email = null;
			String pictureUrl = null;
			
			List<Element> foreignMarkup = (List<Element>)entry.getForeignMarkup();
			for(Element gd : foreignMarkup) {
				if(gd.getName().equals("email") && 
						"true".equals(gd.getAttributeValue("primary"))) {
					email = gd.getAttributeValue("address");
				}
			}
			
			for(Link link : (List<Link>)entry.getOtherLinks()) {
				if("http://schemas.google.com/contacts/2008/rel#photo".equals(link.getRel())) {
					pictureUrl = link.getHref();
				}
			}
			
			Contact contact = new Contact(id, name, email, pictureUrl);
			contacts.add(contact);
		}
		
		return contacts;
	}

}
