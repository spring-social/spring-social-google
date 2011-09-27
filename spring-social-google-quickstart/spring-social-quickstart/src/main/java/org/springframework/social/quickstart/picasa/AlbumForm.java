package org.springframework.social.quickstart.picasa;

public class AlbumForm {

	private String id;
	private String title;
	private String summary;
	
	public AlbumForm() {}

	public AlbumForm(String id, String title, String summary) {
		this.id = id;
		this.title = title;
		this.summary = summary;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
