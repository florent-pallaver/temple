package com.temple.geo.service.ejb.geonames;

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

import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.Geoname;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.service.GeoDataMigrater;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import com.temple.service.model.EntityException;
import com.temple.util.geo.Country;
import com.temple.util.process.LimitedProgress;

/**
 * TODO
 *
 * @author flominou
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Lock(LockType.READ)
public class GeonamesDataMigraterBean extends AbstractEJBBean implements GeoDataMigrater, Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private TimerService ts;

	@Inject
	@TempleObject
	private EntityManager em;

	private LimitedProgress progress = new LimitedProgress();

	@Override
	public void migrateData() {
		this.ts.createTimer(5000, null);
	}

	@Override
	public LimitedProgress getProgress() {
		return this.progress;
	}

	@Timeout
	void timeout(Timer timer) {
		this.info("migrating geo data");

		timer.cancel();

		this.info("cleaning AdministrativeDivisions and PopulatedPlaces ...");

		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaDelete<AdministrativeDivision> cd = cb.createCriteriaDelete(AdministrativeDivision.class);
		cd.from(AdministrativeDivision.class);
		this.em.createQuery(cd).executeUpdate();
		this.info("AdministrativeDivisions deleted");
		final CriteriaDelete<PopulatedPlace> cdpp = cb.createCriteriaDelete(PopulatedPlace.class);
		cdpp.from(PopulatedPlace.class);
		this.em.createQuery(cdpp).executeUpdate();
		this.info("PopulatedPlaces deleted");

		final Country[] countries = Country.values();
		this.progress = new LimitedProgress(countries.length);
		try {
			for (final Country c : countries) {
				this.info("migrating for %s", c.getName());
				this.doMigrateData(c);
				this.progress.incrementProcessed();
			}
		} catch (final Exception ex) {
			this.thrown(ex);
		} finally {
			this.progress.done();
		}
	}

	private void doMigrateData(Country c) throws EntityException {
		final GeonameFilter gf = new GeonameFilter();
		gf.setCountry(c);
		gf.setFeatures(Feature.ADM1);

		final List<? extends Geoname> adms = gf.createTypedQuery(this.em).getResultList();
		if (this.isDebugLoggable()) {
			this.debug("In " + c.getName() + " [" + c.ordinal() + "] " + adms.size() + " administrative divisions");
		}

		gf.setFeatures(PopulatedPlace.FEATURES);
		for (final Geoname g : adms) {
			final String ac1 = g.getAc1();
			if ("00".equals(ac1)) {
				this.warning("Invalid admin1_code for %s", g);
			} else {
				gf.setAdminCodes(ac1);
				final AdministrativeDivision ad = new AdministrativeDivision(g, null);
				gf.createTypedQuery(this.em).getResultList().forEach(pp -> new PopulatedPlace(pp, ad));
				if (this.isDebugLoggable()) {
					this.debug("%s has %d populated places", ad.getName(), ad.getPopulatedPlacesCount());
				}
				this.em.persist(ad);
				this.em.flush();
				this.em.clear();
			}
		}
		this.em.flush();
		this.em.clear();
	}

	@SuppressWarnings("unused")
	private static final class AdminCode implements Comparable<AdminCode> {

		private final Feature level;

		private final String[] ac;

		AdminCode(Geoname g) {
			this.level = g.getFeature();
			this.ac = new String[] { g.getAc1(), g.getAc2(), g.getAc3(), g.getAc4() };
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 41 * hash + this.level.hashCode();
			hash = 41 * hash + Arrays.deepHashCode(this.ac);
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof AdminCode)) {
				return false;
			}
			final AdminCode other = (AdminCode) obj;
			return this.level == other.level && Arrays.deepEquals(this.ac, other.ac);
		}

		@Override
		public int compareTo(AdminCode o) {
			int c = o == null ? 1 : this.level.compareTo(o.level);
			for (int i = 0; i < this.ac.length && c == 0; i++) {
				c = this.ac[i] == null ? o.ac[i] == null ? 0 : -1 : this.ac[i].compareTo(o.ac[i]);
			}
			return c;
		}

	}

}
