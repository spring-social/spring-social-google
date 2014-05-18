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
package org.springframework.social.google.api.userinfo;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

public class UserInfoTemplateTests extends AbstractGoogleApiTest {

	@Test
	public void getUserInfo() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/oauth2/v2/userinfo"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("userinfo"), APPLICATION_JSON));

		GoogleUserInfo userInfo = google.userOperations().getUserInfo();
		assertNotNull(userInfo);
		assertEquals("114863353858610846998", userInfo.getId());
		assertEquals("guznik@gmail.com", userInfo.getEmail());
		assertEquals("Gabriel Axel", userInfo.getName());
		assertEquals("Gabriel", userInfo.getFirstName());
		assertEquals("Axel", userInfo.getLastName());
		assertEquals("https://plus.google.com/+GabrielAxel", userInfo.getLink());
		assertEquals(
				"https://lh5.googleusercontent.com/-UyuMuAWmKIM/AAAAAAAAAAI/AAAAAAAAAn0/pMK2DzFNBNI/photo.jpg",
				userInfo.getProfilePictureUrl());
		assertEquals("male", userInfo.getGender());
		assertEquals("en", userInfo.getLocale());
	}

}
