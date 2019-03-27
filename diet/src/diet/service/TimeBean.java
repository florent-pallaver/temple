package diet.service;

import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeBean {
	
	private int hour;
	
	private int minute;
	
	public LocalTime getTime() {
		return LocalTime.of(hour, minute);
	}
}
