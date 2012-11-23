

import static java.util.Collections.singletonList;
import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS;
import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;
import static org.springframework.http.MediaType.APPLICATION_ATOM_XML;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.apache.commons.collections.MapUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.impl.PatchBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

public class Drive {

	public static void main(String[] args) throws Exception {

		Map<String, Object> map = new PatchBuilder()
			.set("labels.starred", true)
			.set("labels.hidden", false)
			.getMap();
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setSerializationInclusion(NON_NULL);
		
		String str = objectMapper.writeValueAsString(map);
		
		System.out.println(str);
		
//		System.out.println(URLEncoder.encode("=", "UTF-8"));
		
//		Google google = new GoogleTemplate("ya29.AHES6ZRTnoYTC6wdETQFF-6rFg13jyIi11Ntiz_eYy2l7QyU");
//		DriveFilesPage page = google.driveOperations().driveFileQuery().titleIs("Appartments").getPage();
//		System.out.println(page.getItems().size());
		
//		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
//		objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
//		objectMapper.setSerializationInclusion(NON_NULL);
//		jsonConverter.setObjectMapper(objectMapper);
//		
//		SourceHttpMessageConverter<Source> sourceConverter = new SourceHttpMessageConverter<Source>();
//		sourceConverter.setSupportedMediaTypes(singletonList(APPLICATION_ATOM_XML));
//
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//		messageConverters.add(jsonConverter);
//		
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.setMessageConverters(messageConverters);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "Bearer ya29.AHES6ZRTnoYTC6wdETQFF-6rFg13jyIi11Ntiz_eYy2l7QyU");
//		HttpEntity<Map<String,String>> entity = new HttpEntity<Map<String,String>>(headers);
//		ResponseEntity<DriveFilesPage> response = restTemplate.exchange(new URI("https://www.googleapis.com/drive/v2/files?q=title%3D'Appartments'"), HttpMethod.GET, entity, DriveFilesPage.class);
//		System.out.println(response.getBody().getItems().size());
	}

}
