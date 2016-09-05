package com.temple.util.process;

import java.util.Date;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.json.JsonField;

public abstract class AbstractProgress implements Progress {

	private static final long serialVersionUID = 1L ;

	@ToString
	@JsonField
	private final Date startDate ;

	@ToString
	@JsonField
	private Date endTime ;

	protected AbstractProgress() {
		this.startDate = new Date() ;
		this.endTime = null ;
	}

	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	@Override
	public boolean isDone() {
		return this.endTime != null;
	}

	@Override
	public void done() {
		if(!this.isDone()) {
			this.endTime = new Date() ;
		}
	}

	@Override
	public int getProcessingTime() {
		final Date asOf = this.isDone() ? this.endTime : new Date() ;
		return (int) ((asOf.getTime() - this.startDate.getTime()) / 1000) ;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}

}
