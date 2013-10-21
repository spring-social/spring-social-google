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

import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.social.google.api.tasks.TaskQueryBuilder;
import org.springframework.social.google.api.tasks.TasksPage;
import org.springframework.web.client.RestTemplate;

/**
 * {@link TaskQueryBuilder} implementation.
 * @author Gabriel Axel
 */
class TaskQueryBuilderImpl extends
		ApiQueryBuilderImpl<TaskQueryBuilder, TasksPage> implements TaskQueryBuilder {

	private String taskListId = DEFAULT;
	
	TaskQueryBuilderImpl(RestTemplate restTemplate) {
		super(TasksPage.class, restTemplate);
	}
	
	@Override
	public TaskQueryBuilder fromTaskList(String taskListId) {
		this.taskListId = taskListId;
		return this;
	}
	
	@Override
	public TaskQueryBuilder completedFrom(Date completedMin) {
		return appendQueryParam("completedMin", completedMin);
	}

	@Override
	public TaskQueryBuilder completedUntil(Date completedMax) {
		return appendQueryParam("completedMax", completedMax);
	}

	@Override
	public TaskQueryBuilder dueFrom(Date dueMin) {
		return appendQueryParam("dueMin", dueMin);
	}

	@Override
	public TaskQueryBuilder dueUntil(Date dueMax) {
		return appendQueryParam("dueMax", dueMax);
	}
	
	@Override
	public TaskQueryBuilder updatedFrom(Date updatedMin) {
		return appendQueryParam("updatedMin", updatedMin);
	}

	@Override
	public TaskQueryBuilder includeCompleted(boolean showCompleted) {
		return appendQueryParam("showCompleted", showCompleted);
	}

	@Override
	public TaskQueryBuilder includeDeleted(boolean showDeleted) {
		return appendQueryParam("showDeleted", showDeleted);
	}

	@Override
	public TaskQueryBuilder includeHidden(boolean showHidden) {
		return appendQueryParam("showHidden", showHidden);
	}

	@Override
	protected String build() {
		feedUrl = TASKS_URL + taskListId + TASKS;
		return super.build();
	}
}
