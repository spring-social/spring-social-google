package org.springframework.social.quickstart;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchForm {

	private String text;
	private int startIndex;
	private int maxResults;
	
	@DateTimeFormat(iso=DATE)
	private Date updatedMin;
	
	@DateTimeFormat(iso=DATE)
	private Date updatedMax;
	
	@DateTimeFormat(iso=DATE)
	private Date publishedMin;
	
	@DateTimeFormat(iso=DATE)
	private Date publishedMax;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public int getMaxResults() {
		return maxResults;
	}
	
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	
	public Date getUpdatedMin() {
		return updatedMin;
	}
	
	public void setUpdatedMin(Date updatedMin) {
		this.updatedMin = updatedMin;
	}
	
	public Date getUpdatedMax() {
		return updatedMax;
	}
	
	public void setUpdatedMax(Date updatedMax) {
		this.updatedMax = updatedMax;
	}

	public Date getPublishedMin() {
		return publishedMin;
	}

	public void setPublishedMin(Date publishedMin) {
		this.publishedMin = publishedMin;
	}

	public Date getPublishedMax() {
		return publishedMax;
	}

	public void setPublishedMax(Date publishedMax) {
		this.publishedMax = publishedMax;
	}
	
}
