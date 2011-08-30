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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.ContactGroup;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.GoogleProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final Google google;
	
	@Autowired
	public HomeController(Google google) {
		this.google = google;
	}

	@RequestMapping(value="/", method=GET)
	public ModelAndView home() {
		
		GoogleProfile profile = google.userOperations().getUserProfile();
		
		return new ModelAndView("profile", "profile", profile);
	}
	
	@RequestMapping(value="/contacts", method=GET)
	public ModelAndView contacts(@RequestParam(value="group", required=false) String groupId) {
		
		List<ContactGroup> groups = google.contactOperations().getContactGroupList();
		List<Contact> contacts;
		if(groupId == null) {
			contacts = google.contactOperations().getContactList();
		} else {
			contacts = google.contactOperations().getGroupContacts(groupId);
		}
		
		return new ModelAndView("contacts")
			.addObject("groups", groups)
			.addObject("contacts", contacts);
	}

}