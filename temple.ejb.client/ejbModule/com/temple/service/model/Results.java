package com.temple.service.model;

import java.io.Serializable;
import java.util.List;

import com.temple.model.TempleEntity;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Results<E extends TempleEntity> extends Serializable {

	/**
	 * @return the entities found.
	 */
	List<E> getAll();
}
