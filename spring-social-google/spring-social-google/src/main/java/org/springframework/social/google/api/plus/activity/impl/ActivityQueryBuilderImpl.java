package org.springframework.social.google.api.plus.activity.impl;

import org.springframework.social.google.api.plus.activity.ActivitiesOrder;
import org.springframework.social.google.api.plus.activity.ActivitiesPage;
import org.springframework.social.google.api.plus.activity.ActivityQueryBuilder;
import org.springframework.social.google.api.plus.impl.AbstractGooglePlusOperations;
import org.springframework.social.google.api.plus.query.impl.PlusQueryBuilderImpl;

public class ActivityQueryBuilderImpl extends PlusQueryBuilderImpl<ActivityQueryBuilder, ActivitiesPage> implements ActivityQueryBuilder {
	
	private ActivitiesOrder order;
	
	public ActivityQueryBuilderImpl(AbstractGooglePlusOperations operations) {
		super("https://www.googleapis.com/plus/v1/activities", ActivitiesPage.class, operations);
	}
	
	@Override
	public ActivityQueryBuilder orderBy(ActivitiesOrder order) {
		this.order = order;
		return this;
	}

	@Override
	protected StringBuilder build() {
		
		StringBuilder sb = super.build();
		
		if(order != null) {
			appendQueryParam(sb, "orderBy", order.toString());
		}
		
		return sb;
	}
}
