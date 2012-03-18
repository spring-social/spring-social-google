package org.springframework.social.google.api.plus.person.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.person.Person;

public class ContactsResponse {

	private int startIndex;
	
	@JsonProperty("totalResults")
	private int total;
	
	@JsonProperty("entry")
	private List<Person> items;
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public int getTotal() {
		return total;
	}
	
	public List<Person> getItems() {
		return items;
	}
	
}
