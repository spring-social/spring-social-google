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
package org.springframework.social.google.api.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;
import org.springframework.social.google.api.tasks.Task;
import org.springframework.social.google.api.tasks.TaskList;
import org.springframework.social.google.api.tasks.TaskListsPage;
import org.springframework.social.google.api.tasks.TaskStatus;
import org.springframework.social.google.api.tasks.TasksPage;

public class TaskTemplateTest extends AbstractGoogleApiTest {

	private static final String DEFAULT = "@default";
	
	private static final String FIRST_TASK_LIST_ID = "MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow";
	private static final String FIRST_TASK_LIST_TITLE = "First Task List";

	private static final String SECOND_TASK_LIST_ID = "MTY1OTA2NzU4OSQyMDAzMjM0MDc6NjI7MDQ0NjgwOjA";
	private static final String SECONDS_TASK_LIST_TITLE = "Seconds Task List";
	
	private static final String COMPLETED_TASK_ID = "MTY1OTA2NxR4OTQyMDAzMjM0CDc6MDox";
	private static final String COMPLETED_TASK_TITLE = "Completed Task";
	private static final Date COMPLETED_TASK_UPDATED = date("2010-02-02T04:05:06.000Z");
	private static final String COMPLETED_TASK_POSITION = "00000000002147483647";
	private static final Date COMPLETED_TASK_DUE = date("2011-01-10T00:00:00.000Z");
	private static final Date COMPLETED_TASK_COMPLETED = date("2011-01-01T01:01:01.000Z");

	private static final String ACTIVE_TASK_ID = "MTY1OTA2NzU4OTQyMQAzMjM0MDc6MDo4GTI5NjMfMTc";
	private static final String ACTIVE_TASK_TITLE = "Active Task";
	private static final String ACTIVE_TASK_NOTES = "Task notes";
	private static final Date ACTIVE_TASK_UPDATED = date("2010-01-01T01:02:03.000Z");
	private static final String ACTIVE_TASK_POSITION = "00000000003221225471";
	private static final Date ACTIVE_TASK_DUE = date("2012-02-02T00:00:00.000Z");
	
