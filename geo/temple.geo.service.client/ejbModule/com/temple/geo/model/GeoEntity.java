package com.temple.geo.model;

import com.temple.model.TempleEntity;
import com.temple.util.geo.GeoLocatable;
import com.temple.util.json.Jsonable;

/**
 * Base contract to define geographic entity.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface GeoEntity extends TempleEntity, Jsonable, GeoLocatable {

}
