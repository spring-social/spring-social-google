package org.springframework.social.quickstart.picasa;

public class AlbumForm {

	private String id;
	private String title;
	private String summary;
	private String visibility;
	
	public AlbumForm() {}

	public AlbumForm(String id, String title, String summary, String visibility) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.visibility = visibility;
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

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
}
