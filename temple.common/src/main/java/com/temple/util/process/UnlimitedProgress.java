package com.temple.util.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.temple.util.ToString;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UnlimitedProgress extends AbstractProgress {

	private static final long serialVersionUID = 1L;

	@ToString
	private long processed;

	public UnlimitedProgress() {
		this.processed = 0;
	}

	@Override
	public long getProcessed() {
		return this.processed;
	}

	@Override
	public void incrementProcessed(long unitAmount) {
		if (!this.isDone()) {
			this.processed += unitAmount;
		}
	}

}
