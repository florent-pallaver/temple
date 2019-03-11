package com.temple.util.geo;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class CountryRep implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlValue
	public final String key;

	@XmlAttribute
	public final String name;

	protected CountryRep() {
		this(null, null);
	}

	public CountryRep(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.key);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CountryRep && Objects.equals(((CountryRep) obj).key, this.key);
	}

}
