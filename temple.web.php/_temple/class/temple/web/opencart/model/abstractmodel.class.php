<?php

namespace temple\web\opencart\model ;

use temple\web\opencart\Helper;

/**
 *
 * TODOC
 *
 * @author florent
 */
abstract class AbstractModel extends \Model {

	/**
	 * Constructor.
	 *
	 * @throws temple\web\opencart\OpencartException if the registry has not been set.
	 * @see Helper::getRegistry()
	 */
	public function __construct() {
		// At this point the registry should be set since a model is meant to be loaded by a controller
		parent::__construct(Helper::getRegistry()) ;
	}

}
