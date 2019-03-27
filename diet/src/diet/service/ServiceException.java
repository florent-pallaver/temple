package diet.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	// 4 JAX-B
	protected ServiceException() {}
	
	public ServiceException(Throwable cause, String format, Object... params) {
		super(String.format(format, params), cause);
	}

	public ServiceException(String format, Object... params) {
		super(String.format(format, params));
	}

	@XmlElement
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
