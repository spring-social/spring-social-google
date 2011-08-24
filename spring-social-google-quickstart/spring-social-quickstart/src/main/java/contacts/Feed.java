package contacts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feed {
	
	@XmlElement(name="entry")
	private ArrayList<Contact> entries;
	
	public List<Contact> getEntries() {
		return entries;
	}
}
