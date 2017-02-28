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

import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;
import static org.springframework.util.StringUtils.hasText;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.tasks.Task;
import org.springframework.social.google.api.tasks.TaskList;
import org.springframework.social.google.api.tasks.TaskListQueryBuilder;
import org.springframework.social.google.api.tasks.TaskListsPage;
import org.springframework.social.google.api.tasks.TaskOperations;
import org.springframework.social.google.api.tasks.TaskQueryBuilder;
import org.springframework.social.google.api.tasks.TasksPage;
import org.springframework.web.client.RestTemplate;

/**
 * {@link TaskOperations} implementation.
 * 
 * @author Gabriel Axel
 */
public class TaskTemplate extends AbstractGoogleApiOperations implements
		TaskOperations {

	static final String TASK_LISTS_URL = "https://www.googleapis.com/tasks/v1/users/@me/lists";
	static final String TASKS_URL = "https://www.googleapis.com/tasks/v1/lists/";

	static final String DEFAULT = "@default";
	static final String TASKS = "/tasks";

	public TaskTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public TaskListsPage getTaskLists() {
		return taskListQuery().maxResultsNumber(100).getPage();
	}

	@Override
	public TaskListsPage getTaskLists(String pageToken) {
		return taskListQuery().fromPage(pageToken).getPage();
	}

	@Override
	public TaskList getTaskList(String id) {
		return getEntity(TASK_LISTS_URL + '/' + id, TaskList.class);
	}

	@Override
	public TaskList saveTaskList(TaskList taskList) {
		return saveEntity(TASK_LISTS_URL, taskList);
	}

	@Override
	public void deleteTaskList(TaskList taskList) {
		deleteTaskList(taskList.getId());
	}

	@Override
	public void deleteTaskList(String taskListId) {
		deleteEntity(TASK_LISTS_URL, taskListId);
	}

	@Override
	public TaskListQueryBuilder taskListQuery() {
		return new TaskListQueryBuilderImpl(restTemplate);
	}

	@Override
	public TasksPage getTasks() {
		return taskQuery().maxResultsNumber(100).getPage();
	}

	@Override
	public TasksPage getTasks(String taskListId, String pageToken) {
		return taskQuery().fromTaskList(taskListId).fromPage(pageToken)
				.getPage();
	}

	@Override
	public Task getTask(String id) {
		return getTask(DEFAULT, id);
	}

	@Override
	public Task getTask(String taskListId, String id) {
		return getEntity(TASKS_URL + taskListId + TASKS + '/' + id, Task.class);
	}

	@Override
	public Task saveTask(Task task) {
		return saveTask(DEFAULT, task);
	}

	@Override
	public Task saveTask(String taskListId, Task task) {
		return saveEntity(TASKS_URL + taskListId + TASKS, task);
	}

	@Override
	public Task createTaskAt(String taskListId, String parent, String previous,
			Task task) {
		isNull(task.getId());
		StringBuilder sb = new StringBuilder(TASKS_URL)
				.append(defaultIfBlank(taskListId, DEFAULT)).append(TASKS)
				.append('?');
		if (hasText(parent)) {
			sb.append("parent=").append(parent).append('&');
		}
		if (hasText(previous)) {
			sb.append("previous=").append(previous);
		}
		return saveEntity(sb.toString(), task);
	}

	@Override
	public Task moveTask(String taskListId, Task task, String parent,
			String previous) {
		notNull(task.getId());
		isTrue(hasText(parent) || hasText(previous),
				"'parent' and/or 'previous' must be set");
		StringBuilder sb = new StringBuilder(TASKS_URL)
				.append(defaultIfBlank(taskListId, DEFAULT)).append(TASKS)
				.append('/').append(task.getId()).append("/move?");
		if (hasText(parent)) {
			sb.append("parent=").append(parent).append('&');
		}
		if (hasText(previous)) {
			sb.append("previous=").append(previous);
		}
		return restTemplate.postForObject(sb.toString(), null, Task.class);
	}

	@Override
	public void deleteTask(String taskId) {
		deleteTask(DEFAULT, taskId);
	}

	@Override
	public void deleteTask(Task task) {
		deleteTask(task.getId());
	}

	@Override
	public void deleteTask(String taskListId, String taskId) {
		deleteEntity(TASKS_URL + taskListId + TASKS, taskId);
	}

	@Override
	public void deleteTask(String taskListId, Task task) {
		deleteTask(taskListId, task.getId());
	}

	@Override
	public TaskQueryBuilder taskQuery() {
		return new TaskQueryBuilderImpl(restTemplate);
	}

	private static String defaultIfBlank(String value, String defaultValue) {
		return hasText(value) ? value : defaultValue;
	}

	@Override
	public void clearCompletedTasks(TaskList taskList) {
		notNull(taskList.getId());
		restTemplate.postForLocation(TASKS_URL + taskList.getId() + "/clear",
				null);
	}
}
