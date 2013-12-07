/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.connect;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

/**
 * Google-specific extension to OAuth2Template.
 * @author Gabriel Axel
 */
public class GoogleOAuth2Template extends OAuth2Template {
	
	public GoogleOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret,
				"https://accounts.google.com/o/oauth2/auth",
				"https://accounts.google.com/o/oauth2/token");
		setUseParametersForClientAuthentication(true);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		ResponseEntity<Map> responseEntity = getRestTemplate().exchange(accessTokenUrl, HttpMethod.POST, requestEntity, Map.class);
		Map<String, Object> responseMap = responseEntity.getBody();
		return extractAccessGrant(responseMap);
	}
	
  @Override
  public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
    String scope = parameters.getScope();
    if (scope != null) {
      if (scope.contains("plus.login")) {
        parameters.add("request_visible_actions", ALL_MOMENT_ACTIVITY_TYPES);
        parameters.add("access_type", "offline");
      }
    }

    return super.buildAuthorizeUrl(grantType, parameters);
  }
	
	private AccessGrant extractAccessGrant(Map<String, Object> result) {
		String accessToken = (String) result.get("access_token");
		String scope = (String) result.get("scope");
		String refreshToken = (String) result.get("refresh_token");
		
		// result.get("expires_in") may be an Integer, so cast it to Number first. 	
		Number expiresInNumber = (Number) result.get("expires_in");
		Long expiresIn = (expiresInNumber == null) ? null : expiresInNumber.longValue();
		
		return createAccessGrant(accessToken, scope, refreshToken, expiresIn, result);
	}
	
  private static final String ALL_MOMENT_ACTIVITY_TYPES;

  static {
    final String[] MOMENT_ACTIVITY_TYPES = new String[] {
      "Add", "Buy", "CheckIn", "Comment", "Create", "Discover", "Listen",
      "Reserve", "Review", "Want"
    };

    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String activityType : MOMENT_ACTIVITY_TYPES) {
      if (first) {
        first = false;
      } else {
        sb.append(' ');
      }
      sb.append("http://schemas.google.com/");
      sb.append(activityType);
      sb.append("Activity");
    }
    ALL_MOMENT_ACTIVITY_TYPES = sb.toString();
  }
}
