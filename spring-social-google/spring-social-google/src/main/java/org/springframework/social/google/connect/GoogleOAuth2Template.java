package org.springframework.social.google.connect;

import java.util.Collections;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GoogleOAuth2Template extends OAuth2Template {
	
	public GoogleOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret,
				"https://accounts.google.com/o/oauth2/auth",
				"https://accounts.google.com/o/oauth2/token");
	}

	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(
				ClientHttpRequestFactorySelector.getRequestFactory());
		FormHttpMessageConverter messageConverter = new FormHttpMessageConverter() {
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				// always read as x-www-url-formencoded even though Facebook
				// sets contentType to text/plain
				return true;
			}
		};
		restTemplate.setMessageConverters(Collections
				.<HttpMessageConverter<?>> singletonList(messageConverter));
		return restTemplate;
	}

	@SuppressWarnings("unchecked")
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		try {
			Map<String, Object> responseMap = getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class);
			String responseText = responseMap.keySet().iterator().next();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> responseParameters = mapper.readValue(responseText, Map.class);
			return extractAccessGrant(responseParameters);
		} catch(Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	private AccessGrant extractAccessGrant(Map<String, Object> result) {
		String accessToken = (String) result.get("access_token");
		String scope = (String) result.get("scope");
		String refreshToken = (String) result.get("refresh_token");
		Integer expiresIn = (Integer) result.get("expires_in");
		
		
		
		return createAccessGrant(accessToken, scope, refreshToken, expiresIn, result);
	}
	
	//Authorization: OAuth oauth_version="1.0", oauth_nonce="8960effbfb92bf69db425d894e63c824", oauth_timestamp="1312926066", oauth_consumer_key="googlecodesamples.com", oauth_token="1%2FzJHDMNg1h0LrgOHdxmlGPbynjd55hfqSgfD1coEqO5A", oauth_signature_method="RSA-SHA1", oauth_signature="0W%2BLbdCAtGSOhcLcjUwJxImDDbtcFIhoMLYMjRJmt7oX%2FOdi2qk243OUT523rPqxeOHUyCuV6GU9fmn7k0SU43M3HoxuFA7c%2FA2ovlvHcB%2BoQ1BC%2FrE5CcDIKX%2BSW%2Fk4MpmOZ7CsDLVoM6Iz3llJrU57UBKLCJg8ZW15TqyO8Hk%3D"

}
