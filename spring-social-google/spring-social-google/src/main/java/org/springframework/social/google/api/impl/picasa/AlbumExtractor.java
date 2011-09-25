package org.springframework.social.google.api.impl.picasa;

import java.util.Date;

import nu.xom.Element;

import org.springframework.social.google.api.impl.helper.EntryExtractor;
import org.springframework.social.google.api.picasa.Album;
import org.springframework.social.google.api.picasa.Visibility;

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
		
		return new Album(id, published, updated, title, summary, visibility, authorId, authorName, numberOfPhotos);
	}

	private String getPicasaElement(Element element, String elementName) {
		return getElementValue(element, "gphoto", elementName);
	}
	
}
