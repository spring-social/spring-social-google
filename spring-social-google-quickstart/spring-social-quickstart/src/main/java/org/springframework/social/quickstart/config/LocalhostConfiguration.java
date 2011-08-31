package org.springframework.social.quickstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("localhost")
@PropertySource("classpath:org/springframework/social/quickstart/config/localhost.properties")
public class LocalhostConfiguration {

}
