package org.springframework.social.google.api.plus.person;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.plus.query.PlusPage;

public class PeoplePage extends PlusPage<BasePerson> {

	public PeoplePage(@JsonProperty("items") List<BasePerson> items, @JsonProperty("nextPageToken") String nextPageToken) {
		super(items, nextPageToken);
	}

}
