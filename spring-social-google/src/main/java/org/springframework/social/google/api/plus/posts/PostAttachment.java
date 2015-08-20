package org.springframework.social.google.api.plus.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.google.api.plus.Activity;

public class PostAttachment extends Activity.Attachment {

	@JsonProperty
	private String id;
	@JsonProperty
	private String url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
