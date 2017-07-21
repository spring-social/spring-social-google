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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a full Google profile in people
 * @author Oscar Carballo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeoplePerson extends ApiEntity {

  private List<Name> names;

  private List<Gender> genders;

  private List<Birthday> birthdays;

  private List<EmailAddress> emailAddresses;

  private PersonMetada metadata;

  private List<Photo> photos;

  @Override
  public String getId() {
    return getMetadata().getSources().get(0).getId();
  }

  @Override
  public String getEtag() {
    return getMetadata().getSources().get(0).getEtag();
  }


  public List<Name> getNames() {
    return names;
  }

  public List<Gender> getGenders() {
    return genders;
  }

  public List<Birthday> getBirthdays() {
    return birthdays;
  }

  public List<EmailAddress> getEmailAddresses() {
    return emailAddresses;
  }

  public PersonMetada getMetadata() {
    return metadata;
  }

  public List<Photo> getPhotos() {
    return photos;
  }
}
