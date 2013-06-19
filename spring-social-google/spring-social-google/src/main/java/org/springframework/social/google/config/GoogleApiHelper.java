package org.springframework.social.google.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.google.api.Google;

public class GoogleApiHelper implements ApiHelper<Google> {
	
	private final UsersConnectionRepository usersConnectionRepository;

	private final UserIdSource userIdSource;

	private GoogleApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.userIdSource = userIdSource;		
	}

	public Google getApi() {
		if (logger.isDebugEnabled()) {
			logger.debug("Getting API binding instance for Google provider");
		}
				
		Connection<Google> connection = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId()).findPrimaryConnection(Google.class);
		if (logger.isDebugEnabled() && connection == null) {
			logger.debug("No current connection; Returning default GoogleTemplate instance.");
		}
		return connection != null ? connection.getApi() : null;
	}
	
	private final static Log logger = LogFactory.getLog(GoogleApiHelper.class);

}