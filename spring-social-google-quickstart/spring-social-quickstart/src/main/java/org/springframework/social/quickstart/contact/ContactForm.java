package org.springframework.social.quickstart.contact;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.functors.InstantiateFactory;
import org.apache.commons.collections.list.LazyList;

public class ContactForm {

	private String id;
	private String url;
	private String namePrefix;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private String pictureUrl;
	private List<String> groupIds;
	
	@SuppressWarnings("unchecked")
	private List<EmailForm> emails = 
		LazyList.decorate(new ArrayList<EmailForm>(), new InstantiateFactory(EmailForm.class));
	
	@SuppressWarnings("unchecked")
	private List<PhoneForm> phones =
		LazyList.decorate(new ArrayList<PhoneForm>(), new InstantiateFactory(PhoneForm.class));
	
	public ContactForm() {
	}
	
	public ContactForm(String id, String url, String namePrefix,
			String firstName, String middleName, String lastName,
			String nameSuffix, String pictureUrl, List<String> groupIds) {
		this.id = id;
		this.url = url;
		this.namePrefix = namePrefix;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.nameSuffix = nameSuffix;
		this.pictureUrl = pictureUrl;
		this.groupIds = groupIds;
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
	
	public List<String> getGroupIds() {
		return groupIds == null ? new ArrayList<String>() : groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public List<EmailForm> getEmails() {
		return emails;
	}
	
	public void setEmails(List<EmailForm> emails) {
		this.emails = emails;
	}

	public List<PhoneForm> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneForm> phones) {
		this.phones = phones;
	}
	
}
