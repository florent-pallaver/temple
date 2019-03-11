package com.temple.util.process;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractProgress implements Progress {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Date startDate;

	@ToString
	private Date endTime;

	protected AbstractProgress() {
		this.startDate = new Date();
		this.endTime = null;
	}

	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	@XmlElement
	@Override
	public boolean isDone() {
		return this.endTime != null;
	}

	@Override
	public void done() {
		if (!this.isDone()) {
			this.endTime = new Date();
		}
	}

	@XmlElement
	@Override
	public int getProcessingTime() {
		final Date asOf = this.isDone() ? this.endTime : new Date();
		return (int) ((asOf.getTime() - this.startDate.getTime()) / 1000);
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}

	public static class Adapter extends XmlAdapter<AbstractProgress, Progress> {

		@Override
		public Progress unmarshal(AbstractProgress v) throws Exception {
			return v;
		}

		@Override
		public AbstractProgress marshal(Progress v) throws Exception {
			return (AbstractProgress) v;
		}
	}

}
