/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.impl;

import static java.lang.System.arraycopy;
import static org.springframework.social.google.api.impl.ApiEnumSerializer.enumToString;
import static org.springframework.util.StringUtils.delimitedListToStringArray;

import java.util.HashMap;
import java.util.Map;

/**
 * A builder of a map to be used as the request body of PATCH operations.
 * The following code:
 * <pre>
 * new PatchBuilder()
 * 	.set("labels.starred", true)
 * 	.set("labels.hidden", false)
 * 	.getMap();
 * </pre>
 * returns a Map, which will be serialized to the following JSON:
 * <pre>
 * {
 * 	"labels": {
 * 		"starred": true,
 * 		"hidden": false
 * 		}
 * 	}
 * }
 * </pre>
 * @see AbstractGoogleApiOperations#patch(String, Object, Class)
 * @author Gabriel Axel
 *
 */
public class PatchBuilder {

	public static final String PATH_DELIMITER = ".";
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Map<String, Object> getNode(Map<String, Object> map, String[] path) {
		if(path.length == 0) {
			return map;
		}
		String[] innerPath = new String[path.length-1];
		arraycopy(path, 1, innerPath, 0, innerPath.length);
		@SuppressWarnings("unchecked")
		Map<String, Object> innerMap = (Map<String, Object>)map.get(path[0]);
		if(innerMap == null) {
			innerMap = new HashMap<String, Object>();
			map.put(path[0], innerMap);
		}
		return getNode(innerMap, innerPath);
	}
	
	/**
	 * Adds a property and a value to be set in the PATCH request
	 * @param path the path representing the property, with "." representing nested properties
	 * @param value the value to be set for the property
	 * @return the {@link PatchBuilder} instance
	 */
	public PatchBuilder set(String path, Object value) {
		String[] pathTokens = delimitedListToStringArray(path, PATH_DELIMITER);
		String[] parentPathTokens = new String[pathTokens.length-1];
		arraycopy(pathTokens, 0, parentPathTokens, 0, parentPathTokens.length);
		Map<String, Object> parentNode = getNode(map, parentPathTokens);
		parentNode.put(pathTokens[pathTokens.length-1], value);
		return this;
	}
	
	/**
	 * Adds a property and a value to be set in the PATCH request
	 * @param path the path representing the property, with "." representing nested properties
	 * @param value the value to be set for the property
	 * @return the {@link PatchBuilder} instance
	 */
	public PatchBuilder set(String path, Enum<?> value) {
		return set(path, enumToString(value));
	}
	
	/**
	 * Defines a property to be deleted by setting its value to null.
	 * Equivalent to invoking {@link PatchBuilder#set(String, Object)} with null value
	 * @param path the path representing the property, with "." representing nested properties
	 * @return the {@link PatchBuilder} instance
	 */
	public PatchBuilder delete(String path) {
		return set(path, null);
	}
	
	/**
	 * Returns the map created so far
	 * @return Map with the set properties
	 */
	public Map<String, Object> getMap() {
		return map;
	}
}
