package org.springframework.social.quickstart.tasks;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.util.StringUtils.hasText;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskSearchForm {

	private String list;
	private String pageToken;
	
	@DateTimeFormat(iso=DATE)
	private Date completedMax;
	
	@DateTimeFormat(iso=DATE)
	private Date completedMin;
	
	@DateTimeFormat(iso=DATE)
	private Date dueMax;
	
	@DateTimeFormat(iso=DATE)
	private Date dueMin;
	
	@DateTimeFormat(iso=DATE)
	private Date updatedMin;
	
	private boolean includeCompleted;
	private boolean includeDeleted;
	private boolean includeHidden;
	
	public String getList() {
		return hasText(list) ? list : "@default";
	}
	
	public void setList(String list) {
		this.list = list;
	}
	
	public String getPageToken() {
		return pageToken;
	}
	
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	
	public Date getCompletedMax() {
		return completedMax;
	}
	
	public void setCompletedMax(Date completedMax) {
		this.completedMax = completedMax;
	}
	
	public Date getCompletedMin() {
		return completedMin;
	}
	
	public void setCompletedMin(Date completedMin) {
		this.completedMin = completedMin;
	}
	
	public Date getDueMax() {
		return dueMax;
	}
	
	public void setDueMax(Date dueMax) {
		this.dueMax = dueMax;
	}
	
	public Date getDueMin() {
		return dueMin;
	}
	
	public void setDueMin(Date dueMin) {
		this.dueMin = dueMin;
	}
	
	public Date getUpdatedMin() {
		return updatedMin;
	}
	
	public void setUpdatedMin(Date updatedMin) {
		this.updatedMin = updatedMin;
	}

	public boolean isIncludeCompleted() {
		return includeCompleted;
	}

	public void setIncludeCompleted(boolean includeCompleted) {
		this.includeCompleted = includeCompleted;
	}

	public boolean isIncludeDeleted() {
		return includeDeleted;
	}

	public void setIncludeDeleted(boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
	}

	public boolean isIncludeHidden() {
		return includeHidden;
	}

	public void setIncludeHidden(boolean includeHidden) {
		this.includeHidden = includeHidden;
	}
	
}