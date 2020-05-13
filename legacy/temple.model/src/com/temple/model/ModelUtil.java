package com.temple.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import com.temple.Module;
import com.temple.util.TempleUtil;

/**
 * Utility class for entities.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class ModelUtil extends TempleUtil {

	private ModelUtil() {}

	/**
	 * @return the current {@link Timestamp}.
	 */
	public static final Timestamp currentTimestamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * TODOC
	 * 
	 * @param s
	 * @param maxLength
	 * @param fieldName
	 * @return
	 */
	public static final String shrink(String s, int maxLength, String fieldName) {
		final String ss;
		if (s != null && s.length() > maxLength) {
			ss = s.substring(0, maxLength);
			if (Module.MODEL.logger.isLoggable(Level.FINEST)) {
				Module.MODEL.logger.finest(new StringBuilder("Shrinking ").append(fieldName).append(" from \"").append(s).append("\" to \"").append(ss)
						.append('"').toString());
			}
		} else {
			ss = s;
		}
		return ss;
	}

	/**
	 * TODOC
	 * 
	 * @param list
	 * @return
	 */
	public static final <E> List<E> protect(List<E> list) {
		return Collections.unmodifiableList(list);
	}

	/**
	 * TODOC
	 * 
	 * @param set
	 * @return
	 */
	public static final <E> Set<E> protect(Set<E> set) {
		return Collections.unmodifiableSet(set);
	}

	/**
	 * Tells of a {@link User} owns a {@link Resource}.
	 * 
	 * @param r - a {@link Resource}.
	 * @param u - a {@link User}.
	 * @return <code>true</code> if the given {@link User} owns the given {@link Resource}, <code>false</code>
	 *         otherwise.
	 * @deprecated TODEL !!!!!
	 */
	@Deprecated
	public static final boolean isOwnedBy(Resource<?> r, User<?, ?> u) {
		return TempleUtil.equals(u, r.getOwner());
	}
	/**
	 * Tells if a {@link Resource} is a member of a {@link Group}.
	 * 
	 * @param r - a {@link Resource}.
	 * @param g - a {@link Group}.
	 * @return <code>true</code> if the given {@link Resource} is member of the given {@link Group}.
	 */
	// public static final boolean isMemberOf(Resource<?> r, Group<?> g) {
	// return TempleUtil.equals(r.getPrimaryGroup(), g) || r instanceof User && ((User<?, ?>)
	// r).getSecondaryGroups().contains(g);
	// }
	/**
	 * Tells if two resources have at least one group in common.
	 * 
	 * @param r0 - one {@link Resource}.
	 * @param r1 - another {@link Resource}.
	 * @return <code>true</code> if the two {@link Resource}s have at least one group in common, <code>false</code>
	 *         otherwise.
	 */
	// public static final boolean haveOneGroupInCommon(Resource<?> r0, Resource<?> r1) {
	// final Group<?> g0 = r0.getPrimaryGroup();
	// final Group<?> g1 = r1.getPrimaryGroup();
	// boolean b = TempleUtil.equals(g0, g1);
	// if (!b) {
	// final boolean u1 = r1 instanceof User;
	// if (r0 instanceof User) {
	// final Set<?> groups0 = ((User<?, ?>) r0).getSecondaryGroups();
	// b = groups0.contains(g1);
	// if (!b && u1) {
	// final Set<?> groups1 = ((User<?, ?>) r1).getSecondaryGroups();
	// b = groups1.contains(g0) || Collections.disjoint(groups0, groups1);
	// }
	// } else {
	// b = u1 && ((User<?, ?>) r1).getSecondaryGroups().contains(g0);
	// }
	// }
	// return b;
	// }
}
