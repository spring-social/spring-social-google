package org.springframework.social.quickstart.config;

import org.cloudfoundry.runtime.env.ApplicationInstanceInfo;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	public void initialize(ConfigurableApplicationContext ctx) {
		
		ConfigurableEnvironment environment = ctx.getEnvironment();

		ApplicationInstanceInfo instanceInfo = new CloudEnvironment().getInstanceInfo();

		if (instanceInfo == null) {
			environment.setActiveProfiles("localhost");
		} else {
			environment.setActiveProfiles("cloudfoundry");
		}
	}

}
