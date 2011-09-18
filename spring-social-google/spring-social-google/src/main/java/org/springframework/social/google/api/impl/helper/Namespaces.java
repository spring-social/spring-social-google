package org.springframework.social.google.api.impl.helper;

import nu.xom.XPathContext;

public abstract class Namespaces {

	public static final String AtomNamespace = "http://www.w3.org/2005/Atom";
	public static final String GdataNamespace = "http://schemas.google.com/g/2005";
	public static final String ContactNamespace = "http://schemas.google.com/contact/2008";
	
	public static final XPathContext NamespaceContext;
	
	static {
		NamespaceContext = new XPathContext();
		NamespaceContext.addNamespace("atom", AtomNamespace);
		NamespaceContext.addNamespace("gd", GdataNamespace);
		NamespaceContext.addNamespace("gContact", ContactNamespace);
	}
}
