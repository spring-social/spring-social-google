package contacts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feed {
	
	public static final String GD_NAMESPACE = "http://schemas.google.com/g/2005";

	@XmlElement(name="entry")
	private ArrayList<Contact> entries;
	
	public List<Contact> getEntries() {
		return entries;
	}
}
