package org.springframework.social.google.api.oauth2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;
import org.springframework.social.google.api.plus.Person;

public class Oauth2TemplateTests extends AbstractGoogleApiTest {


	@Test
	public void getUserInfo() {
		mockServer
				.expect(requestTo("https://www.googleapis.com/oauth2/v2/userinfo?access_token=ACCESS_TOKEN"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("userinfo"), APPLICATION_JSON));
		UserInfo userInfo = google.oauth2Operations().getUserinfo();
		assertUserInfo(userInfo);
	}

	private void assertUserInfo(UserInfo userInfo) {
		assertNotNull(userInfo);
		assertEquals("115195428470189335897",userInfo.getId());
		assertEquals("charles maheu",userInfo.getName());
		assertEquals("charles",userInfo.getGivenName());
		assertEquals("maheu",userInfo.getFamilyName());
		assertEquals("choc.mah@gmail.com",userInfo.getEmail());
		assertEquals("https://plus.google.com/115195428470189335897",userInfo.getLink());
		assertEquals("https://lh6.googleusercontent.com/-f5nUw3UA0Cc/AAAAAAAAAAI/AAAAAAAAABw/1r78kB5lAEg/photo.jpg",userInfo.getPicture());
	}
}
