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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Contact;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.GoogleProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Simple little @Controller that invokes Facebook and renders the result.
 * The injected {@link Facebook} reference is configured with the required authorization credentials for the current user behind the scenes.
 * @author Keith Donald
 */
@Controller
public class HomeController {

	private final Google google;
	
	@Autowired
	public HomeController(Google google) {
		this.google = google;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		GoogleProfile profile = google.userOperations().getUserProfile();
		List<Contact> contacts = google.contactOperations().getContactList();
		
		model.addAttribute("profile", profile);
		model.addAttribute("contacts", contacts);
		
		return "home";
	}

}