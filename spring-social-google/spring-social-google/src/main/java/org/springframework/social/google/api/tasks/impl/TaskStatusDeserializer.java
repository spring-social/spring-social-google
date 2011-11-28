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
package org.springframework.social.google.api.tasks.impl;

import org.codehaus.jackson.map.annotate.JsonCachable;
import org.springframework.social.google.api.impl.ApiEnumDeserializer;
import org.springframework.social.google.api.tasks.TaskStatus;

/**
 * {@link ApiEnumDeserializer} for {@link TaskStatus}
 * @author Gabriel Axel
 */
@JsonCachable
public class TaskStatusDeserializer extends ApiEnumDeserializer<TaskStatus> {

	public TaskStatusDeserializer() {
		super(TaskStatus.class);
	}

}
