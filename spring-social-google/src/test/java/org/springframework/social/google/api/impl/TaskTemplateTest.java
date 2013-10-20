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
package org.springframework.social.google.api.impl;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import org.junit.Test;
import org.springframework.social.google.api.tasks.TaskList;
import org.springframework.social.google.api.tasks.TaskListsPage;

public class TaskTemplateTest extends AbstractGoogleApiTest {

	private static final String FIRST_TASK_LIST_ID = "MTY1OTA3NzU4OTQyMFAzMjM0MDc6MDow";
	private static final String FIRST_TASK_LIST_TITLE = "First Task List";

	private static final String SECOND_TASK_LIST_ID = "MTY1OTA2NzU4OSQyMDAzMjM0MDc6NjI7MDQ0NjgwOjA";
	private static final String SECONDS_TASK_LIST_TITLE = "Seconds Task List";

	@Test
	public void getTasklistsPage() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/tasks/v1/users/@me/lists?"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("tasklists"), APPLICATION_JSON));
		TaskListsPage page = google.taskOperations().getTaskLists(null);
		assertNotNull(page);
		assertNull(page.getNextPageToken());
		assertEquals(2, page.getItems().size());
		assertFirstTaskList(page.getItems().get(0));
		assertSecondTaskList(page.getItems().get(1));
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
				.andExpect(method(DELETE)).andRespond(withSuccess());
	}

	@Test
	public void getDefaultTasks() {
		
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
}
