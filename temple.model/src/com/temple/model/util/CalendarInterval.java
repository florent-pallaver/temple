package com.temple.model.util;

import com.temple.util.Interval;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODOC
 * @author flominou
 * @version 1.0
 */
@Embeddable
public class CalendarInterval implements Interval<Calendar>, Serializable {
	
	private static final long serialVersionUID = 1L;

	@ToString
	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Calendar beginning ;
	
	@ToString
	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Calendar end ;

	protected CalendarInterval() {}

	/**
	 * Constructor
	 * @param beginning
	 * @param end 
	 */
	public CalendarInterval(Calendar beginning, Calendar end) {
		Util.checkBoundaries(beginning, end);
		this.beginning = beginning;
		this.end = end;
	}
	
	@Override
	public Calendar getLowerLimit() {
		return this.beginning ;
	}

	@Override
	public Calendar getUpperLimit() {
		return this.end ;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this) ;
	}
	
}
