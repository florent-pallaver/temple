package com.temple.model.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.temple.util.Nameable;

/**
 * TODOC
 * @author flominou
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Named implements Nameable, Serializable {

	private static final long serialVersionUID = 1L;

	private int id ;

	private String name;

	protected Named() {
		super();
	}

	public Named(int id, String name) {
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

}
