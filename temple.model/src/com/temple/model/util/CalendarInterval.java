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
	
	@ToString
	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Calendar lowerLimit ;
	
	@ToString
	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	private Calendar upperLimit ;

	protected CalendarInterval() {}

	/**
	 * Constructor
	 * @param lowerLimit
	 * @param upperLimit 
	 */
	public CalendarInterval(Calendar lowerLimit, Calendar upperLimit) {
		Util.checkBoundaries(lowerLimit, upperLimit);
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}
	
	@Override
	public Calendar getLowerLimit() {
		return this.lowerLimit ;
	}

	@Override
	public Calendar getUpperLimit() {
		return this.upperLimit ;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this) ;
	}
	
}
