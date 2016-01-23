package com.temple.geo.service.ejb.geonames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import com.temple.geo.model.geonames.Geoname;
import com.temple.geo.service.GeoDataImporter;
import com.temple.geo.service.ImportInProgressException;
import com.temple.geo.service.ImportResult;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import com.temple.util.Charsets;
import com.temple.util.file.Content;

/**
 *
 * @author flominou
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Lock(LockType.READ)
public class GeonamesDataImporterBean extends AbstractEJBBean implements GeoDataImporter  {

	private static final long serialVersionUID = 1L;

	@Resource
	private TimerService ts ;

	@Inject
	@TempleObject
	private EntityManager em;

	private ImportResult importResult = new ImportResult() ;

	private Queue<Content> updates = new LinkedList<>() ;

	@SuppressWarnings("unused")
	private List<String> updatesFailures = new ArrayList<>() ;

	private boolean importing = false ;

	private final int flushLimit = 2500 ;

	@Override
	@Lock(LockType.WRITE)
	public void importData(Content data, long limit) throws ServiceException {
		// TODO throw exception if already importing
		if(this.importing) {
			throw new ImportInProgressException() ;
		}
		this.info("5 seconds before importing") ;
		this.importing = true ;
		this.ts.createTimer(5000, new Serializable[]{data, limit}) ;
		this.importResult = new ImportResult() ;
	}

	@Timeout
	void doImportData(Timer timer) {
		final Content c = (Content)((Serializable[])timer.getInfo())[0] ;
		final long limit = (Long)((Serializable[])timer.getInfo())[1] ;
		timer.cancel();
		this.info("starting importing data") ;
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaDelete<Geoname> d = cb.createCriteriaDelete(Geoname.class);
		d.from(Geoname.class);
		try {
			this.em.createQuery(d).executeUpdate();
		} catch (final Exception e) {
			this.thrown(e);
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), Charsets.utf8))) {
			this.algo2(br, limit);
		} catch (final Exception e) {
			// TODO check if legit !
			this.thrown(e);
		} finally {
			this.importResult.done();
			this.importing = false ;
		}
	}
	private final Consumer<? super String> name = s -> {
		try {
			final Geoname g = new Geoname(s) ;
			this.em.persist(g);
			this.importResult.incrementProcessed();
		} catch (final Exception e) {
			this.importResult.addFailedEntry(s, e);
		}
	};

	@SuppressWarnings("unused")
	private void algo1(BufferedReader br, long limit) throws IOException {
		Stream<String> lines = br.lines() ;
		if (limit > 0) {
			lines = lines.limit(limit);
		}
		// FIXME ? semble causé du GC à fond après éxécution
		lines.forEach(this.name);
	}

	private void algo2(BufferedReader br, long limit) throws IOException {
		String s ;
		//		this.em.setFlushMode(FlushModeType.COMMIT);
		for(long l = 0 ; this.importing && (limit == 0 || l < limit) && (s = br.readLine()) != null ; l++ ) {
			// flush from time to time to relieve the em !
			if(l % this.flushLimit == 0) {
				//				info("flushing entities ... " + this.importResult.getProcessedEntries() + " processed so far") ;
				this.em.flush();
				this.em.clear();
			}
			this.name.accept(s);
		}
	}

	@Override
	public ImportResult getProgress() {
		return this.importResult ;
	}

	@Schedule(hour = "*/6", persistent = false)
	void getUpdates() {
		this.info("Getting updates ...");
	}

	public void addUpdate(Content c) {
		this.updates.add(c) ;
	}

}
