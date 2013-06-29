package org.springframework.social.google.api.plus.moments;

import java.util.Date;
import java.util.List;

/**
 * A moment's target. Construct with a URL of a resource containing metadata as
 * required for the specific activity type. When fetching, the fields will
 * contain the metadata values from that resource.
 * 
 * @author Gabriel Axel
 * 
 */
public class MomentTarget {

	private String url;

	private String id;

	private String name;

	private String description;

	private String image;

	private List<Author> author;

	private String text;

	private Date datePublished;

	private String duration;

	private Album inAlbum;

	private Audio audio;

	private Artist byArtist;

	protected MomentTarget() {
	}

	public MomentTarget(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public List<Author> getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public String getDuration() {
		return duration;
	}

	public Album getInAlbum() {
		return inAlbum;
	}

	public Audio getAudio() {
		return audio;
	}

	public Artist getByArtist() {
		return byArtist;
	}

}