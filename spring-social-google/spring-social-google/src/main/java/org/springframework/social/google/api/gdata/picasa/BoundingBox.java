package org.springframework.social.google.api.gdata.picasa;

public class BoundingBox {

	private final float west;
	private final float south;
	private final float east;
	private final float north;
	
	public BoundingBox(float west, float south, float east, float north) {
		this.west = west;
		this.south = south;
		this.east = east;
		this.north = north;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(west).append(',')
			.append(south).append(',')
			.append(east).append(',')
			.append(north).toString();
	}
	
	public float getWest() {
		return west;
	}
	
	public float getSouth() {
		return south;
	}
	
	public float getEast() {
		return east;
	}
	
	public float getNorth() {
		return north;
	}
	
}
