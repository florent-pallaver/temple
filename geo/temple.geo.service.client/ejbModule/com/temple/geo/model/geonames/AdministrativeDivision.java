package com.temple.geo.model.geonames;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.temple.geo.model.CountryDivisionEntity;

/**
 * TODOC
 * @author flominou
 */
@Entity
@Table(name = "admin_divisions")
public class AdministrativeDivision extends AbstractCountryArea implements CountryDivisionEntity {

	private static final long serialVersionUID = 1L ;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parentDivision")
	private List<AdministrativeDivision> divisions ;
	
	@Column(nullable = false)
	private int divisionsCount ;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parentDivision")
	private List<PopulatedPlace> places ;
	
	@Column(nullable = false)
	private int placesCount ;
	
	protected AdministrativeDivision() {
		super() ;
	}

	public AdministrativeDivision(Geoname g, AdministrativeDivision parent) {
		super(g, parent) ;
		// FIXME do a better process !
		// builds children list for migration only
		this.divisionsCount = 0 ;
		this.placesCount = 0 ;
		this.divisions = new ArrayList<>() ;
		this.places = new ArrayList<>() ;
		if(parent != null) {
			parent.add(this);
		}
	}

	public int getAdministrativeDivisionsCount() {
		return divisionsCount;
	}

	public int getPopulatedPlacesCount() {
		return placesCount;
	}
	
	public List<AdministrativeDivision> getDivisions() {
		return divisions;
	}
	
	private void add(AdministrativeDivision ad) {
		this.divisions.add(ad) ;
		this.divisionsCount++ ;
	}
	
	void add(PopulatedPlace pp) {
		this.places.add(pp) ;
		this.placesCount++ ;
	}
	
}
