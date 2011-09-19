import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.springframework.social.google.api.activity.ActivitiesFeed;
import org.springframework.social.google.api.activity.Activity;
import org.springframework.social.google.api.profile.GoogleProfile;
import org.springframework.social.google.api.profile.Organization;
import org.springframework.social.google.api.profile.PlaceLived;
import org.springframework.social.google.api.profile.ProfileURL;


public class PlusActivitiesTest {

	public static void main(String[] args) throws Exception {
				
		ObjectMapper mapper = new ObjectMapper();
		mapper.getDeserializationConfig().set(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		ActivitiesFeed feed = mapper.readValue(new File("C:\\Users\\gabriel\\Desktop\\activities.txt"), ActivitiesFeed.class);

		System.out.println("ID: " + feed.getId());
		System.out.println("Updated: " + feed.getUpdated());
		System.out.println("Next page token: " + feed.getNextPageToken());
		
		System.out.println("\nActivities - " + feed.getActivities().size() + "\n");
		for(Activity a : feed.getActivities()) {
			System.out.println("ID: " + a.getId());
			System.out.println("Title: " + a.getTitle());
			System.out.println("Published: " + a.getPublished());
			System.out.println("Updated: " + a.getUpdated());
			System.out.println("Actor: " + a.getActor());
			System.out.println();
		}
	}

}
