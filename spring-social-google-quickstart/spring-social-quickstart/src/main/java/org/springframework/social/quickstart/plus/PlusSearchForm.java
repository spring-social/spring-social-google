package org.springframework.social.quickstart.plus;

public class PlusSearchForm {

	private String text;
	private String pageToken;
	private int maxResults;
	private String order;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getPageToken() {
		return pageToken;
	}
	
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	
	public int getMaxResults() {
		return maxResults;
	}
	
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
