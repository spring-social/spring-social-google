package org.springframework.social.quickstart.contact;

import org.hibernate.validator.constraints.NotBlank;

public class EmailForm {

	@NotBlank
	private String rel;
	
	private String label;
	
	@NotBlank
	private String address;
	
	private boolean primary;
	
	public EmailForm() {}

	public EmailForm(String rel, String label, String address, boolean primary) {
		this.rel = rel;
		this.label = label;
		this.address = address;
		this.primary = primary;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
}
