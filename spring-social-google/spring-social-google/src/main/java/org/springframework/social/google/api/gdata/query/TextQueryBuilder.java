package org.springframework.social.google.api.gdata.query;

public interface TextQueryBuilder<Q extends QueryBuilder<?, T>, T> extends QueryBuilder<Q, T> {
	Q searchFor(String text);
}
