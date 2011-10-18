package org.springframework.social.google.api.gdata.query;

public interface TextQueryBuilder<Q extends GDataQueryBuilder<?, T>, T> extends GDataQueryBuilder<Q, T> {
	Q searchFor(String text);
}
