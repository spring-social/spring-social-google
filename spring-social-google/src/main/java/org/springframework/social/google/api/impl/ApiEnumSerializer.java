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
package org.springframework.social.google.api.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Jackson {@link JsonSerializer} that converts an enum name to camel case
 * string.
 * 
 * @author Gabriel Axel
 */
public class ApiEnumSerializer extends JsonSerializer<Enum<?>> {

	public static String enumToString(Enum<?> value) {

		if(value == null) {
			return null;
		}
		
		String underscored = value.name();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < underscored.length(); i++) {
			char c = underscored.charAt(i);
			if (c == '_') {
				sb.append(Character.toUpperCase(underscored.charAt(++i)));
			} else {
				sb.append(Character.toLowerCase(c));
			}
		}
		return sb.toString();
	}

	@Override
	public void serialize(Enum<?> value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		jgen.writeString(enumToString(value));
	}

}
