package org.springframework.social.google.api.plus.comment;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.google.api.query.ApiPage;

/**
 * {@link ApiPage} for {@link Comment} pagination result.
 * @author Gabriel Axel
 */
public class CommentsPage extends ApiPage<Comment> {

	@JsonCreator
	public CommentsPage(@JsonProperty("items") List<Comment> items,
			@JsonProperty("nextPageToken") String nextPageToken) {
		super(items, nextPageToken);
	}
}
