package org.springframework.social.google.api.query;

import java.util.Date;
import java.util.List;

public interface QueryBuilder<Q extends QueryBuilder<?, T>, T> {
	Q startingFromIndex(int startIndex);
	Q maxResultsNumber(int maxResults);
	Q updatedFrom(Date updatedMin);
	Q updatedUntil(Date updatedMax);
	List<T> getList();
	Page<T> getPage();
}
