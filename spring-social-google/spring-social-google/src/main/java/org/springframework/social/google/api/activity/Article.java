package org.springframework.social.google.api.activity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("article")
public class Article extends Attachment {

	public Article(@JsonProperty("url") String url, @JsonProperty("displayName") String displayName,
			@JsonProperty("content") String content, @JsonProperty("image") PreviewImage previewImage) {
		super(url, displayName, content, previewImage);
	}

	
}
