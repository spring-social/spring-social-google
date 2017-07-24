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

/**
 * Defines operations for searching and retrieving Google people information. To use "me" as user ID,
 * requires OAuth2 authentication.
 *
 *
 * @author Oscar Carballo
 */
public interface PeopleOperations {

  /**
   * All the fields available in google people api. In order to get a field value, the authentication
   * must have been done with the right scope.
   */
  String ALL_FIELDS = "addresses,ageRanges,biographies,birthdays,braggingRights,coverPhotos,"
    + "emailAddresses,events,genders,imClients,interests,locales,memberships,metadata,names,nicknames,occupations,"
    + "organizations,phoneNumbers,photos,relations,relationshipInterests,relationshipStatuses,residences,skills"
    + "taglines,urls";

  /**
   * Retrieves a user's Google profile. Google people api requires to send the fields you want back.
   * This will try to retrieve all fields for that user. The expected values will be returned
   * as long as the right scope has been requested previously
   *
   * @param id user ID or "me"
   * @return the retrieved {@link PeoplePerson}
   */
  PeoplePerson getPerson(final String id);

  /**
   * Retrieves a user's Google profile populated with the requested fields. Google people api
   * requires to send the fields you want back. This will try to retrieve the fields passed in.
   * The expected values will be returned as long as the right scope has been
   * requested previously
   *
   * @param id user ID or "me"
   * @param fields comma separated values containing the desired fields
   * @return the retrieved {@link PeoplePerson}
   */
  PeoplePerson getPerson(final String id, String fields);


  /**
   * Retrieves the authenticated user's complete Google profile. Google people api requires to
   * send the fields you want back.
   * This will try to retrieve all fields for that user. The expected values will be
   * returned as long as the right scope has been requested previously
   *
   * @return the retrieved {@link PeoplePerson}
   */
  PeoplePerson getGoogleProfile();


  /**
   * Retrieves the authenticated user's complete Google profile. Google people api requires to
   * send the fields you want back.
   * This will try to retrieve the fields passed in. The expected values will be
   * returned as long as the right scope has been requested previously
   *
   * @param fields user ID or "me"
   * @return the retrieved {@link PeoplePerson}
   */
  PeoplePerson getGoogleProfile(final String fields);



}
