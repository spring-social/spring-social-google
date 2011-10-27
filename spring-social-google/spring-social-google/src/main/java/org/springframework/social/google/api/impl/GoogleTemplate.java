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
package org.springframework.social.google.api.impl;

import static java.util.Collections.singletonList;
import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.springframework.http.MediaType.APPLICATION_ATOM_XML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.gdata.contact.ContactOperations;
import org.springframework.social.google.api.gdata.contact.impl.ContactTemplate;
import org.springframework.social.google.api.gdata.picasa.PicasaOperations;
import org.springframework.social.google.api.gdata.picasa.impl.PicasaTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.social.google.api.legacyprofile.impl.UserTemplate;
import org.springframework.social.google.api.plus.activity.ActivityOperations;
import org.springframework.social.google.api.plus.activity.impl.ActivityTemplate;
import org.springframework.social.google.api.plus.person.PersonOperations;
import org.springframework.social.google.api.plus.person.impl.PersonTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * The central class for interacting with Google APIs.
 * </p>
 * <p>
 * Most of the APIs, specifically all GData APIs and Google+ usage of "me", require OAuth2 authentication.
 * To use methods that require OAuth2 authentication, {@link GoogleTemplate} must be constructed with
 * an access token which is authorized for the appropriate scope.
 * For using Google+ without authenticating, {@link GoogleTemplate} default constructor can be used.
 * </p>
 * @author Gabriel Axel
 */
public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

	private LegacyProfileOperations userOperations;
	private ContactOperations contactOperations;
	private PersonOperations profileOperations;
	private ActivityOperations activityOperations;
	private PicasaOperations picasaOperations;
	
	/**
	 * Creates a new instance of GoogleTemplate.
	 * This constructor creates a new GoogleTemplate able to perform unauthenticated operations against Google+.
	 */
	public GoogleTemplate() {
		initialize();
	}
	
	/**
	 * Creates a new instance of GoogleTemplate.
	 * This constructor creates the FacebookTemplate using a given access token.
	 * @param accessToken an access token granted by Google after OAuth2 authentication
	 */
	public GoogleTemplate(String accessToken) {
		super(accessToken);
		initialize();
	}

	private void initialize() {
		userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
		contactOperations = new ContactTemplate(getRestTemplate(), isAuthorized());
		profileOperations = new PersonTemplate(getRestTemplate(), isAuthorized());
		activityOperations = new ActivityTemplate(getRestTemplate(), isAuthorized());
		picasaOperations = new PicasaTemplate(getRestTemplate(), isAuthorized());
	}
	
	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {		
		restTemplate.getInterceptors().add(new GDataInterceptor());
	}
	
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonConverter.setObjectMapper(objectMapper);
		
		SourceHttpMessageConverter<Source> sourceConverter = new SourceHttpMessageConverter<Source>();
		sourceConverter.setSupportedMediaTypes(singletonList(APPLICATION_ATOM_XML));

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(jsonConverter);
		messageConverters.add(sourceConverter);
		messageConverters.add(new ByteArrayHttpMessageConverter());
		return messageConverters;
	}
	
	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.DRAFT_10;
	}

	@Override
	public LegacyProfileOperations userOperations() {
		return userOperations;
	}

	@Override
	public ContactOperations contactOperations() {
		return contactOperations;
	}

	@Override
	public PersonOperations personOperations() {
		return profileOperations;
	}

	@Override
	public ActivityOperations activityOperations() {
		return activityOperations;
	}

	@Override
	public PicasaOperations picasaOperations() {
		return picasaOperations;
	}
	
}
