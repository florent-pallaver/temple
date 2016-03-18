/**
 *
 */
package com.temple.model.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.temple.util.Nameable;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.IntegerHandler;
import com.temple.util.json.JsonField;

/**
 * TODOC
 *
 * @author flominou
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class NamedEntity extends AbstractJsonable implements Nameable, Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	@JsonField(handler = IntegerHandler.class, inputable = false)
	@XmlAttribute(required = true)
	private final int id;

	@ToString
	@JsonField(inputable = false)
	@XmlAttribute(required = true)
	private final String name;

	protected NamedEntity() {
		this(0, null);
	}

	/**
	 *
	 * @param id
	 * @param name
	 */
	public NamedEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return 37 + this.id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof NamedEntity && this.id == ((NamedEntity) obj).id;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
