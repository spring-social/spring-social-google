package org.springframework.social.google.api.gdata.picasa.impl;

import nu.xom.Element;

import org.springframework.social.google.api.gdata.impl.EntryExtractor;
import org.springframework.social.google.api.gdata.picasa.Photo;

public class PhotoExtractor extends EntryExtractor<Photo> {

	@Override
	public Photo extractEntry(Element entry) {
		return new Photo();
	}

}
