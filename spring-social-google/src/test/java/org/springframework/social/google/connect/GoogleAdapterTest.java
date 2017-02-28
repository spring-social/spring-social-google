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

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.google.api.Google;

import static org.junit.Assert.assertEquals;
import org.springframework.social.google.api.oauth2.OAuth2Operations;
import org.springframework.social.google.api.oauth2.UserInfo;

public class GoogleAdapterTest {

	private final GoogleAdapter apiAdapter = new GoogleAdapter();

	private final Google google = Mockito.mock(Google.class);

	@Test
	public void fetchProfile() {

		OAuth2Operations authOperations = Mockito.mock(OAuth2Operations.class);
		Mockito.when(google.oauth2Operations()).thenReturn(authOperations);

		UserInfo userInfo = Mockito.mock(UserInfo.class);
		Mockito.when(userInfo.getName()).thenReturn("Gabriel Axel");
		Mockito.when(userInfo.getGivenName()).thenReturn("Gabriel");
		Mockito.when(userInfo.getFamilyName()).thenReturn("Axel");
		Mockito.when(userInfo.getEmail()).thenReturn("guznik@gmail.com");
		Mockito.when(userInfo.getId()).thenReturn("114863353858610846998");


		Mockito.when(authOperations.getUserinfo()).thenReturn(userInfo);
                
		UserProfile profile = apiAdapter.fetchUserProfile(google);
		assertEquals("Gabriel Axel", profile.getName());
		assertEquals("Gabriel", profile.getFirstName());
		assertEquals("Axel", profile.getLastName());
		assertEquals("guznik@gmail.com", profile.getEmail());
		assertEquals("114863353858610846998", profile.getUsername());
	}

}
