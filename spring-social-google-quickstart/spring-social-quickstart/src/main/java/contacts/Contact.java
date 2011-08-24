package contacts;

import static contacts.Feed.GD_NAMESPACE;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement
public class Contact {

	private String id;
	
	@XmlElement(name="title")
	private String name;
	
	@XmlPath("email/@address")
	@XmlElement(namespace=GD_NAMESPACE)
	private String email;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
}
