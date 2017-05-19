/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.social.google.config.support;

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

    private GoogleApiHelper(
      final UsersConnectionRepository usersConnectionRepository,
      final UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    public Google getApi() {
        if (logger.isDebugEnabled()) {
            logger.debug("Getting API binding instance for Google provider");
        }

        final Connection<Google> connection = usersConnectionRepository
            .createConnectionRepository(userIdSource.getUserId())
            .findPrimaryConnection(Google.class);
        if (logger.isDebugEnabled() && connection == null) {
            logger.debug("No current connection; Returning default GoogleTemplate instance.");
        }
        return connection != null ? connection.getApi() : null;
    }

    private final static Log logger = LogFactory.getLog(GoogleApiHelper.class);

}
