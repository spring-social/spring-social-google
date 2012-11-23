package org.springframework.social.quickstart.drive;

public enum DateOperators {
	
	BEFORE("Before"),
	IS_OR_BEFORE("Is or before"),
	IS("Is"),
	IS_OR_AFTER("Is or after"),
	AFTER("After");
	
	private final String text;
	
	DateOperators(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
