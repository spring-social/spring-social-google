package org.springframework.social.quickstart.drive;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum OptionalBoolean {
	
	ALL("", null),
	YES("Yes", TRUE),
	NO("No", FALSE);
	
	private final String text;
	private final Boolean value;
	
	OptionalBoolean(String text, Boolean value) {
		this.text = text;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public Boolean getValue() {
		return value;
	}
}
