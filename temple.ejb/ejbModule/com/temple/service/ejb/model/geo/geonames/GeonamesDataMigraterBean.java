package com.temple.service.ejb.model.geo.geonames;

import com.temple.model.geo.geonames.AdministrativeDivision;
import com.temple.model.geo.geonames.Feature;
import com.temple.model.geo.geonames.Geoname;
import com.temple.model.geo.geonames.PopulatedPlace;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import com.temple.service.model.EntityException;
import com.temple.service.model.geo.GeoDataMigrater;
import com.temple.util.geo.Country;
import com.temple.util.process.Progress;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

/**
 * TODO
 *
 * @author flominou
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Lock(LockType.READ)
public class GeonamesDataMigraterBean extends AbstractEJBBean implements GeoDataMigrater, Serializable {

	private static final long serialVersionUID = 1L ;
	
	@Resource
	private TimerService ts;

	@Inject
	@TempleObject
	private EntityManager em ;
	
	private Progress progress = new Progress() ;
	
	@Override
	public void migrateData() {
		this.ts.createTimer(5000, null);
	}

	@Override
	public Progress getProgress() {
		return this.progress ;
	}
	
	@Timeout
	void timeout(Timer timer) {
		info("migrating geo data") ;

		timer.cancel();
		
		CriteriaBuilder cb = this.em.getCriteriaBuilder() ;
		final CriteriaDelete<AdministrativeDivision> cd = cb.createCriteriaDelete(AdministrativeDivision.class);
		cd.from(AdministrativeDivision.class) ;
		this.em.createQuery(cd).executeUpdate() ;
		final CriteriaDelete<PopulatedPlace> cdpp = cb.createCriteriaDelete(PopulatedPlace.class);
		cdpp.from(PopulatedPlace.class) ;
		this.em.createQuery(cdpp).executeUpdate() ;

		final Country[] countries = Country.values();
		this.progress = new Progress(countries.length) ;
		try {
			for(Country c : countries) {
				this.doMigrateData(c) ;
				this.progress.incrementProcessed();
			}
		} catch (Exception ex) {
			this.thrown(ex);
		} finally {
			this.progress.done();
		}
	}
	
	private void doMigrateData(Country c) throws EntityException {
		final GeonameFilter gf = new GeonameFilter();
		gf.setCountry(c);
		gf.setFeatures(Feature.ADM1);
		
		final List<? extends Geoname> adms = gf.createTypedQuery(em).getResultList() ;
		if(isDebugLoggable()) {
			this.debug("In " + c.getName() + " [" + c.ordinal() + "] " + adms.size() + " administrative divisions" ) ;
		}
		
		gf.setFeatures(PopulatedPlace.FEATURES);
		for(Geoname g : adms) {
			final String ac1 = g.getAc1() ;
			if("00".equals(ac1)) {
				this.warning("Invalid admin1_code for " + g) ;
			} else {
				gf.setAdminCodes(ac1);
				final AdministrativeDivision ad = new AdministrativeDivision(g, null) ;
				gf.createTypedQuery(em).getResultList().forEach(pp -> new PopulatedPlace(pp, ad)) ;
				if(isDebugLoggable()) {
					this.debug(ad.getName() + " has " + ad.getPopulatedPlacesCount() + " populated places") ;
				}
				this.em.persist(ad);
				this.em.flush();
				this.em.clear();
			}
		}
		
		this.em.flush();
		this.em.clear();
	}
	
	private static final class AdminCode implements Comparable<AdminCode>{

		private final Feature level ;
		
		private final String[] ac ;

		AdminCode(Geoname g) {
			this.level = g.getFeature() ;
			this.ac = new String[]{g.getAc1(), g.getAc2(), g.getAc3(), g.getAc4()} ;
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 41 * hash + this.level.hashCode() ;
			hash = 41 * hash + Arrays.deepHashCode(this.ac);
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof AdminCode)) {
				return false;
			}
			final AdminCode other = (AdminCode) obj;
			return this.level == other.level && Arrays.deepEquals(this.ac, other.ac) ;
		}
		
		@Override
		public int compareTo(AdminCode o) {
			int c = o == null ? 1 : this.level.compareTo(o.level) ;
			for(int i = 0 ; i < this.ac.length && c == 0 ; i++) {
				c = this.ac[i] == null ? (o.ac[i] == null ? 0 :  -1) : this.ac[i].compareTo(o.ac[i])  ;
			}
			return c ;
		}
		
	}

}
