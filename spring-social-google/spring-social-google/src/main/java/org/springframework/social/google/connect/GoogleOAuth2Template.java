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
	
	public static final String ACCESS_TYPE_PARAMETER_NAME = "access_type";
	public static final String ACCESS_TYPE_OFFLINE = "offline";
	public static final String ACCESS_TYPE_ONLINE = "online";

	private final boolean offline;

	public GoogleOAuth2Template(String clientId, String clientSecret) {
		this(clientId, clientSecret, false);
	}
	
	/**
	 * @param clientId
	 * @param clientSecret
	 * @param defaultOffline
	 *				if true, the default access_type for all 
	 *					authorize urls will be "offline", which
	 *                  gives us a refresh token for offline use
	 */
	public GoogleOAuth2Template(String clientId, String clientSecret, boolean defaultOffline) {
		super(clientId, clientSecret,
				"https://accounts.google.com/o/oauth2/auth",
				"https://accounts.google.com/o/oauth2/token");
		setUseParametersForClientAuthentication(true);
		this.offline = defaultOffline;
	}

	@Override
	public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
		addAccessTypeIfMissing(parameters);
		return super.buildAuthorizeUrl(grantType, parameters);
	}
	
	protected void addAccessTypeIfMissing(OAuth2Parameters parameters) {
		if(!parameters.containsKey(ACCESS_TYPE_PARAMETER_NAME)) {
			parameters.add(ACCESS_TYPE_PARAMETER_NAME, offline ? ACCESS_TYPE_OFFLINE : ACCESS_TYPE_ONLINE);
		}
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
	
	private AccessGrant extractAccessGrant(Map<String, Object> result) {
		String accessToken = (String) result.get("access_token");
		String scope = (String) result.get("scope");
		String refreshToken = (String) result.get("refresh_token");
		Integer expiresIn = (Integer) result.get("expires_in");
		return createAccessGrant(accessToken, scope, refreshToken, expiresIn, result);
	}
	
}
