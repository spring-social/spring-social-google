/*
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.google.api.calendar.impl;

import java.text.MessageFormat;

import org.springframework.social.google.api.calendar.Event;
import org.springframework.social.google.api.calendar.QuickAddEventBuilder;
import org.springframework.util.Assert;

/**
 * {@link QuickAddEventBuilder} implementation.
 * 
 * @author Martin Wink
 */
public class QuickAddEventBuilderImpl extends UriQueryBuilderImpl<QuickAddEventBuilder, Event> implements QuickAddEventBuilder {
	
	public QuickAddEventBuilderImpl(String urlTemplate, String calendarId) {
		super(MessageFormat.format(urlTemplate, encode(calendarId)));
	}

	@Override
	public QuickAddEventBuilder text(String text) {
		Assert.notNull(text, "Text must not be null");
		return appendQueryParam("text", encode(text));
	}

	@Override
	public QuickAddEventBuilder sendNotifications(boolean sendNotifications) {
		return appendQueryParam("sendNotifications", sendNotifications);
	}
}
