package org.springframework.social.quickstart.picasa;

import org.springframework.social.quickstart.SearchForm;

public class PhotoSearchForm extends SearchForm {

	private String access;
	private Float west;
	private Float south;
	private Float east;
	private Float north;
	private Integer maxSize;
	private String location;
	private Integer thumbSize;
	
	public boolean hasCoordinates() {
		return west != null && south != null && east != null && north != null;
	}
	
	public String getAccess() {
		return access;
	}
	
	public void setAccess(String access) {
		this.access = access;
	}
	
	public Float getWest() {
		return west;
	}
	
	public void setWest(Float west) {
		this.west = west;
	}
	
	public Float getSouth() {
		return south;
	}
	
	public void setSouth(Float south) {
		this.south = south;
	}
	
	public Float getEast() {
		return east;
	}
	
	public void setEast(Float east) {
		this.east = east;
	}
	
	public Float getNorth() {
		return north;
	}
	
	public void setNorth(Float north) {
		this.north = north;
	}
	
	public Integer getMaxSize() {
		return maxSize;
	}
	
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getThumbSize() {
		return thumbSize;
	}
	
	public void setThumbSize(Integer thumbSize) {
		this.thumbSize = thumbSize;
	}
	
}
