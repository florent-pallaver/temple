<?php

namespace temple\data\persistence\model ;

/**
 *
 * TODOC
 *
 * @author florent
 */
interface Model extends \JsonSerializable {

	/**
	 * @return \ReflectionClass this object's class.
	 */
	function getClass() ;

	/**
	 * @return mixed TODOC
	 */
	function getId() ;

}
