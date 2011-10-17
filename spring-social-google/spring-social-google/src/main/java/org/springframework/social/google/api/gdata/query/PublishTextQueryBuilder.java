package org.springframework.social.google.api.gdata.query;

import java.util.Date;

public interface PublishTextQueryBuilder<Q extends QueryBuilder<?, T>, T> extends TextQueryBuilder<Q, T> {
	Q publishedFrom(Date publishedMin);
	Q publishedUntil(Date publishedMax);
}
