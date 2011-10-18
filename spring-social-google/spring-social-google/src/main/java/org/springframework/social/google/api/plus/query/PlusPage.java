package org.springframework.social.google.api.plus.query;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.GooglePlusEntity;

public class PlusPage<T extends GooglePlusEntity> {

	private final List<T> items;
	private final String nextPageToken;
	
	@JsonCreator
	public PlusPage(@JsonProperty("items") List<T> items, @JsonProperty("nextPageToken") String nextPageToken) {
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
