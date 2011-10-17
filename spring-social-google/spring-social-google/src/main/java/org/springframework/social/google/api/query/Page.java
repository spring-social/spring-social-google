package org.springframework.social.google.api.query;

import java.util.List;

public class Page<T> {

	private final List<T> items;
	private final int offset;
	private final int total;
	
	public Page(List<T> items, int offset, int total) {
		this.items = items;
		this.offset = offset;
		this.total = total;
	}

	public List<T> getItems() {
		return items;
	}

	public int getOffset() {
		return offset;
	}

	public int getTotal() {
		return total;
	}
	
}
