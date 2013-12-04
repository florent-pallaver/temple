package com.temple.model.geo;

import com.temple.model.TempleEntity;
import com.temple.util.geo.GeoLocatable;

/**
 * Base contract to define geographic entity.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface GeoEntity extends TempleEntity, GeoLocatable {}
