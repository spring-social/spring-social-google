package org.springframework.social.google.api.query;


/**
 * {@link QueryBuilder} extension for Google+.
 * @author Gabriel Axel
 * @param <Q> {@link QueryBuilder} type
 * @param <T> Model type
 */
public interface ApiQueryBuilder<Q extends ApiQueryBuilder<?, T>, T extends ApiPage<?>> extends QueryBuilder<Q, T> {

	Q searchFor(String text);
	Q fromPage(String pageToken);
	T getPage();
}
