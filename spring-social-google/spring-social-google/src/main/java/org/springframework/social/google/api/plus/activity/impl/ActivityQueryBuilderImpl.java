/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.plus.activity.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.plus.activity.ActivitiesOrder;
import org.springframework.social.google.api.plus.activity.ActivitiesPage;
import org.springframework.social.google.api.plus.activity.ActivityQueryBuilder;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;

/**
 * {@link ActivityQueryBuilder} implementation.
 * @author Gabriel Axel
 */
public class ActivityQueryBuilderImpl extends ApiQueryBuilderImpl<ActivityQueryBuilder, ActivitiesPage> implements ActivityQueryBuilder {
	
	private String text;
	private ActivitiesOrder order;
	
	public ActivityQueryBuilderImpl(AbstractGoogleApiOperations operations) {
		super("https://www.googleapis.com/plus/v1/activities", ActivitiesPage.class, operations);
	}
	
	@Override
	public ActivityQueryBuilder searchFor(String text) {
		this.text = text;
		return this;
	}
	
	@Override
	public ActivityQueryBuilder orderBy(ActivitiesOrder order) {
		this.order = order;
		return this;
	}

	@Override
	protected StringBuilder build() {
		
		StringBuilder sb = super.build();
		appendQueryParam(sb, "query", text);
		appendQueryParam(sb, "orderBy", order);
		return sb;
	}
}
