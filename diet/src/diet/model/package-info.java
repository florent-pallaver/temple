@XmlSchema(
		namespace = "http://temple.com/diet", 
		elementFormDefault = XmlNsForm.QUALIFIED,
		xmlns = @XmlNs(
				prefix = "diet", 
				namespaceURI = "http://temple.com/diet"
				)
		)
@XmlAccessorType(XmlAccessType.FIELD)
package diet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
