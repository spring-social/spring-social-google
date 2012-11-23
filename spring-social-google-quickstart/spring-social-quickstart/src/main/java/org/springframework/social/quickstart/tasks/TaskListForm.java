package org.springframework.social.quickstart.tasks;

import org.hibernate.validator.constraints.NotBlank;

public class TaskListForm {

	private String id;
	
	@NotBlank(message="Task name may not be empty")
	private String title;
	
	public TaskListForm() {
		
	}
	
	public TaskListForm(String id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}
