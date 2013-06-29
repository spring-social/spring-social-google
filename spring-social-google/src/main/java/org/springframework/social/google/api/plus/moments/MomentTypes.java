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
package org.springframework.social.google.api.plus.moments;

/**
 * Constants for the available moment types
 * 
 * @author Gabriel Axel
 * 
 */
interface MomentTypes {

	String ADD_ACTIVITY = "http://schemas.google.com/AddActivity";
	String BUY_ACTIVITY = "http://schemas.google.com/BuyActivity";
	String CHECK_IN_ACTIVITY = "http://schemas.google.com/CheckInActivity";
	String COMMENT_ACTIVITY = "http://schemas.google.com/CommentActivity";
	String CREATE_ACTIVITY = "http://schemas.google.com/CreateActivity";
	String DISCOVER_ACTIVITY = "http://schemas.google.com/DiscoverActivity";
	String LISTEN_ACTIVITY = "http://schemas.google.com/ListenActivity";
	String RESERVE_ACTIVITY = "http://schemas.google.com/ReserveActivity";
	String REVIEW_ACTIVITY = "http://schemas.google.com/ReviewActivity";
	String WANT_ACTIVITY = "http://schemas.google.com/WantActivity";
}
