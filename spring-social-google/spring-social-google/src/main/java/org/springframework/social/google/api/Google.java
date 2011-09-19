package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.google.api.activity.ActivityOperations;
import org.springframework.social.google.api.contact.ContactOperations;
import org.springframework.social.google.api.legacyprofile.LegacyProfileOperations;
import org.springframework.social.google.api.profile.ProfileOperations;

public interface Google extends ApiBinding {

	LegacyProfileOperations userOperations();
	ContactOperations contactOperations();
	ProfileOperations profileOperations();
	ActivityOperations activityOperations();
}
