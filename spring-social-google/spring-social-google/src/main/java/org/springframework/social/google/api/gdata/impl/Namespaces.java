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
package org.springframework.social.google.api.gdata.impl;

import nu.xom.XPathContext;

/**
 * GData XML namespaces
 * @author Gabriel Axel
 */
public abstract class Namespaces {

	public static final String AtomNamespace = "http://www.w3.org/2005/Atom";
	public static final String GdataNamespace = "http://schemas.google.com/g/2005";
	public static final String SearchNamespace = "http://a9.com/-/spec/opensearch/1.1/";
	public static final String ContactNamespace = "http://schemas.google.com/contact/2008";
	public static final String PicasaNamespace = "http://schemas.google.com/photos/2007";
	public static final String MediaNamespace = "http://search.yahoo.com/mrss/";
	
	public static final XPathContext NamespaceContext;
	
	static {
		NamespaceContext = new XPathContext();
		NamespaceContext.addNamespace("atom", AtomNamespace);
		NamespaceContext.addNamespace("gd", GdataNamespace);
		NamespaceContext.addNamespace("openSearch", SearchNamespace);
		NamespaceContext.addNamespace("gContact", ContactNamespace);
		NamespaceContext.addNamespace("gphoto", PicasaNamespace);
		NamespaceContext.addNamespace("media", MediaNamespace);
	}
}
