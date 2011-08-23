package org.springframework.social.google.api;

import org.springframework.social.ApiBinding;

import com.google.api.services.tasks.Tasks;

public interface Google extends ApiBinding {

	UserOperations userOperations();
	ContactOperations contactOperations();
	Tasks getTaskOperations();
}
