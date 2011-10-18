package org.springframework.social.google.api.gdata.query;

import java.util.Date;
import java.util.List;

import org.springframework.social.google.api.query.QueryBuilder;

public interface GDataQueryBuilder<Q extends GDataQueryBuilder<?, T>, T> extends QueryBuilder<Q, T> {
	Q startingFromIndex(int startIndex);
	Q updatedFrom(Date updatedMin);
	Q updatedUntil(Date updatedMax);
	GDataPage<T> getPage();
	List<T> getList();
}
