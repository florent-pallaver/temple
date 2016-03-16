package com.temple.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class TempleUtil {

	/**
	 * The value returned when a value hasn't been found in an array.
	 */
	public static final int VALUE_NOT_FOUND = -1;

	/**
	 * Constructor
	 */
	protected TempleUtil() {}

	/**
	 * TODOC
	 *
	 * @param str
	 * @return
	 */
	public static final String base64Encode(String str) {
		return TempleUtil.base64Encode(str.getBytes());
	}

	/**
	 * TODOC
	 *
	 * @param bytes
	 * @return
	 */
	public static final String base64Encode(byte[] bytes) {
		return DatatypeConverter.printBase64Binary(bytes);
	}

	/**
	 * TODOC
	 *
	 * @param str64
	 * @return
	 */
	public static final byte[] base64Decode(String str64) {
		return DatatypeConverter.parseBase64Binary(str64);
	}

	/**
	 * TODOC
	 *
	 * @param bytes64
	 * @return
	 */
	public static final byte[] base64Decode(byte[] bytes64) {
		return DatatypeConverter.parseBase64Binary(new String(bytes64));
	}

	/**
	 * @param min - the minimum value (included)
	 * @param max - the maximum value (excluded)
	 * @return a random value within the interval [ min ; max [
	 */
	public static final int random(int min, int max) {
		final int r = min + (int) Math.round(Math.random() * (max - min));
		return r == max ? min : r;
	}

	/**
	 * @param min - the minimum value (included)
	 * @param max - the maximum value (excluded)
	 * @return a random value within the interval [ min ; max [
	 */
	public static final long random(long min, long max) {
		// FIXME bug si on utilise les limites MAX and MIN
		final long r = min + Math.round(Math.random() * (max - min));
		return r == max ? min : r;
	}

	/**
	 * 
	 * @param array an array
	 * @return a random element from the given array
	 * @throws NullPointerException if given array is <code>null</code>
	 * @throws IllegalArgumentException if the given array is empty
	 */
	public static final <E> E random(E[] array) {
		if(array.length == 0) {
			throw new IllegalArgumentException("Empty array given.") ;
		}
		return array[TempleUtil.random(0, array.length)] ;
	}
	
	/**
	 * TODOC
	 *
	 * @param o
	 * @return
	 */
	public static int hashCode(Object o) {
		final int hashCode;
		if (o == null) {
			hashCode = 0;
		} else if (o.getClass().isArray()) {
			if (o instanceof Object[]) {
				hashCode = Arrays.hashCode((Object[]) o);
			} else if (o instanceof int[]) {
				hashCode = Arrays.hashCode((int[]) o);
			} else if (o instanceof char[]) {
				hashCode = Arrays.hashCode((char[]) o);
			} else if (o instanceof byte[]) {
				hashCode = Arrays.hashCode((byte[]) o);
			} else if (o instanceof long[]) {
				hashCode = Arrays.hashCode((long[]) o);
			} else if (o instanceof double[]) {
				hashCode = Arrays.hashCode((double[]) o);
			} else if (o instanceof boolean[]) {
				hashCode = Arrays.hashCode((boolean[]) o);
			} else if (o instanceof float[]) {
				hashCode = Arrays.hashCode((float[]) o);
			} else {
				hashCode = Arrays.hashCode((short[]) o);
			}
		} else {
			hashCode = o.hashCode();
		}
		return hashCode;
	}

	/**
	 * Compares two objects for equality.
	 * <p>
	 * Two objects a and b are equals if one of these condition is true :
	 * <ul>
	 * <li>they reference the same object : <code>a == b</code>,</li>
	 * <li>they are <code>null</code> : <code>a == null && b == null</code>,</li>
	 * <li>or they are equals according to the {@link Object#equals(Object) equals method} : <code>a.equals(b)</code></li>
	 * </ul>
	 * TODOC add the array case
	 *
	 * @param one
	 *            - one object.
	 * @param another
	 *            - another object.
	 * @return <code>true</code> if both objects are equals, <code>false</code> otherwise.
	 */
	public static boolean equals(Object o1, Object o2) {
		final boolean e;
		if (o1 == null) {
			e = o2 == null;
		} else {
			final Class<? extends Object> c1 = o1.getClass();
			if (c1.isArray()) {
				if (c1.equals(o2.getClass())) {
					if (o1 instanceof Object[]) {
						e = Arrays.deepEquals((Object[]) o1, (Object[]) o2);
					} else if (o1 instanceof int[]) {
						e = Arrays.equals((int[]) o1, (int[]) o2);
					} else if (o1 instanceof char[]) {
						e = Arrays.equals((char[]) o1, (char[]) o2);
					} else if (o1 instanceof byte[]) {
						e = Arrays.equals((byte[]) o1, (byte[]) o2);
					} else if (o1 instanceof long[]) {
						e = Arrays.equals((long[]) o1, (long[]) o2);
					} else if (o1 instanceof double[]) {
						e = Arrays.equals((double[]) o1, (double[]) o2);
					} else if (o1 instanceof boolean[]) {
						e = Arrays.equals((boolean[]) o1, (boolean[]) o2);
					} else if (o1 instanceof float[]) {
						e = Arrays.equals((float[]) o1, (float[]) o2);
					} else {
						e = Arrays.equals((short[]) o1, (short[]) o2);
					}
				} else {
					e = false;
				}
			} else {
				e = o1.equals(o2);
			}
		}
		return e;
	}

	/**
	 * Searches a value in an array and return it's first position in that array.
	 *
	 * @param a
	 *            - an array.
	 * @param v
	 *            - a value.
	 * @return the index of the given value in the given array, or {@link #VALUE_NOT_FOUND} if that value hasn't been
	 *         found.
	 */
	public static final int linearSearch(Object[] a, Object v) {
		final int l = a.length;
		int i = 0;
		for (; i < l && !TempleUtil.equals(a[i], v); i++) {}
		return i == l ? TempleUtil.VALUE_NOT_FOUND : i;
	}

	/**
	 * Checks if an array contains a given value.
	 *
	 * @param a
	 *            - an array.
	 * @param v
	 *            - a value.
	 * @return <code>true</code> if the given array contains the given value, <code>false</code> otherwise.
	 */
	public static final boolean contains(Object[] a, Object v) {
		return TempleUtil.linearSearch(a, v) != TempleUtil.VALUE_NOT_FOUND;
	}

	/**
	 * Shuffles elements of the given array.
	 *
	 * @param a
	 *            - an array of Object.
	 */
	public static final void shuffle(final Object[] a) {
		final Random r = new Random();
		final int l = a.length;
		for (int i = l; i-- > 0;) {
			final int ni = r.nextInt(l);
			final Object tmp = a[i];
			a[i] = a[ni];
			a[ni] = tmp;
		}
	}

	/**
	 * Shuffles elements of the given array.
	 *
	 * @param a
	 *            - an array of int.
	 */
	public static final void shuffle(final int[] a) {
		final Random r = new Random();
		final int l = a.length;
		for (int i = l; i-- > 0;) {
			final int ni = r.nextInt(l);
			final int tmp = a[i];
			a[i] = a[ni];
			a[ni] = tmp;
		}
	}

	/**
	 * Shuffles elements of the given array.
	 *
	 * @param a
	 *            - an array of short.
	 */
	public static final void shuffle(final short[] a) {
		final Random r = new Random();
		final int l = a.length;
		for (int i = l; i-- > 0;) {
			final int ni = r.nextInt(l);
			final short tmp = a[i];
			a[i] = a[ni];
			a[ni] = tmp;
		}
	}

	/**
	 * TODOC
	 *
	 * @param e
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static final Annotation[] getAnnotations(Enum<?> e) throws NullPointerException, IllegalArgumentException {
		if (e == null) {
			throw new NullPointerException();
		}
		try {
			return e.getClass().getField(e.name()).getAnnotations();
		} catch (final Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	/**
	 * TODOC
	 *
	 * @param o
	 * @param f
	 * @return
	 * @throws RuntimeException
	 */
	protected static final Object get(Object o, AccessibleObject f) {
		final Object object;
		try {
			final boolean accessible = f.isAccessible();
			if (!accessible) {
				f.setAccessible(true);
			}
			object = f instanceof Method ? ((Method) f).invoke(o) : ((Field) f).get(o);
			if (!accessible) {
				f.setAccessible(false);
			}
		} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
			// should never happen !
			throw new RuntimeException(e);
		}
		return object;
	}

	/**
	 * TODOC
	 *
	 * @param o
	 * @param f
	 * @return
	 * @throws RuntimeException
	 */
	protected static final void set(Object o, AccessibleObject f, Object v) {
		try {
			final boolean accessible = f.isAccessible();
			if (!accessible) {
				f.setAccessible(true);
			}
			if (f instanceof Method) {
				((Method) f).invoke(o, v);
			} else {
				((Field) f).set(o, v);
			}
			if (!accessible) {
				f.setAccessible(false);
			}
		} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
			// should never happen !
			throw new RuntimeException(e);
		}
	}

	/**
	 * TODOC
	 *
	 * @param o
	 * @return
	 */
	public static final String toString(Object o) {
		final StringBuilder sb = new StringBuilder(o.getClass().getName()) ;
		final AnnotatedField<ToString>[] fields = Lazy.toStringFieldsCache.getFields(o.getClass());
		final List<?> values = Arrays.stream(fields).parallel().map(field -> {
				final Object value = TempleUtil.getString(o, field.field);
				return field.annotation.renderIfNull() || value != null ? field.field.getName() + ':'  + value : null ;
			}).filter(s -> s != null).collect(Collectors.toList()) ;
		return sb.append(values).toString();
	}
	
	private static Object getString(Object o, Field f) {
		final Class<?> type = f.getType();
		final Object value = TempleUtil.get(o, f);
		return type.isArray() && !type.getComponentType().isPrimitive() ? Arrays.toString((Object[]) value) : value;
	}

	private static final class Lazy {

		static final AnnotatedFieldCache<ToString> toStringFieldsCache = new AnnotatedFieldCache<>(ToString.class);
	}
	/* public static final boolean equals(Object... objects) { boolean e = true; int l = objects.length; if(l > 0) {
	 * Object r = objects[0]; for(int i = l; e &&
	 * i-- > 1;) { e = equals(r, objects[i]); } } return e; } */
	// test linearSearch
	// public static void main(String[] args) {
	// Object[] a = {1, 2, 3, 4, 5};
	// System.out.println(linearSearch(a, 0));
	// System.out.println(linearSearch(a, 1));
	// System.out.println(linearSearch(a, 3));
	// System.out.println(linearSearch(a, 5));
	// System.out.println(linearSearch(a, 6));
	//
	// Object o = new Object(), o1 = new Object(), o2 = new Object();
	// Object[] b = {o, o1, o2};
	// System.out.println(linearSearch(b, null));
	// System.out.println(linearSearch(b, o));
	// System.out.println(linearSearch(b, o1));
	// System.out.println(linearSearch(b, o2));
	// System.out.println(linearSearch(b, new Object()));
	//
	// Object[] c = {} ;
	// System.out.println(linearSearch(c, null)) ;
	// System.out.println(linearSearch(c, new Object())) ;
	//
	// }
}
