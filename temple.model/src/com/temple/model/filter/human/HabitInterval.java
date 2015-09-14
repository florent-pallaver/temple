package com.temple.model.filter.human;

import com.temple.util.Interval;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.human.Habit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author flominou
 */
@Embeddable
public class HabitInterval implements Interval<Habit>, Serializable {

	private static final long serialVersionUID = 1L ;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@ToString
	private Habit lowerLimit ;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@ToString
	private Habit upperLimit ;
	
	protected HabitInterval() {}
	
	/**
	 * 
	 * @param lower
	 * @param upper
	 * @throws IllegalArgumentException 
	 */
	public HabitInterval(Habit lower, Habit upper) throws IllegalArgumentException {
		Util.checkBoundaries(lower, upper);
		this.lowerLimit = lower ;
		this.upperLimit = upper ;
	}

	@Override
	public Habit getLowerLimit() {
		return this.lowerLimit ;
	}

	@Override
	public Habit getUpperLimit() {
		return this.upperLimit ;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this) ;
	}
	
}
