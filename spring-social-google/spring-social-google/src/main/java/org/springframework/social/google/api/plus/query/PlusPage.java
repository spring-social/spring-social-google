package org.springframework.social.google.api.plus.query;

import java.util.List;

public class PlusPage<T> {

	private final List<T> items;
	private final String nextPageToken;
	
	public PlusPage(List<T> items, String nextPageToken) {
		this.items = items;
		this.nextPageToken = nextPageToken;
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public String getNextPageToken() {
		return nextPageToken;
	}
	
}
