package org.springframework.social.google.api.gdata.contact;

public class Email {

	private final String rel;
	private final String label;
	private final String address;
	private final boolean primary;
	
	public Email(String rel, String label, String address, boolean primary) {
		this.rel = rel;
		this.label = label;
		this.address = address;
		this.primary = primary;
	}
	
	@Override
	public String toString() {
		return address;
	}
	
	public String getRel() {
		return rel;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getAddress() {
		return address;
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
}
