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
package org.springframework.social.google.api.people;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class PeopleTemplateTest extends AbstractGoogleApiTest {

  @Test
  public void getPersonById() {
    mockServer
      .expect(requestTo("https://people.googleapis.com/v1/people/115661085724677333632?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("people"), APPLICATION_JSON));
    final PeoplePerson person = google.peopleOperations().getPerson("115661085724677333632");
    assertPerson(person);
  }

  @Test
  public void getSelfProfile() {
    mockServer
      .expect(requestTo("https://people.googleapis.com/v1/people/me?access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("people"), APPLICATION_JSON));
    final PeoplePerson person = google.peopleOperations().getGoogleProfile();
    assertPerson(person);
  }

  private void assertPerson(final PeoplePerson person) {
    assertNotNull(person);
    assertEquals("115661085724677333632", person.getMetadata().getSources().get(0).getId());
    assertEquals("115661085724677333632", person.getId());
    assertEquals("#8OZqg2xl60w=", person.getMetadata().getSources().get(0).getEtag());
    assertEquals("#8OZqg2xl60w=", person.getEtag());
    assertThat(person.getNames().get(0).getGivenName(), is("Oscar"));
    assertThat(person.getNames().get(0).getFamilyName(), is("Carballo"));
    assertThat(person.getBirthdays().get(0).getDate().getDay(), is(21));
    assertThat(person.getBirthdays().get(0).getDate().getMonth(), is(10));
    assertThat(person.getBirthdays().get(0).getDate().getYear(), is(1979));
    assertThat(person.getGenders().get(0).getValue(), is("male"));
    assertThat(person.getEmailAddresses().get(0).getValue(), is("oscar.carballo@scout24.com"));
    assertThat(person.getPhotos().get(0).getUrl(), is("https://lh6.googleusercontent.com/-1lUywZzxLoQ/AAAAAAAAAAI/AAAAAAAAAC0/c95Whdc5AAA/s100/photo.jpg"));
  }

}
