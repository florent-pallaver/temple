package com.temple.geo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.temple.util.Nameable;

/**
 * 
 * @author flominou
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Place implements Nameable {

	private int id ;
	
	private String name;

	protected Place() {
		super();
	}

	public Place(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
}
