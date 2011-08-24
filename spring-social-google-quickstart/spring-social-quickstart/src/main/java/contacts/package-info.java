@XmlSchema(namespace = "http://www.w3.org/2005/Atom", elementFormDefault = XmlNsForm.QUALIFIED,
		xmlns={
        @XmlNs(prefix="gd", namespaceURI="http://schemas.google.com/g/2005")
})
@XmlAccessorType(XmlAccessType.FIELD)
package contacts;

import javax.xml.bind.annotation.*;