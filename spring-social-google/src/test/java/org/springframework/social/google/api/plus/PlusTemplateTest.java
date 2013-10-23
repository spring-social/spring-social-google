package org.springframework.social.google.api.plus;

import static java.util.Collections.singletonMap;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
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
	
	@Test
	public void searchPeople() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/people?query=pivotal"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("people"), APPLICATION_JSON));
		PeoplePage page = google.plusOperations().searchPeople("pivotal", null);
		
		assertNotNull(page);
		assertEquals("EAIaAA", page.getNextPageToken());
		assertEquals(2, page.getItems().size());
		
		assertEquals("105320112436428794490", page.getItems().get(0).getId());
		assertEquals("Pivotal", page.getItems().get(0).getDisplayName());
		assertEquals("https://lh6.googleusercontent.com/-SpRmYYefQfI/AAAAAAAAAAI/AAAAAAAAAE4/utGWIQK75eE/photo.jpg?sz=50", page.getItems().get(0).getImageUrl());
		
		assertEquals("113677672877589536101", page.getItems().get(1).getId());
		assertEquals("Pivotal Tracker", page.getItems().get(1).getDisplayName());
		assertEquals("https://lh3.googleusercontent.com/-PoFlIF3qlf0/AAAAAAAAAAI/AAAAAAAAAEw/eCwYJIvhyDA/photo.jpg?sz=50", page.getItems().get(1).getImageUrl());
	}
	
	@Test
	public void searchPublicActivities() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/activities?query=spring+social"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("activities"), APPLICATION_JSON));
		ActivitiesPage page = google.plusOperations().searchPublicActivities("spring social", null);
		assertNotNull(page);
		assertEquals("CjAIsKxsEijg0MADiaTFA8n66wPJ-usDyfrrA6Tw-APo0NsEivGMBtzqkQayyJwGGAIKJRIjtIAF5NsHzIMQzPdU1fZu__23AZmC6wHio_gB8c64ApCGwAIKLwjmllESJ5iGSeGB2wHi7ogD9-6wA_qayQPg2KkFpdOxBd_7tAbf-7QGh_W_BhgBEOf5kZMFGP79mJMFIgA", page.getNextPageToken());
		assertEquals(2, page.getItems().size());
		
		assertEquals("z12ycrl5mkify3qas04cdhxgfknvibyy5zg0k", page.getItems().get(0).getId());
		assertEquals("Tweeting StackExchange Questions with #Spring Social", page.getItems().get(0).getTitle());
		assertEquals("114226250987063330965", page.getItems().get(0).getActor().getId());
		assertEquals("Java Code Geeks", page.getItems().get(0).getActor().getDisplayName());

		assertEquals("z12he3zi3k2vwvfh204cdhxgfknvibyy5zg0k", page.getItems().get(1).getId());
		assertEquals("#Spring Social Twitter Setup", page.getItems().get(1).getTitle());
		assertEquals("114226250987063330965", page.getItems().get(1).getActor().getId());
		assertEquals("Java Code Geeks", page.getItems().get(1).getActor().getDisplayName());
	}
	
	@Test
	public void getActivity() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/activities/z13djjbraz2fdfp5g04chb0rkrvwhnmpch4"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("activity"), APPLICATION_JSON));
		Activity activity = google.plusOperations().getActivity("z13djjbraz2fdfp5g04chb0rkrvwhnmpch4");
		assertNotNull(activity);
		assertEquals("z13djjbraz2fdfp5g04chb0rkrvwhnmpch4", activity.getId());
		assertEquals("Spring Social Google 1.0.0.M3 supports Google+ App Activities https://github.com/GabiAxel/spring-social...", activity.getTitle());
		assertEquals(date("2013-09-11T12:14:11.274Z"), activity.getPublished());
		assertEquals(date("2013-09-11T12:15:08.000Z"), activity.getUpdated());
		assertEquals("https://plus.google.com/114863353858610846998/posts/VszgJt887pm", activity.getUrl());
		assertEquals("114863353858610846998", activity.getActor().getId());
		assertEquals("Gabriel Axel", activity.getActor().getDisplayName());
		assertEquals("https://lh5.googleusercontent.com/-UyuMuAWmKIM/AAAAAAAAAAI/AAAAAAAAAn0/rCC-XL5bxAM/photo.jpg?sz=50", activity.getActor().getImageUrl());
		assertEquals("Spring Social Google 1.0.0.M3 supports Google+ App Activities \u003ca href=\"https://github.com/GabiAxel/spring-social-google\" class=\"ot-anchor\" rel=\"nofollow\"\u003ehttps://github.com/GabiAxel/spring-social-google\u003c/a\u003e Documentation: \u003ca href=\"http://gabiaxel.github.io/spring-social-google-reference/\" class=\"ot-anchor\" rel=\"nofollow\"\u003ehttp://gabiaxel.github.io/spring-social-google-reference/\u003c/a\u003e  \u003ca class=\"ot-hashtag\" href=\"https://plus.google.com/s/%23springframework\"\u003e#springframework\u003c/a\u003e   \u003ca class=\"ot-hashtag\" href=\"https://plus.google.com/s/%23googleapis\"\u003e#googleapis\u003c/a\u003e   \u003ca class=\"ot-hashtag\" href=\"https://plus.google.com/s/%23googleplus\"\u003e#googleplus\u003c/a\u003e  ", 
				activity.getContent());
		assertNotNull(activity.getAttachments());
		assertEquals(1, activity.getAttachments().size());
		assertEquals("spring-social-google", activity.getAttachments().get(0).getDisplayName());
		assertEquals("spring-social-google - Spring Social extension with connection support and an API binding for Google", activity.getAttachments().get(0).getContent());
		assertEquals("https://github.com/GabiAxel/spring-social-google", activity.getAttachments().get(0).getUrl());
		assertEquals("https://lh6.googleusercontent.com/proxy/HXa3YsniUc8vczq91QirQ0Ch4CYBa8mzbdwgvIYvtf-1XwqCPcJT44eYaXWKF9RgCTG5ZDCBqme3-zmj0qTQ6Utq_7A2J3lhTFbicYTK_DMUbElyDMsFJtoa=w120-h120", activity.getAttachments().get(0).getPreviewImageUrl());
		assertEquals("image/jpeg", activity.getAttachments().get(0).getPreviewImageContentType());
	}
	
	@Test
	public void listComments() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/activities/z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4/comments"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("comments"), APPLICATION_JSON));
		ActivityCommentsPage page = google.plusOperations().getComments("z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4", null);
		assertNotNull(page);
		assertNull(page.getNextPageToken());
		assertEquals(2, page.getItems().size());
		
		assertEquals("z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4.1364410988265921", page.getItems().get(0).getId());
		assertEquals(date("2013-03-27T19:03:08.265Z"), page.getItems().get(0).getPublished());
		assertEquals(date("2013-03-27T19:03:08.265Z"), page.getItems().get(0).getUpdated());
		assertEquals("112147361367898278139", page.getItems().get(0).getActor().getId());
		assertEquals("Diventa popolare con Spidly", page.getItems().get(0).getActor().getDisplayName());
		assertEquals("https://lh5.googleusercontent.com/-w3cS77NcWNg/AAAAAAAAAAI/AAAAAAAAADE/BoGkzRLqf8U/photo.jpg?sz=50", page.getItems().get(0).getActor().getImageUrl());
		assertEquals("Great project. I&#39;m exploring it. \u003cbr /\u003eI saw you don&#39;t mention how to post on Google Plus streams. \u003cbr /\u003eYou don&#39;t think about it ot it&#39;s just impossibile due to read only access to Google Plus via apis?", 
				page.getItems().get(0).getContent());
		
		assertEquals("z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4.1369027932569419", page.getItems().get(1).getId());
		assertEquals(date("2013-05-20T05:32:12.569Z"), page.getItems().get(1).getPublished());
		assertEquals(date("2013-05-20T05:32:12.569Z"), page.getItems().get(1).getUpdated());
		assertEquals("114863353858610846998", page.getItems().get(1).getActor().getId());
		assertEquals("Gabriel Axel", page.getItems().get(1).getActor().getDisplayName());
		assertEquals("https://lh5.googleusercontent.com/-UyuMuAWmKIM/AAAAAAAAAAI/AAAAAAAAAn0/rCC-XL5bxAM/photo.jpg?sz=50", page.getItems().get(1).getActor().getImageUrl());
		assertEquals("Sorry for the late response. Currently write operations are not supported for activities and people, but I&#39;m working on integration for the Moments API, which let you write app activities.",
				page.getItems().get(1).getContent());
	}
	
	@Test
	public void getComment() {
		mockServer
		.expect(requestTo("https://www.googleapis.com/plus/v1/comments/z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4.1364410988265921"))
		.andExpect(method(GET))
		.andRespond(
				withSuccess(jsonResource("comment"), APPLICATION_JSON));
		ActivityComment comment = google.plusOperations().getComment("z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4.1364410988265921");
		assertEquals("z12ge3o4orj2sdkbb04chb0rkrvwhnmpch4.1364410988265921",comment.getId());
		assertEquals(date("2013-03-27T19:03:08.265Z"), comment.getPublished());
		assertEquals(date("2013-03-27T19:03:08.265Z"), comment.getUpdated());
		assertEquals("112147361367898278139", comment.getActor().getId());
		assertEquals("Diventa popolare con Spidly", comment.getActor().getDisplayName());
		assertEquals("https://lh5.googleusercontent.com/-w3cS77NcWNg/AAAAAAAAAAI/AAAAAAAAADE/BoGkzRLqf8U/photo.jpg?sz=50", comment.getActor().getImageUrl());
		assertEquals("Great project. I&#39;m exploring it. \u003cbr /\u003eI saw you don&#39;t mention how to post on Google Plus streams. \u003cbr /\u003eYou don&#39;t think about it ot it&#39;s just impossibile due to read only access to Google Plus via apis?", 
				comment.getContent());
	}
	
	@Test
	public void listMoments() {
		fail();
	}
	
	@Test
	public void insertMoment() {
		fail();
	}
	
	@Test
	public void deleteMoment() {
		fail();
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
