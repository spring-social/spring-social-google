package org.springframework.social.google.api.plus.query;

import org.springframework.social.google.api.query.QueryBuilder;

/**
 * {@link QueryBuilder} extension for Google+.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public interface PlusQueryBuilder<Q extends PlusQueryBuilder<?, T>, T extends PlusPage<?>> extends QueryBuilder<Q, T> {

	Q searchFor(String text);
	Q fromPage(String pageToken);
	T getPage();
}
