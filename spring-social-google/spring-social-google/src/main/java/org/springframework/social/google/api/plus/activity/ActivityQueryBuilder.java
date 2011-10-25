package org.springframework.social.google.api.plus.activity;

import org.springframework.social.google.api.plus.query.PlusQueryBuilder;

public interface ActivityQueryBuilder extends PlusQueryBuilder<ActivityQueryBuilder, ActivitiesPage> {

	ActivityQueryBuilder orderBy(ActivitiesOrder order);

}
