package org.springframework.social.google.api.gdata.contact;

public class Phone {

	private final String rel;
	private final String label;
	private final String number;
	private final boolean primary;
	
	public Phone(String rel, String label, String number, boolean primary) {
		this.rel = rel;
		this.label = label;
		this.number = number;
		this.primary = primary;
	}
	
	@Override
	public String toString() {
		return number;
	}
	
	public String getRel() {
		return rel;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getNumber() {
		return number;
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
}
