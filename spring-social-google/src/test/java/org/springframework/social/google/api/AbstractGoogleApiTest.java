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
package org.springframework.social.google.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONObject;
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.test.web.client.MockRestServiceServer;

public class AbstractGoogleApiTest {

	protected GoogleTemplate google;
	
	protected GoogleTemplate appAuthGoogle;
	
	protected MockRestServiceServer mockServer;
	
	protected MockRestServiceServer appAuthMockServer;
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
	static {
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	@Before
	public void setup() {
		google = new GoogleTemplate("ACCESS_TOKEN");
		mockServer = MockRestServiceServer.createServer(google.getRestTemplate());
		appAuthGoogle = new GoogleTemplate("APP_ACCESS_TOKEN");
		appAuthMockServer = MockRestServiceServer.createServer(appAuthGoogle.getRestTemplate());
	}
	
	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}
	
	protected static Date date(String formatted) {
		try {
			return dateFormat.parse(formatted);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected String encodeUTF8(String textToEncode) throws UnsupportedEncodingException {

		return URLEncoder.encode(textToEncode, "UTF-8");
	}

	protected String normalizeJsonObjectLineFeeds(String fileJsonString) {

		return new JSONObject(fileJsonString).toString();
	}
}
