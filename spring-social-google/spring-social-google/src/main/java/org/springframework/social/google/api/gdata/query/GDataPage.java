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
package org.springframework.social.google.api.gdata.query;

import java.util.List;

/**
 * Model representing a pagination result
 * @author Gabriel Axel
 * @param <T> item type
 */
public class GDataPage<T> {

	private final List<T> items;
	private final int offset;
	private final int total;
	
	public GDataPage(List<T> items, int offset, int total) {
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
