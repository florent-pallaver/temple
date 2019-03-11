package test.com.temple.util.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.temple.util.TempleUtil;
import com.temple.util.geo.Country;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.JsonField;

/**
 *
 * @author flominou
 */
public class JsonTest {

	private JsonObject o1;

	private JsonObject o2;

	@Before
	public void init() {
		this.o1 = new JsonObject();
		this.o2 = new JsonObject();
	}

	@Test
	public void testReadWrite() {
		this.o1.reset();
		final javax.json.JsonObject jo1 = this.o1.toJsonObject();
		Assert.assertNotEquals(this.o1, this.o2);
		this.o2.setValues(jo1);
		final javax.json.JsonObject jo2 = this.o2.toJsonObject();
		Assert.assertEquals(jo1, jo2);
	}

	private static final class JsonObject extends AbstractJsonable {

		@JsonField
		private int f1;

		@JsonField
		private int f2;

		@JsonField
		private boolean f3;

		@JsonField
		private boolean f4;

		@JsonField
		private double f5;

		@JsonField
		private Double f6;

		@JsonField
		private Boolean f7;

		@JsonField
		private Integer f8;

		@JsonField
		private Integer f9;

		@JsonField
		private Country c;

		@JsonField
		private String s0;

		@JsonField
		private String s1;

		@JsonField
		private String s2;

		@JsonField
		private Country c0;

		@JsonField
		private final Map<String, String> m = new HashMap<>();

		JsonObject() {
		}

		void reset() {
			this.f1 = TempleUtil.random(0, 5000000);
			this.f2 = TempleUtil.random(0, 5000000);
			this.f3 = true;
			this.f4 = false;
			this.f5 = TempleUtil.random(0, 5000000) * Math.PI;
			this.f6 = TempleUtil.random(-50000000L, 0L) * Math.E;
			this.f7 = null;
			this.f8 = null;
			this.f9 = 76;
			this.c = Country.France;
			this.s0 = null;
			this.s1 = "one string ...";
			this.s2 = "{another string : \"....\"}";
			this.c0 = null;
			this.m.put("one", Integer.toString(TempleUtil.random(0, 50000000)));
			this.m.put("two", Long.toString(TempleUtil.random(0, 500000000000L)));
			this.m.put("three", Double.toString(this.f5));
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (this.c == null ? 0 : this.c.hashCode());
			result = prime * result + (this.c0 == null ? 0 : this.c0.hashCode());
			result = prime * result + this.f1;
			result = prime * result + this.f2;
			result = prime * result + (this.f3 ? 1231 : 1237);
			result = prime * result + (this.f4 ? 1231 : 1237);
			long temp;
			temp = Double.doubleToLongBits(this.f5);
			result = prime * result + (int) (temp ^ temp >>> 32);
			result = prime * result + (this.f6 == null ? 0 : this.f6.hashCode());
			result = prime * result + (this.f7 == null ? 0 : this.f7.hashCode());
			result = prime * result + (this.f8 == null ? 0 : this.f8.hashCode());
			result = prime * result + (this.f9 == null ? 0 : this.f9.hashCode());
			result = prime * result + (this.m == null ? 0 : this.m.hashCode());
			result = prime * result + (this.s0 == null ? 0 : this.s0.hashCode());
			result = prime * result + (this.s1 == null ? 0 : this.s1.hashCode());
			result = prime * result + (this.s2 == null ? 0 : this.s2.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof JsonObject)) {
				return false;
			}
			final JsonObject other = (JsonObject) obj;
			if (this.c != other.c) {
				return false;
			}
			if (this.c0 != other.c0) {
				return false;
			}
			if (this.f1 != other.f1) {
				return false;
			}
			if (this.f2 != other.f2) {
				return false;
			}
			if (this.f3 != other.f3) {
				return false;
			}
			if (this.f4 != other.f4) {
				return false;
			}
			if (Double.doubleToLongBits(this.f5) != Double.doubleToLongBits(other.f5)) {
				return false;
			}
			if (this.f6 == null) {
				if (other.f6 != null) {
					return false;
				}
			} else if (!this.f6.equals(other.f6)) {
				return false;
			}
			if (this.f7 == null) {
				if (other.f7 != null) {
					return false;
				}
			} else if (!this.f7.equals(other.f7)) {
				return false;
			}
			if (this.f8 == null) {
				if (other.f8 != null) {
					return false;
				}
			} else if (!this.f8.equals(other.f8)) {
				return false;
			}
			if (this.f9 == null) {
				if (other.f9 != null) {
					return false;
				}
			} else if (!this.f9.equals(other.f9)) {
				return false;
			}
			if (this.m == null) {
				if (other.m != null) {
					return false;
				}
			} else if (!this.m.equals(other.m)) {
				return false;
			}
			if (this.s0 == null) {
				if (other.s0 != null) {
					return false;
				}
			} else if (!this.s0.equals(other.s0)) {
				return false;
			}
			if (this.s1 == null) {
				if (other.s1 != null) {
					return false;
				}
			} else if (!this.s1.equals(other.s1)) {
				return false;
			}
			if (this.s2 == null) {
				if (other.s2 != null) {
					return false;
				}
			} else if (!this.s2.equals(other.s2)) {
				return false;
			}
			return true;
		}

	}
}
