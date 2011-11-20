package org.springframework.social.google.api.plus.activity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("photo-album")
public class Album extends Attachment {

	@JsonCreator
	public Album(@JsonProperty("url") String url, @JsonProperty("displayName") String displayName) {
		super(url, displayName, null, null);
	}

}
