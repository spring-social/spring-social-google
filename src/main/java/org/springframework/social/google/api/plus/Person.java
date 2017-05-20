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
package org.springframework.social.google.api.plus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.social.google.api.ApiEntity;
import org.springframework.social.google.api.plus.impl.AgeRangeDeserializer;

/**
 * Model class representing a full Google profile
 *
 * @author Gabriel Axel
 */
public class Person extends ApiEntity {
  @JsonProperty
  private Name name;
  private String displayName;
  private String url;

  @JsonProperty("isPlusUser")
  private boolean plusUser;
  private int circledByCount;

  @JsonProperty
  private Image image;
  private String thumbnailUrl;
  private Date birthday;
  private String gender;
  private String occupation;
  private String aboutMe;
  private String tagline;
  private String nickname;
  private String language;
  private Boolean verified = false;
  private String relationshipStatus;
  private List<ProfileUrl> urls;
  private List<Organization> organizations;
  private Map<String, Boolean> placesLived;
  private Map<String, String> emails;

  @JsonProperty
  @JsonDeserialize(using = AgeRangeDeserializer.class)
  private AgeRange ageRange = AgeRange.UNKNOWN;

  @Override
  public String toString() {
    return displayName;
  }

  public String getGivenName() {
    return name == null ? null : name.givenName;
  }

  public String getFamilyName() {
    return name == null ? null : name.familyName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getUrl() {
    return url;
  }

  public boolean isPlusUser() {
    return plusUser;
  }

  public int getCircledByCount() {
    return circledByCount;
  }

  /**
   * Get the image URL - uses the thumbnail if set, then the main image, otherwise returns null.
   */
  public String getImageUrl() {
    if (thumbnailUrl != null) {
      return thumbnailUrl;
    }

    if (image != null) {
      return image.url;
    }

    return null;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getGender() {
    return gender;
  }

  public String getOccupation() {
    return occupation;
  }

  public String getAboutMe() {
    return aboutMe;
  }

  public String getTagline() {
    return tagline;
  }

  public String getNickname() {
    return nickname;
  }

  public String getLanguage() {
    return language;
  }

  public Boolean isVerified() {
    return verified;
  }

  public String getRelationshipStatus() {
    return relationshipStatus;
  }

  public List<ProfileUrl> getUrls() {
    return urls;
  }

  public List<Organization> getOrganizations() {
    return organizations;
  }

  public Map<String, Boolean> getPlacesLived() {
    return placesLived;
  }

  @JsonSetter
  private void setPlacesLived(final List<PlaceLived> placesLivedAsList) {
    placesLived = new LinkedHashMap<>();
    if (placesLivedAsList != null) {
      for (final PlaceLived placeLived : placesLivedAsList) {
        placesLived.put(placeLived.value, placeLived.primary);
      }
    }
  }

  public Map<String, String> getEmails() {
    return emails;
  }

  @JsonSetter
  private void setEmails(final List<Email> emailsAsList) {
    emails = new LinkedHashMap<>();
    if (emailsAsList != null) {
      for (final Email email : emailsAsList) {
        emails.put(email.value, email.type);
      }
    }
  }

  /**
   * Return the email address(es) associated with this person.

   * @return Null if no emails found, otherwise a set.
   */
  public Set<String> getEmailAddresses() {
    return emails == null ? null : emails.keySet();
  }

  /**
   * Return the account email.
   *
   * @return Null if no account email found, otherwise a single String.
   */
  public String getAccountEmail() {
    if (emails != null) {
      for (final Entry<String, String> entry : emails.entrySet()) {
        if (entry.getValue().equals("account")) {
          return entry.getKey();
        }
      }
    }
    return null;
  }

  public AgeRange getAgeRange() {
    return ageRange;
  }

  private static class Name {
    @JsonProperty
    private String givenName;

    @JsonProperty
    private String familyName;
  }

  private static class Image {
    @JsonProperty
    private String url;
  }

  private static class PlaceLived {
    @JsonProperty
    private String value;

    @JsonProperty
    private boolean primary;
  }

  private static class Email {
    @JsonProperty
    private String value;

    @JsonProperty
    private String type;
  }
}
