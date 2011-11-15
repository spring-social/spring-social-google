package org.springframework.social.google.api.gdata.picasa.impl;

import static org.springframework.social.google.api.gdata.impl.Namespaces.NamespaceContext;

import java.util.Date;

import nu.xom.Element;

import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.picasa.Photo;
import org.springframework.social.google.api.gdata.picasa.Visibility;

public class PhotoExtractor extends EntryExtractor<Photo> {

	@Override
	public Photo extractEntry(Element element) {
		
		String id = getPicasaString(element, "id");
		String albumId = getPicasaString(element, "albumid");
		Date published = getPublished(element);
		Date updated = getUpdated(element);
		String title = getTitle(element);
		String summary = getAtomElement(element, "summary");
		Visibility visibility = parseEnum(Visibility.class, getPicasaString(element, "access"));
		int width = getPicasaInt(element, "width");
		int height = getPicasaInt(element, "height");
		int size = getPicasaInt(element, "size");
		String contentType = getAtomAttribute(element, "content", "type");
		String contentUrl = getAtomAttribute(element, "content", "src");
		Element thumbnail= (Element)element.query("media:group/media:thumbnail", NamespaceContext).get(0);
		int thumbnailWidth = Integer.valueOf(thumbnail.getAttributeValue("width"));
		int thumbnailHeight = Integer.valueOf(thumbnail.getAttributeValue("height"));
		String thumbnailUrl = thumbnail.getAttributeValue("url");
		
		return new Photo(id, albumId, published, updated, title, summary, visibility, width, height, size, contentType, contentUrl, thumbnailWidth, thumbnailHeight, thumbnailUrl);
	}
	
	private String getPicasaString(Element element, String elementName) {
		return getElementValue(element, "gphoto:" + elementName);
	}

	private int getPicasaInt(Element element, String elementName) {
		return Integer.valueOf(getPicasaString(element, elementName));
	}
}
