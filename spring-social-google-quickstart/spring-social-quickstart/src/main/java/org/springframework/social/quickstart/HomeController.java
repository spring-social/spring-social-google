/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.gdata.contact.Contact;
import org.springframework.social.google.api.gdata.contact.ContactGroup;
import org.springframework.social.google.api.gdata.contact.Email;
import org.springframework.social.google.api.gdata.contact.Phone;
import org.springframework.social.google.api.gdata.picasa.Album;
import org.springframework.social.google.api.gdata.query.GDataPage;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.social.google.api.plus.activity.ActivitiesPage;
import org.springframework.social.google.api.plus.person.BasePerson;
import org.springframework.social.google.api.plus.person.PeoplePage;
import org.springframework.social.google.api.plus.person.Person;
import org.springframework.social.quickstart.contact.ContactForm;
import org.springframework.social.quickstart.contact.ContactGroupForm;
import org.springframework.social.quickstart.contact.ContactSearchForm;
import org.springframework.social.quickstart.contact.EmailForm;
import org.springframework.social.quickstart.contact.PhoneForm;
import org.springframework.social.quickstart.picasa.AlbumForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final Google google;
	
	@Autowired
	public HomeController(Google google) {
		this.google = google;
	}
	
	@ExceptionHandler(ExpiredAuthorizationException.class)
	public String handleExpiredToken() {
		return "redirect:/signout";
	}

	@RequestMapping(value="/", method=GET)
	public ModelAndView home() {
		
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		
		return new ModelAndView("profile", "profile", profile);
	}
	
	@RequestMapping(value="/contacts", method=GET)
	public ModelAndView contacts(ContactSearchForm command) {
		
		List<ContactGroup> groups = google.contactOperations().getContactGroupList();
		
		GDataPage<Contact> contacts = google.contactOperations().contactQuery()
			.searchFor(command.getText())
			.startingFromIndex(command.getStartIndex())
			.maxResultsNumber(command.getMaxResults())
			.updatedFrom(command.getUpdatedMin())
			.updatedUntil(command.getUpdatedMax())
			.onGroup(hasText(command.getGroupId()) ?new ContactGroup(command.getGroupId(), null, null, null) : null)
			.getPage();
		
		return new ModelAndView("contacts")
			.addObject("groups", groups)
			.addObject("contacts", contacts)
			.addObject("command", command);
	}
	
	@RequestMapping(value="/groups", method=GET)
	public ModelAndView groups(SearchForm command) {
		
		GDataPage<ContactGroup> groups = google.contactOperations().contactGroupQuery()
			.startingFromIndex(command.getStartIndex())
			.maxResultsNumber(command.getMaxResults())
			.updatedFrom(command.getUpdatedMin())
			.updatedUntil(command.getUpdatedMax())
			.getPage();
		
		return new ModelAndView("groups")
			.addObject("groups", groups)
			.addObject("command", command);
	}
	
	@RequestMapping(value="/group", method=GET)
	public ModelAndView addContactGroup() {
		return new ModelAndView("group", "command", new ContactGroupForm());
	}
	
	@RequestMapping(value="/group", method=GET, params="url")
	public ModelAndView editContactGroup(@RequestParam(required=false) String url) {
		ContactGroup group = google.contactOperations().getContactGroup(url);
		ContactGroupForm command = new ContactGroupForm(group.getId(), group.getName(), group.getSelf());
		return new ModelAndView("group", "command", command);
	}
		
	@RequestMapping(value="/group", method=POST)
	public ModelAndView saveContactGroup(@Valid ContactGroupForm command, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("group", "command", command);
		}
		
		ContactGroup group = new ContactGroup(command.getId(), command.getName(), command.getUrl(), null);
		google.contactOperations().saveContactGroup(group);

		return new ModelAndView("redirect:/groups");
	}
	
	@RequestMapping(value="/group", method=POST, params="delete")
	public String deleteContactGroup(@RequestParam String url) {
		google.contactOperations().deleteContactGroup(url);
		return "redirect:/groups";
	}
	
	@RequestMapping(value="/contact", method=POST, params="delete")
	public String deleteContact(String url) {
		google.contactOperations().deleteContact(url);
		return "redirect:/contacts";
	}
	
	@RequestMapping(value="/contact", method=GET)
	public ModelAndView addContact() {
		
		List<ContactGroup> allGroups = google.contactOperations().getContactGroupList();
		
		return new ModelAndView("contact", "command", new ContactForm())
			.addObject("allGroups", allGroups);
	}
	
	@RequestMapping(value="/contact", method=GET, params="url")
	public ModelAndView editContact(@RequestParam String url) {
		
		Contact contact = google.contactOperations().getContact(url);
		ContactForm command = new ContactForm(
			contact.getId(), contact.getSelf(), contact.getNamePrefix(),
			contact.getFirstName(), contact.getMiddleName(), contact.getLastName(), 
			contact.getNameSuffix(), contact.getPictureUrl(), contact.getGroupIds());
		
		for(Email email : contact.getEmails()) {
			command.getEmails().add(new EmailForm(email.getRel(), email.getLabel(), email.getAddress(), email.isPrimary()));
		}
		
		for(Phone phone : contact.getPhones()) {
			command.getPhones().add(new PhoneForm(phone.getRel(), phone.getLabel(), phone.getNumber(), phone.isPrimary()));
		}
		
		List<ContactGroup> allGroups = google.contactOperations().getContactGroupList();
		
		return new ModelAndView("contact", "command", command)
			.addObject("allGroups", allGroups);
	}
	
	@RequestMapping(value="/contact", method=POST)
	public ModelAndView saveContact(@Valid ContactForm command, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("contact", "command", command);
		}
		
		List<Email> emails = new ArrayList<Email>();
		for(EmailForm e : command.getEmails()) {
			if(hasText(e.getAddress()) && hasText(e.getRel()) || hasText(e.getLabel())) {
				emails.add(new Email(e.getRel(), e.getLabel(), e.getAddress(), e.isPrimary()));
			}
		}
		
		List<Phone> phones = new ArrayList<Phone>();
		for(PhoneForm p : command.getPhones()) {
			if(hasText(p.getNumber()) && hasText(p.getRel()) || hasText(p.getLabel())) {
				phones.add(new Phone(p.getRel(), p.getLabel(), p.getNumber(), p.isPrimary()));
			}
		}
		
		Contact contact = new Contact(command.getId(), command.getUrl(), null, command.getNamePrefix(), 
				command.getFirstName(), command.getMiddleName(), command.getLastName(), 
				command.getNameSuffix(), command.getPictureUrl(), command.getGroupIds(), emails, phones);
		google.contactOperations().saveContact(contact);
		return new ModelAndView("redirect:/contacts");
	}

	@RequestMapping(value="/contactpicture", method=GET)
	public ResponseEntity<byte[]> getProfilePicture(@RequestParam String url) {
		byte[] body = google.contactOperations().getProfilePicture(url);
		if(body != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(IMAGE_JPEG);
			headers.setCacheControl("no-cache");
			return new ResponseEntity<byte[]>(body, headers, OK);
		}
		return new ResponseEntity<byte[]>(NOT_FOUND);
	}
	
	@RequestMapping(value="/contactpicture", method=POST)
	public String uploadProfilePicture(
			@RequestHeader String referer, @RequestParam String pictureUrl, 
			@RequestParam MultipartFile file) throws IOException {
		google.contactOperations().uploadProfilePicture(pictureUrl, file.getBytes());
		return "redirect:" + referer;
	}
	
	@RequestMapping(value="/person", method=GET)
	public ModelAndView person(@RequestParam(required=false) String id) {
		if(hasText(id)) {
			Person person = google.personOperations().getPerson(id);
			return new ModelAndView("person")
				.addObject("command", new SearchForm())
				.addObject("person", person);
		}
		return new ModelAndView("redirect:/people");
	}
	
	@RequestMapping(value="/people", method=GET)
	public ModelAndView people(String text, String pageToken) {
		
		PeoplePage people;
		if(hasText(text)) {
			people = google.personOperations().personQuery()
				.searchFor(text)
				.fromPage(pageToken)
				.getPage();
		} else {
			people = new PeoplePage(new ArrayList<BasePerson>(), null);
		}
		
		return new ModelAndView("people", "people", people);
	}
	
	@RequestMapping(value="activities", method=GET, params="!text")
	public ModelAndView listActivities(@RequestParam(defaultValue="me") String person, String pageToken) {
		
		ActivitiesPage activities = google.activityOperations().getActivitiesPage(person, pageToken);
		
		return new ModelAndView("activities", "activities", activities);
	}
	
	@RequestMapping(value="activities", method=GET, params="text")
	public ModelAndView searchActivities(String text, String pageToken) {
		
		ActivitiesPage activities = google.activityOperations().activityQuery()
			.searchFor(text)
			.fromPage(pageToken)
			.getPage();
		
		return new ModelAndView("activities", "activities", activities);
	}
	
	@RequestMapping(value="/albums", method=GET)
	public ModelAndView albums() {
		List<Album> albums = google.picasaOperations().getAlbums();
		return new ModelAndView("albums", "albums", albums);
	}
	
	@RequestMapping(value="/album", method=GET)
	public ModelAndView newAlbum() {
		return new ModelAndView("album", "album", new AlbumForm());
	}
	
	@RequestMapping(value="/album", method=GET, params="id")
	public ModelAndView album(@RequestParam String id) {
		
		Album album = google.picasaOperations().getAlbum(id);
		AlbumForm command = new AlbumForm(album.getId(), album.getTitle(), album.getSummary());
		
		return new ModelAndView("album", "album", album)
			.addObject("command", command);
	}
	
	@RequestMapping(value="/album", method=POST)
	public ModelAndView saveAlbum(@Valid AlbumForm command, BindingResult result) {
		
		if(result.hasErrors()) {
//			return album
		}
		return null;
	}
	
}