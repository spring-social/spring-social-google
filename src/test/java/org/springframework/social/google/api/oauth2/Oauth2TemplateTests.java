/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

public class Oauth2TemplateTests extends AbstractGoogleApiTest {

  @Test
  public void getUserInfo() {
    mockServer
      .expect(requestTo("https://www.googleapis.com/oauth2/v2/userinfo?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("userinfo"), APPLICATION_JSON));
    final UserInfo userInfo = google.oauth2Operations().getUserinfo();
    assertUserInfo(userInfo);
  }

  private void assertUserInfo(final UserInfo userInfo) {
    assertNotNull(userInfo);
    assertEquals("115195428470189335897", userInfo.getId());
    assertEquals("charles maheu", userInfo.getName());
    assertEquals("charles", userInfo.getGivenName());
    assertEquals("maheu", userInfo.getFamilyName());
    assertEquals("choc.mah@gmail.com", userInfo.getEmail());
    assertEquals("https://plus.google.com/115195428470189335897", userInfo.getLink());
    assertEquals("https://lh6.googleusercontent.com/-f5nUw3UA0Cc/AAAAAAAAAAI/AAAAAAAAABw/1r78kB5lAEg/photo.jpg", userInfo.getPicture());
  }
}
