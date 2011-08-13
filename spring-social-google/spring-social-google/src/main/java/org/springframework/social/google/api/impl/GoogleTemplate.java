package org.springframework.social.google.api.impl;

import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;

public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

	private final UserOperations userOperations;
	
	public GoogleTemplate(String accessToken) {
		super(accessToken);
		System.out.println(accessToken);
		userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
	}
	
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		converter.setObjectMapper(objectMapper);
		messageConverters.add(converter);
		return messageConverters;
	}
	
	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.DRAFT_10;
	}

	public UserOperations userOperations() {
		return userOperations;
	}
}
