package com.temple.service.model;

import com.temple.model.TempleEntity;
import java.io.Serializable;
import java.util.List;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Deprecated
public interface Results<E extends TempleEntity> extends Serializable {

	/**
	 * @return the entities found.
	 */
	List<E> getAll();
}
