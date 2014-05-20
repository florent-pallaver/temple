<?php

namespace temple\data\persistence\model;

/**
 * Description of EntityCommon
 *
 * @author florent
 */
trait EntityCommon {
	
	use \temple\Reflection ;

	/**
	 * @var array
	 */
	private static $mappings = [] ;

}
