package org.springframework.social.google.api.contact;

import java.util.List;

public class Contact {

	private final String id;
	private final String self;
	private final String namePrefix;
	private final String firstName;
	private final String middleName;
	private final String lastName;
	private final String nameSuffix;
	private final String pictureUrl;
	private final List<String> groupIds;
	private final List<Email> emails;	
	private final List<Phone> phones;
	
	public Contact(String id, String self, String namePrefix, String firstName,
			String middleName, String lastName, String nameSuffix,
			String pictureUrl, List<String> groupIds, List<Email> emails, List<Phone> phones) {
		this.id = id;
		this.self = self;
		this.namePrefix = namePrefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.nameSuffix = nameSuffix;
		this.pictureUrl = pictureUrl;
		this.groupIds = groupIds;
		this.emails = emails;
		this.phones = phones;
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		for(String token : new String[] {namePrefix, firstName, middleName, lastName, nameSuffix}) {
			if(token != null) {
				if(sb.length() > 0) {
					sb.append(' ');
				}
				sb.append(token);
			}
		}
		return sb.toString();
	}
	
	public String getPrimaryEmailAddress() {
		for(Email email : emails) {
			if(email.isPrimary()) {
				return email.getAddress();
			}
		}
		return null;
	}
	
	public String getPrimaryPhoneNumber() {
		for(Phone phone : phones) {
			if(phone.isPrimary()) {
				return phone.getNumber();
			}
		}
		return null;
	}
	
	public String getId() {
		return id;
	}
	
	public String getSelf() {
		return self;
	}
	
	public String getNamePrefix() {
		return namePrefix;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getNameSuffix() {
		return nameSuffix;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	
	public List<String> getGroupIds() {
		return groupIds;
	}

	public List<Email> getEmails() {
		return emails;
	}
	
	public List<Phone> getPhones() {
		return phones;
	}
	
}
