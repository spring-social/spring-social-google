package contacts;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement
public class Contact {

	private String id;
	
	@XmlPath("title/text()")
	private String name;
	
	@XmlPath("gd:email[@primary='true']/@address")
	private String email;
	
	@XmlPath("link[contains(@rel,'photozzz')]/@href")
	private String pictureUrl;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}
}
