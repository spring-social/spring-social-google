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
import static org.springframework.social.google.api.people.impl.PeopleTemplate.ALL_FIELDS;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class PeopleTemplateTest extends AbstractGoogleApiTest {

  @Test
  public void getFullPersonById() {
    mockServer
      .expect(requestTo("https://people.googleapis.com/v1/people/115661085724677333632?personFields=" +
        ALL_FIELDS + "&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("people"), APPLICATION_JSON));
    final PeoplePerson person = google.peopleOperations().getPerson("115661085724677333632");
    assertPerson(person);
  }

  @Test
  public void getFullSelfProfile() {
    mockServer
      .expect(requestTo("https://people.googleapis.com/v1/people/me?personFields="
        + ALL_FIELDS + "&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("people"), APPLICATION_JSON));
    final PeoplePerson person = google.peopleOperations().getGoogleProfile();
    assertPerson(person);
  }

  @Test
  public void getPersonByIdAndFields() {
    mockServer
      .expect(requestTo("https://people.googleapis.com/v1/people/115661085724677333632?" +
        "personFields=names,genders&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("people"), APPLICATION_JSON));
    final PeoplePerson person = google.peopleOperations().getPerson("115661085724677333632", "names,genders");
    assertPerson(person); // In a real api call only names and genders would be there.
  }


  @Test
  public void getSelfProfileWithFields() {
    mockServer
      .expect(requestTo("https://people.googleapis.com/v1/people/me?" +
        "personFields=names,genders&access_token=ACCESS_TOKEN"))
      .andExpect(method(GET))
      .andRespond(
        withSuccess(jsonResource("people"), APPLICATION_JSON));
    final PeoplePerson person = google.peopleOperations().getGoogleProfile("names,genders");
    assertPerson(person); // In a real api call only names and genders would be there.
  }

  private void assertPerson(final PeoplePerson person) {
    assertNotNull(person);
    assertEquals("115661085724677333632", person.getMetadata().getSources().get(0).getId());
    assertEquals("115661085724677333632", person.getId());
    assertEquals("#8OZqg2xl60w=", person.getMetadata().getSources().get(0).getEtag());
    assertEquals("#8OZqg2xl60w=", person.getEtag());
    assertThat(person.getNames().get(0).getGivenName(), is("Name"));
    assertThat(person.getNames().get(0).getFamilyName(), is("FName"));
    assertThat(person.getBirthdays().get(0).getDate().getDay(), is(10));
    assertThat(person.getBirthdays().get(0).getDate().getMonth(), is(3));
    assertThat(person.getBirthdays().get(0).getDate().getYear(), is(1981));
    assertThat(person.getGenders().get(0).getValue(), is("male"));
    assertThat(person.getEmailAddresses().get(0).getValue(), is("some.person@gmail.com"));
    assertThat(person.getPhotos().get(0).getUrl(), is("https://lh3.googleusercontent.com/a-/AAuE7mBnpXpWH1YvoJMrzTsC3H34HeWaFPYSTsIrk1bQrA=s100"));
  }

}
