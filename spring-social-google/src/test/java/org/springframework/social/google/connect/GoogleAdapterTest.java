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
package org.springframework.social.google.connect;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.social.google.api.userinfo.UserInfoOperations;

public class GoogleAdapterTest {

	private GoogleAdapter apiAdapter = new GoogleAdapter();
	
	private Google google = Mockito.mock(Google.class);
	
	@Test
	public void fetchProfile() {
		UserInfoOperations userOperations = Mockito.mock(UserInfoOperations.class);
		Mockito.when(google.userOperations()).thenReturn(userOperations);
		Mockito.when(userOperations.getUserInfo()).thenReturn(new GoogleUserInfo("123456789", "guznik@gmail.com", "Gabriel Axel", "Gabriel", "Axel", "https://plus.google.com/114863353858610846998", "https://lh5.googleusercontent.com/-UyuMuAWmKIM/AAAAAAAAAAI/AAAAAAAAAn0/pMK2DzFNBNI/photo.jpg", "male", "en"));
		UserProfile profile = apiAdapter.fetchUserProfile(google);
		assertEquals("Gabriel Axel", profile.getName());
		assertEquals("Gabriel", profile.getFirstName());
		assertEquals("Axel", profile.getLastName());
		assertEquals("guznik@gmail.com", profile.getEmail());
		assertEquals("guznik@gmail.com", profile.getUsername());
	}
	
}
