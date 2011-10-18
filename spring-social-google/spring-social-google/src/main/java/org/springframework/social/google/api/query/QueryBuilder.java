package org.springframework.social.google.api.query;


public interface QueryBuilder<Q extends QueryBuilder<?, T>, T> {
	Q maxResultsNumber(int maxResults);
}
