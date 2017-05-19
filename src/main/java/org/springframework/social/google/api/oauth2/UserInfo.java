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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.social.google.api.ApiEntity;

/**
 * Model class representing a Google user
 *
 * @author Charles
 */
public class UserInfo extends ApiEntity {

  @JsonProperty
  private String name;

  @JsonProperty("given_name")
  private String givenName;

  @JsonProperty("family_name")
  private String familyName;

  @JsonProperty
  private String gender;

  @JsonProperty
  private String picture;

  @JsonProperty
  private String link;

  @JsonProperty
  private String email;

  @JsonProperty("verified_email")
  private String verifiedEmail;

  @JsonProperty
  private String hd;

  @JsonProperty
  private String locale;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(final String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(final String familyName) {
    this.familyName = familyName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(final String gender) {
    this.gender = gender;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(final String picture) {
    this.picture = picture;
  }

  public String getLink() {
    return link;
  }

  public void setLink(final String link) {
    this.link = link;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getVerifiedEmail() {
    return verifiedEmail;
  }

  public void setVerifiedEmail(final String verifiedEmail) {
    this.verifiedEmail = verifiedEmail;
  }

  public String getHd() {
    return hd;
  }

  public void setHd(final String hd) {
    this.hd = hd;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(final String locale) {
    this.locale = locale;
  }

}
