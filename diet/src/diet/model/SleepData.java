package diet.model;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class SleepData {

	private int sleepDuration;
	
	@XmlTransient
	@Transient
	private int sleepQuality;

	public void set(SleepData data) {
		this.sleepDuration = data.sleepDuration;
		this.sleepQuality = data.sleepQuality;
	}
	
}
