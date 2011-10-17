package org.springframework.social.google.api.gdata.picasa.impl;

import java.util.Date;

import nu.xom.Element;

import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.picasa.Album;
import org.springframework.social.google.api.gdata.picasa.Visibility;

public class AlbumExtractor extends EntryExtractor<Album> {

	@Override
	public Album extractEntry(Element element) {
		
		String id = getPicasaElement(element, "id");
		Date published = getPublished(element);
		Date updated = getUpdated(element);
		String title = getTitle(element);
		String summary = getAtomElement(element, "summary");
		Visibility visibility = parseEnum(Visibility.class, getPicasaElement(element, "access"));
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
