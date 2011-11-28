package org.springframework.social.quickstart.tasks;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class TaskForm {

	private String list;
	private String id;
	
	@NotBlank(message="Task list name may not be empty")
	private String title;
	
	private String notes;
	
	@DateTimeFormat(iso=DATE)
	private Date due;
	
	@DateTimeFormat(iso=DATE)
	private Date completed;
	
	public TaskForm() {
		
	}
	
	public TaskForm(String id, String title, Date due, String notes, Date completed) {
		this.id = id;
		this.title = title;
		this.due = due;
		this.notes = notes;
		this.completed = completed;
	}
	
	public String getList() {
		return hasText(list) ? list : "@default";
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getId() {
		return hasText(id) ? id : null;
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
	
	public String getNotes() {
		return hasText(notes) ? notes : null;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}
	
}