	@Test
	public void getTasklistsPage() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("tasklists"), APPLICATION_JSON));
		TaskListsPage page = google.taskOperations().getTaskLists(null);
		assertTaskListsPage(page);
	}

	@Test
	public void getDefaultTaskList() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists/@default"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("tasklist"), APPLICATION_JSON));
		TaskList taskList = google.taskOperations().getTaskList(DEFAULT);
		assertFirstTaskList(taskList);
	}
	
	@Test
	public void getTaskListById() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists/MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("tasklist"), APPLICATION_JSON));
		TaskList taskList = google.taskOperations().getTaskList(
				FIRST_TASK_LIST_ID);
		assertFirstTaskList(taskList);
	}

	@Test
	public void createTaskList() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists"))
				.andExpect(method(POST))
				.andRespond(
						withSuccess(jsonResource("tasklist"), APPLICATION_JSON));
		TaskList taskList = google.taskOperations().saveTaskList(
				new TaskList(FIRST_TASK_LIST_TITLE));
		assertFirstTaskList(taskList);
	}

	@Test
	public void updateTaskList() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists/MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow"))
				.andExpect(method(PUT))
				.andRespond(
						withSuccess(jsonResource("tasklist"), APPLICATION_JSON));
		TaskList taskList = google.taskOperations().saveTaskList(
				new TaskList(FIRST_TASK_LIST_ID, FIRST_TASK_LIST_TITLE));
		assertFirstTaskList(taskList);
	}

	@Test
	public void deleteTaskList() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists/MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow"))
				.andExpect(method(DELETE)).andRespond(withNoContent());
		google.taskOperations().deleteTaskList("MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow");
	}

	@Test
	public void getTasksInDefaultList() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/@default/tasks?maxResults=100"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("tasks"), APPLICATION_JSON));
		TasksPage page = google.taskOperations().getTasks();
		assertTasksPage(page);
	}
	
	@Test
	public void getTasksByListId() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow/tasks"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("tasks"), APPLICATION_JSON));
		TasksPage page = google.taskOperations().getTasks(FIRST_TASK_LIST_ID, null);
		assertTasksPage(page);
	}
	
	@Test
	public void getTaskFromDefaultListById() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/@default/tasks/MTY1OTA2NzU4OTQyMQAzMjM0MDc6MDo4GTI5NjMfMTc"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("task"), APPLICATION_JSON));
		Task task = google.taskOperations().getTask(ACTIVE_TASK_ID);
		assertActiveTask(task);
	}
	
	@Test
	public void getTaskByIds() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow/tasks/MTY1OTA2NzU4OTQyMQAzMjM0MDc6MDo4GTI5NjMfMTc"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("task"), APPLICATION_JSON));
		Task task = google.taskOperations().getTask(FIRST_TASK_LIST_ID, ACTIVE_TASK_ID);
		assertActiveTask(task);
	}
	
	@Test
	public void createTask() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/@default/tasks"))
		.andExpect(method(POST))
		.andRespond(
				withSuccess(jsonResource("task"), APPLICATION_JSON));
		Task task = google.taskOperations().saveTask(new Task(ACTIVE_TASK_TITLE, ACTIVE_TASK_NOTES, ACTIVE_TASK_DUE));
		assertActiveTask(task);
	}
	
	@Test
	public void updateTask() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/@default/tasks/MTY1OTA2NzU4OTQyMQAzMjM0MDc6MDo4GTI5NjMfMTc"))
		.andExpect(method(PUT))
		.andRespond(
				withSuccess(jsonResource("task"), APPLICATION_JSON));
		Task task = google.taskOperations().saveTask(new Task(ACTIVE_TASK_ID, ACTIVE_TASK_TITLE, ACTIVE_TASK_NOTES, ACTIVE_TASK_DUE, null));
		assertActiveTask(task);
	}
	
	@Test
	public void deleteTask() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/tasks/v1/lists/@default/tasks/MTY1OTA2NzU4OTQyMQAzMjM0MDc6MDo4GTI5NjMfMTc"))
		.andExpect(method(DELETE))
		.andRespond(
				withNoContent());
		google.taskOperations().deleteTask(ACTIVE_TASK_ID);
	}
	
	private void assertTaskListsPage(TaskListsPage page) {
		assertNotNull(page);
		assertEquals(2, page.getItems().size());
		assertNull(page.getNextPageToken());
		assertFirstTaskList(page.getItems().get(0));
		assertSecondTaskList(page.getItems().get(1));
	}

	private void assertFirstTaskList(TaskList taskList) {
		assertNotNull(taskList);
		assertEquals(FIRST_TASK_LIST_ID, taskList.getId());
		assertEquals(FIRST_TASK_LIST_TITLE, taskList.getTitle());
	}

	private void assertSecondTaskList(TaskList taskList) {
		assertNotNull(taskList);
		assertEquals(SECOND_TASK_LIST_ID, taskList.getId());
		assertEquals(SECONDS_TASK_LIST_TITLE, taskList.getTitle());
	}
	
	private void assertTasksPage(TasksPage page) {
		assertNotNull(page);
		assertEquals(2, page.getItems().size());
		assertNull(page.getNextPageToken());
		assertCompletedTask(page.getItems().get(0));
		assertActiveTask(page.getItems().get(1));
	}
	
	private void assertCompletedTask(Task task) {
		assertNotNull(task);
		assertEquals(COMPLETED_TASK_ID, task.getId());
		assertEquals(COMPLETED_TASK_TITLE, task.getTitle());
		assertEquals(COMPLETED_TASK_POSITION, task.getPosition());
		assertEquals(TaskStatus.COMPLETED, task.getStatus());
		assertEquals(COMPLETED_TASK_DUE, task.getDue());
		assertEquals(COMPLETED_TASK_UPDATED, task.getUpdated());
		assertEquals(COMPLETED_TASK_COMPLETED, task.getCompleted());
	}
	
	private void assertActiveTask(Task task) {
		assertNotNull(task);
		assertEquals(ACTIVE_TASK_ID, task.getId());
		assertEquals(ACTIVE_TASK_TITLE, task.getTitle());
		assertEquals(ACTIVE_TASK_NOTES, task.getNotes());
		assertEquals(ACTIVE_TASK_POSITION, task.getPosition());
		assertEquals(TaskStatus.NEEDS_ACTION, task.getStatus());
		assertEquals(ACTIVE_TASK_DUE, task.getDue());
		assertEquals(ACTIVE_TASK_UPDATED, task.getUpdated());
		assertNull(task.getCompleted());
	}
}
