package com.temple.model.geo.geonames;

import com.temple.model.geo.HumanSettlementEntity;
import static com.temple.model.geo.geonames.Feature.PPL;
import static com.temple.model.geo.geonames.Feature.PPLA;
import static com.temple.model.geo.geonames.Feature.PPLA2;
import static com.temple.model.geo.geonames.Feature.PPLA3;
import static com.temple.model.geo.geonames.Feature.PPLA4;
import static com.temple.model.geo.geonames.Feature.PPLC;
import static com.temple.model.geo.geonames.Feature.PPLF;
import static com.temple.model.geo.geonames.Feature.PPLG;
import static com.temple.model.geo.geonames.Feature.PPLL;
import static com.temple.model.geo.geonames.Feature.PPLR;
import static com.temple.model.geo.geonames.Feature.PPLS;
import static com.temple.model.geo.geonames.Feature.PPLX;
import static com.temple.model.geo.geonames.Feature.STLMT;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TODOC
 * @author flominou
 */
@Entity
@Table(name = "populated_places")
public class PopulatedPlace extends AbstractCountryArea implements HumanSettlementEntity {
	
	private static final long serialVersionUID = 1L ;

	/**
	 * Cities, towns, villages, etc
	 */
	public static final Feature[] FEATURES = {PPLC, PPLG, PPLA, PPLA2, PPLA3, PPLA4, PPL, PPLL, PPLF, PPLR, PPLS, PPLX, STLMT} ;

	protected PopulatedPlace() {}

	/**
	 * Constructor.
	 * TODOC
	 * @param g
	 * @param parentDivision not null
	 */
	public PopulatedPlace(Geoname g, AdministrativeDivision parentDivision) {
		super(g, Objects.requireNonNull(parentDivision));
		parentDivision.add(this);
	}
	
	@Override
	public boolean isCapital() {
		return this.feature.equals(Feature.PPLC);
	}

	@Override
	public boolean isSeat() {
		return this.feature.equals(PPLA) || this.feature.equals(PPLA2) || this.feature.equals(PPLA3) || this.feature.equals(PPLA4) || PPLG.equals(this.feature) || this.isCapital() ;
	}

	/**
	 * Comparator to order {@link PopulatedPlace}s by administrative importance.
	 * <br>
	 * It does not accept any null argument.
	 */
	public static final class ByImportanceComparator implements Comparator<PopulatedPlace> {

		/**
		 * Sole instance
		 */
		public static final Comparator<PopulatedPlace> instance = new ByImportanceComparator() ;
		
		private static final Map<Feature, Integer> orders = new HashMap<>(FEATURES.length) ;
 		
		private ByImportanceComparator() {}
		
		@Override
		public int compare(PopulatedPlace o1, PopulatedPlace o2) {
			Objects.requireNonNull(o1) ;
			Objects.requireNonNull(o2) ;
			return orders.get(o1.feature).compareTo(orders.get(o2.feature)) ;
		}
	
		static {
			for(int l = FEATURES.length, i = 0 ; l -->0 ; ) {
				orders.put(FEATURES[l], i++) ;
			}
		}
		
	
}

}
