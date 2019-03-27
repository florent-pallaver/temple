package diet.service;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DateBean {
	
	private int year;
	
	private int month; 
	
	private int day;
	
	public LocalDate getDate() {
		return LocalDate.of(year, month, day);
	}
}
