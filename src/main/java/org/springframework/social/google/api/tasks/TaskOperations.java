/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.tasks;

/**
 * Defines operations for working with tasks and task lists of the authenticated
 * user. Requires OAuth2 authentication with either
 * "https://www.googleapis.com/auth/tasks" for read and write operations or
 * "https://www.googleapis.com/auth/tasks.readonly" for read-only operations.
 *
 * @author Gabriel Axel
 */
public interface TaskOperations {

  /**
   * Retrieves up to 100 task lists.
   *
   * @return {@link TaskListsPage} with up to 100 items
   */
  TaskListsPage getTaskLists();

  /**
   * Retrieves task lists page
   *
   * @param pageToken
   *            page token or null
   * @return {@link TaskList}s page
   */
  TaskListsPage getTaskLists(String pageToken);

  /**
   * Retrieves a task list by its ID.
   *
   * @param id
   *            the task list ID or "@default"
   * @return the retrieved {@link TaskList}
   */
  TaskList getTaskList(String id);

  /**
   * Creates or updates a task list.
   *
   * @param taskList
   *            the task list to create or update
   * @return the saved {@link TaskList}
   */
  TaskList saveTaskList(TaskList taskList);

  /**
   * Deletes a task list.
   *
   * @param taskListId
   *            the ID of the task list to delete
   */
  void deleteTaskList(String taskListId);

  /**
   * Deletes a task list.
   *
   * @param taskList
   *            the task list to delete
   */
  void deleteTaskList(TaskList taskList);

  /**
   * Creates a {@link TaskListQueryBuilder}.
   *
   * @return a new {@link TaskListQueryBuilder}
   */
  TaskListQueryBuilder taskListQuery();

  /**
   * Retrieves up to 100 active tasks from the default task list.
   *
   * @return {@link TasksPage} with up to 100 items
   */
  TasksPage getTasks();

  /**
   * Retrieved tasks from a task list
   *
   * @param taskListId
   *            ID of the task list
   * @param pageToken
   *            page token or null
   * @return {@link Task}s page
   */
  TasksPage getTasks(String taskListId, String pageToken);

  /**
   * Retrieves a task from the default task list by its ID.
   *
   * @param id
   *            the task ID
   * @return the retrieved {@link Task}
   */
  Task getTask(String id);

  /**
   * Retrieves a task by its containing task list ID and by the task ID.
   *
   * @param taskListId
   *            the containing task list ID
   * @param id
   *            the task ID
   * @return the retrieved {@link Task}
   */
  Task getTask(String taskListId, String id);

  /**
   * Creates or updates a task in the default task list.
   *
   * @param task
   *            the task to create or update
   * @return the saved {@link Task}
   */
  Task saveTask(Task task);

  /**
   * Creates or updates a task in a specified task list.
   *
   * @param taskListId
   *            the containing task list ID
   * @param task
   *            the task to create or update
   * @return the saved {@link Task}
   */
  Task saveTask(String taskListId, Task task);

  /**
   * Deletes a task from the default task list. Deleted tasks can be retrieved
   * using {@link TaskQueryBuilder#includeDeleted(boolean)}.
   *
   * @param taskId
   *            the ID of the task to delete
   */
  void deleteTask(String taskId);

  /**
   * Deletes a task from the default task list. Deleted tasks can be retrieved
   * using {@link TaskQueryBuilder#includeDeleted(boolean)}.
   *
   * @param task
   *            the task to delete
   */
  void deleteTask(Task task);

  /**
   * Deletes a task from a task list. Deleted tasks can be retrieved using
   * {@link TaskQueryBuilder#includeDeleted(boolean)}.
   *
   * @param taskListId
   *            the containing task list ID
   * @param taskId
   *            the ID of the task to delete
   */
  void deleteTask(String taskListId, String taskId);

  /**
   * Deletes a task from a task list. Deleted tasks can be retrieved using
   * {@link TaskQueryBuilder#includeDeleted(boolean)}.
   *
   * @param taskListId
   *            the containing task list ID
   * @param task
   *            the task to delete
   */
  void deleteTask(String taskListId, Task task);

  /**
   * Creates a {@link TaskQueryBuilder}.
   *
   * @return a new {@link TaskQueryBuilder}
   */
  TaskQueryBuilder taskQuery();

  /**
   * Creates a task at a specified position in a task list.
   *
   * @param taskListId
   *            the containing task list
   * @param parent
   *            task ID to become parent of the new task
   * @param previous
   *            the task ID after which the new task will be appended
   * @param task
   *            the task to create
   * @return the created {@link Task}
   */
  Task createTaskAt(String taskListId, String parent, String previous,
                    Task task);

  /**
   * Moves a task to a new position in the task list.
   *
   * @param taskListId
   *            the containing task list ID
   * @param task
   *            the task to move
   * @param parent
   *            the task ID to become parent of the moved task
   * @param previous
   *            the task ID after which the moved task will be appended
   * @return the moved {@link Task}
   * @throws IllegalStateException
   *             if both 'parent' and 'previous' arguments are empty
   */
  Task moveTask(String taskListId, Task task, String parent, String previous);

  /**
   * Hides completed tasks from a task list. Hidden tasks can be retrieved
   * using {@link TaskQueryBuilder#includeHidden(boolean)}.
   *
   * @param taskList
   *            the task list to clear
   */
  void clearCompletedTasks(TaskList taskList);
}
