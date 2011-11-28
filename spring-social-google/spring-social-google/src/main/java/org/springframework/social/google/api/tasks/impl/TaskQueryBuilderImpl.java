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
package org.springframework.social.google.api.tasks.impl;

import static org.springframework.social.google.api.tasks.impl.TaskTemplate.DEFAULT;
import static org.springframework.social.google.api.tasks.impl.TaskTemplate.TASKS;
import static org.springframework.social.google.api.tasks.impl.TaskTemplate.TASKS_URL;

import java.util.Date;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.social.google.api.tasks.TaskQueryBuilder;
import org.springframework.social.google.api.tasks.TasksPage;

/**
 * {@link TaskQueryBuilder} implementation.
 * @author Gabriel Axel
 */
class TaskQueryBuilderImpl extends
		ApiQueryBuilderImpl<TaskQueryBuilder, TasksPage> implements TaskQueryBuilder {

	private String taskListId = DEFAULT;
	private Date completedMin;
	private Date completedMax;
	private Date dueMin;
	private Date dueMax;
	private Date updatedMin;
	private boolean includeCompleted;
	private boolean includeDeleted;
	private boolean includeHidden;
	
	TaskQueryBuilderImpl(AbstractGoogleApiOperations operations) {
		super(TasksPage.class, operations);
	}
	
	@Override
	public TaskQueryBuilder fromTaskList(String taskListId) {
		this.taskListId = taskListId;
		return this;
	}
	
	@Override
	public TaskQueryBuilder completedFrom(Date completedMin) {
		this.completedMin = completedMin;
		return this;
	}

	@Override
	public TaskQueryBuilder completedUntil(Date completedMax) {
		this.completedMax = completedMax;
		return this;
	}

	@Override
	public TaskQueryBuilder dueFrom(Date dueMin) {
		this.dueMin = dueMin;
		return this;
	}

	@Override
	public TaskQueryBuilder dueUntil(Date dueMax) {
		this.dueMax = dueMax;
		return this;
	}
	
	@Override
	public TaskQueryBuilder updatedFrom(Date updatedMin) {
		this.updatedMin = updatedMin;
		return this;
	}

	@Override
	public TaskQueryBuilder includeCompleted(boolean includeCompleted) {
		this.includeCompleted = includeCompleted;
		return this;
	}

	@Override
	public TaskQueryBuilder includeDeleted(boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
		return this;
	}

	@Override
	public TaskQueryBuilder includeHidden(boolean includeHidden) {
		this.includeHidden = includeHidden;
		return this;
	}

	@Override
	protected StringBuilder build() {

		// Workaround for 503 error when querying by dueMin or dueMax
		if(dueMin != null && dueMax == null) {
			dueMax = new Date(64060581600000L); // 4000 AD
		}
		if(dueMin == null && dueMax != null) {
			dueMin = new Date(0L); // 1970 AD
		}
		
		feedUrl = TASKS_URL + taskListId + TASKS;
		StringBuilder sb = super.build();
		appendQueryParam(sb, "completedMin", completedMin);
		appendQueryParam(sb, "completedMax", completedMax);
		appendQueryParam(sb, "dueMin", dueMin);
		appendQueryParam(sb, "dueMax", dueMax);
		appendQueryParam(sb, "showCompleted", includeCompleted);
		appendQueryParam(sb, "showDeleted", includeDeleted);
		appendQueryParam(sb, "showHidden", includeHidden);
		appendQueryParam(sb, "updatedMin", updatedMin);
		
		return sb;
	}
}
