package org.springframework.social.quickstart.contact;

public class PhoneForm {

	private String rel;
	private String label;
	private String number;
	private boolean primary;
	
	public PhoneForm() {}

	public PhoneForm(String rel, String label, String number, boolean primary) {
		this.rel = rel;
		this.label = label;
		this.number = number;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
}
