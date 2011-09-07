package org.springframework.social.quickstart.contact;


public class ContactForm {

	private String id;
	private String url;
	private String namePrefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private String pictureUrl;
	
	public ContactForm() {
	}
	
	public ContactForm(String id, String url, String namePrefix,
			String firstName, String middleName, String lastName,
			String nameSuffix, String pictureUrl) {
		this.id = id;
		this.url = url;
		this.namePrefix = namePrefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.nameSuffix = nameSuffix;
		this.pictureUrl = pictureUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
}
