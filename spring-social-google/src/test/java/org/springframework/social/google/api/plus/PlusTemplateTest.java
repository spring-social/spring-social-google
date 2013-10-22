package org.springframework.social.google.api.plus;

import static java.util.Collections.singletonMap;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

public class PlusTemplateTest extends AbstractGoogleApiTest {

	@Test
	public void getPersonById() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/people/114863353858610846998"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("person"), APPLICATION_JSON));
		Person person = google.plusOperations().getPerson("114863353858610846998");
		assertPerson(person);
	}
	
	@Test
	public void getSelfProfile() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/people/me"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("person"), APPLICATION_JSON));
		Person person = google.plusOperations().getGoogleProfile();
		assertPerson(person);
	}
	
	private void assertPerson(Person person) {
		assertNotNull(person);
		assertEquals("114863353858610846998", person.getId());
		assertEquals("Gabriel", person.getGivenName());
		assertEquals("Axel", person.getFamilyName());
		assertEquals("Gabriel Axel", person.getDisplayName());
		assertEquals("male", person.getGender());
		assertEquals("Software engineer", person.getAboutMe());
		assertEquals("single", person.getRelationshipStatus());
		assertEquals("https://lh5.googleusercontent.com/-UyuMuAWmKIM/AAAAAAAAAAI/AAAAAAAAAn0/pMK2DzFNBNI/photo.jpg?sz=50", person.getImageUrl());
		
		List<ProfileUrl> expectedUrls = asList(
			new ProfileUrl("http://il.linkedin.com/pub/gabriel-axel/13/782/8a", "http://il.linkedin.com/pub/gabriel-axel/13/782/8a", null),
			new ProfileUrl("http://twitter.com/GabiAxel", "gabiaxel", null),
			new ProfileUrl("https://github.com/GabiAxel/spring-social-google", "Spring Social Google", null),
			new ProfileUrl("http://www.gabiaxel.com", "Blog", UrlType.OTHER));
		assertEquals(expectedUrls, person.getUrls());
		
		List<Organization> expectedOrganizations = asList(
			new Organization("The Open University of Israel", "Natural Science", "school", "2003", "2011", true));
		assertEquals(expectedOrganizations, person.getOrganizations());
		
		Map<String, Boolean> expectedPlacesLived = singletonMap("Israel", true);
		assertEquals(expectedPlacesLived, person.getPlacesLived());
	}
}
