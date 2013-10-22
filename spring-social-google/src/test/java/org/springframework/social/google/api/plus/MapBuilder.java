package org.springframework.social.google.api.plus;

import java.util.HashMap;

@SuppressWarnings("serial")
class MapBuilder extends HashMap<Object, Object> {
	
	public MapBuilder with(Object key, Object value) {
		put(key, value);
		return this;
	}
	
	static MapBuilder map() {
		return new MapBuilder();
	}
}
