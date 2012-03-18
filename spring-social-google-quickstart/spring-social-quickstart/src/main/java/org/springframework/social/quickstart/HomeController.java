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
package org.springframework.social.quickstart;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.social.google.api.plus.activity.ActivitiesPage;
import org.springframework.social.google.api.plus.activity.Activity;
import org.springframework.social.google.api.plus.comment.Comment;
import org.springframework.social.google.api.plus.comment.CommentsPage;
import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.google.api.tasks.Task;
import org.springframework.social.google.api.tasks.TaskList;
import org.springframework.social.google.api.tasks.TaskListsPage;
import org.springframework.social.google.api.tasks.TasksPage;
import org.springframework.social.quickstart.tasks.TaskForm;
import org.springframework.social.quickstart.tasks.TaskListForm;
import org.springframework.social.quickstart.tasks.TaskSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final Google google;
	
	@Autowired
	public HomeController(Google google) {
		this.google = google;
	}
	
	@ExceptionHandler(ExpiredAuthorizationException.class)
	public String handleExpiredToken() {
		return "redirect:/signout";
	}

	@RequestMapping(value="/", method=GET)
	public ModelAndView home() {
		
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		
		return new ModelAndView("profile", "profile", profile);
	}
	
	@RequestMapping(value="person", method=GET)
	public ModelAndView person(String id, String contact) {
		if(hasText(id)) {
			Person person = google.personOperations().getPerson(id);
			return new ModelAndView("person")
				.addObject("command", new SearchForm())
				.addObject("person", person);
		} else if(hasText(contact)) {
			Person person = google.personOperations().getContact(contact);
			return new ModelAndView("person", "person", person);
		}
		return new ModelAndView("redirect:/people");
	}
	
	@RequestMapping(value="people", method=GET, params={"!plusoners","!resharers"})
	public ModelAndView people(String text, String group, String pageToken) {
		
		PeoplePage people;
		if(hasText(text)) {
			people = google.personOperations().personQuery()
				.searchFor(text)
				.fromPage(pageToken)
				.getPage();
		} else if(hasText(group)) {
			people = google.personOperations().contactQuery()
				.fromGroup(group)
				.fromPage(pageToken)
				.getPage();
		} else {
			people = new PeoplePage();
		}
		
		return new ModelAndView("people", "people", people);
	}
	
	@RequestMapping(value="people", method=GET, params="plusoners")
	public ModelAndView plusOners(String plusoners, String pageToken) {
		
		PeoplePage people = google.personOperations().getActivityPlusOners(plusoners, pageToken);
		return new ModelAndView("people", "people", people);
	}
	
	@RequestMapping(value="people", method=GET, params="resharers")
	public ModelAndView resharers(String resharers, String pageToken) {
		
		PeoplePage people = google.personOperations().getActivityPlusOners(resharers, pageToken);
		return new ModelAndView("people", "people", people);
	}
	
	@RequestMapping(value="activity", method=GET)
	public ModelAndView activity(String id) {
		
		Activity activity = google.activityOperations().getActivity(id);
		return new ModelAndView("activity", "activity", activity);
	}
	
	@RequestMapping(value="activities", method=GET, params="!text")
	public ModelAndView listActivities(@RequestParam(defaultValue="me") String person, String pageToken) {
		
		ActivitiesPage activities = google.activityOperations().getActivitiesPage(person, pageToken);
		
		return new ModelAndView("activities", "activities", activities);
	}
	
	@RequestMapping(value="activities", method=GET, params="text")
	public ModelAndView searchActivities(String text, String pageToken) {
		
		ActivitiesPage activities = google.activityOperations().activityQuery()
			.searchFor(text)
			.fromPage(pageToken)
			.getPage();
		
		return new ModelAndView("activities", "activities", activities);
	}
	
	@RequestMapping(value="comments", method=GET)
	public ModelAndView comments(String activity, String pageToken) {
		
		CommentsPage comments = google.commentOperations().getComments(activity, pageToken);
		return new ModelAndView("comments", "comments", comments);
	}
	
	@RequestMapping(value="comment", method=GET)
	public ModelAndView comment(String id) {
		
		Comment comment = google.commentOperations().getComment(id);
		return new ModelAndView("comment", "comment", comment);
	}
	
	@RequestMapping(value="tasklists", method=GET)
	public ModelAndView taskLists(String pageToken) {
		
		TaskListsPage taskLists = google.taskOperations().taskListQuery().fromPage(pageToken).getPage();
		
		return new ModelAndView("tasklists", "taskLists", taskLists);
	}
	
	@RequestMapping(value="tasklist", method=GET)
	public ModelAndView tasklist() {
		return new ModelAndView("tasklist", "command", new TaskListForm());
	}
	
	@RequestMapping(value="tasklist", method=GET, params="id")
	public ModelAndView taskList(String id) {

		TaskList taskList = google.taskOperations().getTaskList(id);
		TaskListForm command = new TaskListForm(taskList.getId(), taskList.getTitle());
		return new ModelAndView("tasklist", "command", command);
	}
	
	@RequestMapping(value="tasklist", method=POST)
	public ModelAndView saveTaskList(@Valid TaskListForm command, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("tasklist", "command", command);
		}
		
		TaskList taskList = new TaskList(command.getId(), command.getTitle());
		google.taskOperations().saveTaskList(taskList);
		return new ModelAndView("redirect:/tasklists");
	}
	
	@RequestMapping(value="tasklist", method=POST, params="delete")
	public String deleteTaskList(TaskListForm command) {
		
		TaskList taskList = new TaskList(command.getId(), command.getTitle());
		google.taskOperations().deleteTaskList(taskList);
		return "redirect:/tasklists";
	}
	
	@RequestMapping(value="tasks", method=GET)
	public ModelAndView tasks(TaskSearchForm command) {
		
		TasksPage tasks = google.taskOperations().taskQuery()
				.fromTaskList(command.getList())
				.fromPage(command.getPageToken())
				.completedFrom(command.getCompletedMin())
				.completedUntil(command.getCompletedMax())
				.dueFrom(command.getDueMin())
				.dueUntil(command.getDueMax())
				.updatedFrom(command.getUpdatedMin())
				.includeCompleted(command.isIncludeCompleted())
				.includeDeleted(command.isIncludeDeleted())
				.includeHidden(command.isIncludeHidden())
				.getPage();
		
		return new ModelAndView("tasks")
			.addObject("command", command)
			.addObject("tasks", tasks);
	}
	
	@RequestMapping(value="task", method=GET)
	public ModelAndView task() {
		
		return new ModelAndView("task", "command", new TaskForm());
	}
	
	@RequestMapping(value="task", method=GET, params="id")
	public ModelAndView task(String list, String id) {
		
		if(!hasText(list)) {
			list = "@default";
		}
		
		Task task = google.taskOperations().getTask(list, id);
		TaskForm command = new TaskForm(task.getId(), task.getTitle(), task.getDue(), task.getNotes(), task.getCompleted());
		return new ModelAndView("task", "command", command);
	}
	
	@RequestMapping(value="task", method=POST)
	public ModelAndView saveTask(TaskForm command, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("task", "command", command);
		}

		Task task = new Task(command.getId(), command.getTitle(), command.getNotes(), command.getDue(), command.getCompleted());
		google.taskOperations().saveTask(command.getList(), task);
		
		return new ModelAndView("redirect:/tasks", "list", command.getList());
	}
	
	@RequestMapping(value="task", method=POST, params="parent")
	public ModelAndView createTask(String parent, String previous, TaskForm command, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("task", "command", command);
		}

		Task task = new Task(command.getId(), command.getTitle(), command.getNotes(), command.getDue(), command.getCompleted());
		google.taskOperations().createTaskAt(command.getList(), parent, previous, task);
		
		return new ModelAndView("redirect:/tasks", "list", command.getList());
	}
	
	@RequestMapping(value="movetask", method=POST)
	public ModelAndView moveTask(String list, String move, String parent, String previous) {
		
		google.taskOperations().moveTask(list, new Task(move), parent, previous);
		return new ModelAndView("redirect:/tasks", "list", list);
	}
	
	@RequestMapping(value="task", method=POST, params="delete")
	public ModelAndView deleteTask(@Valid TaskForm command) {
		
		google.taskOperations().deleteTask(command.getList(), new Task(command.getId()));
		return new ModelAndView("redirect:/tasks", "list", command.getList());
	}
	
	@RequestMapping(value="cleartasks", method=POST)
	public ModelAndView clearTasks(String list) {
		
		if(!hasText(list)) {
			list = "@default";
		}
		google.taskOperations().clearCompletedTasks(new TaskList(list, null));
		return new ModelAndView("redirect:/tasks", "list", list);
	}
}