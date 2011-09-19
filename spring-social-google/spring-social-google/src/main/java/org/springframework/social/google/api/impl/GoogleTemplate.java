package org.springframework.social.google.api.impl;

import static java.util.Collections.singletonList;
import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.springframework.http.MediaType.APPLICATION_ATOM_XML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.activity.ActivityOperations;
import org.springframework.social.google.api.contact.ContactOperations;
import org.springframework.social.google.api.impl.activity.ActivityTemplate;
import org.springframework.social.google.api.impl.contact.ContactTemplate;
import org.springframework.social.google.api.impl.legacyprofile.UserTemplate;
import org.springframework.social.google.api.impl.profile.ProfileTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.social.google.api.profile.ProfileOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.web.client.RestTemplate;

public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

	private final LegacyProfileOperations userOperations;
	private final ContactOperations contactOperations;
	private final ProfileOperations profileOperations;
	private final ActivityOperations activityOperations;
	
	public GoogleTemplate(String accessToken) {
		super(accessToken);

		System.out.println(accessToken);
		
		RestTemplate restTemplate = getRestTemplate();
		
		ClientHttpRequestInterceptor[] interceptors = restTemplate.getInterceptors();
		ClientHttpRequestInterceptor[] newInterceptors = new ClientHttpRequestInterceptor[interceptors.length+1];
		System.arraycopy(interceptors, 0, newInterceptors, 0, interceptors.length);
		newInterceptors[interceptors.length] = new GDataInterceptor();
		restTemplate.setInterceptors(newInterceptors);
		
		userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
		contactOperations = new ContactTemplate(getRestTemplate(), isAuthorized());
		profileOperations = new ProfileTemplate(getRestTemplate(), isAuthorized());
		activityOperations = new ActivityTemplate(getRestTemplate(), isAuthorized());
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
	public ProfileOperations profileOperations() {
		return profileOperations;
	}

	@Override
	public ActivityOperations activityOperations() {
		return activityOperations;
	}
	
}
