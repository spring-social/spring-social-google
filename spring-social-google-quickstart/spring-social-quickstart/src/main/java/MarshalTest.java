import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;


import contacts.Contact;
import contacts.Feed;


public class MarshalTest {

	public static void main(String[] args) throws Exception {

		InputStream in = new FileInputStream("C:\\Users\\gabriel\\Dropbox\\contacts.xml");
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
		
		Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
		unmarshaller.setUnmarshallerProperties(properties);
		
		unmarshaller.setClassesToBeBound(Feed.class, Contact.class);
		
		Feed feed = (Feed)unmarshaller.unmarshal(new StreamSource(in));
		
		System.out.println("Size: " + feed.getEntries().size());
		for(Contact contact : feed.getEntries()) {
			System.out.println(contact.getEmail());
		}
	}

}
