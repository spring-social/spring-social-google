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
package org.springframework.social.google.api.plus.moments;

/**
 * Postal address
 *
 * @author Gabriel Axel
 *
 */
public class PostalAddress {

  private String addressCountry;

  private String addressLocality;

  private String addressRegion;

  private String postalCode;

  private String streetAddress;

  public String getAddressCountry() {
    return addressCountry;
  }

  public String getAddressLocality() {
    return addressLocality;
  }

  public String getAddressRegion() {
    return addressRegion;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

}
