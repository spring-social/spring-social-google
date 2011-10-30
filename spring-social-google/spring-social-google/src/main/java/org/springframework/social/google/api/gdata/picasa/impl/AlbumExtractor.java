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
package org.springframework.social.google.api.gdata.picasa.impl;

import java.util.Date;

import nu.xom.Element;

import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.picasa.Album;
import org.springframework.social.google.api.gdata.picasa.Visibility;

/**
 * {@link EntryExtractor} for {@link Album}.
 * @author Gabriel Axel
 */
public class AlbumExtractor extends EntryExtractor<Album> {

	@Override
	public Album extractEntry(Element element) {
		
		String id = getPicasaElement(element, "id");
		Date published = getPublished(element);
		Date updated = getUpdated(element);
		String title = getTitle(element);
		String summary = getAtomElement(element, "summary");
		Visibility visibility = parseEnum(Visibility.class, getAtomElement(element, "rights"));
		String authorId = getPicasaElement(element, "user");
		String authorName = getPicasaElement(element, "nickname");
		int numberOfPhotos = Integer.valueOf(getPicasaElement(element, "numphotos"));
		String thumbnailUrl = find(element, "media:group/media:thumbnail", null, null, "url");
		
		return new Album(id, published, updated, title, summary, visibility, authorId, authorName, numberOfPhotos, thumbnailUrl);
	}

	private String getPicasaElement(Element element, String elementName) {
		return getElementValue(element, "gphoto:" + elementName);
	}
			
}
