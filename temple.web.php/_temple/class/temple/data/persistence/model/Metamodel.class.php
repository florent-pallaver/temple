<?php

namespace temple\data\persistence\model ;

use ReflectionClass ;

/**
 * TODOC
 *
 * @author florent
 */
interface Metamodel {

	/**
	 * @return ReflectionClass - the entity class this metamodel describes.
	 */
	function getClass() ;

	function getTableName() ;

	function getRelationMappings() ;

	function getMappings() ;
	
	/**
	 * TODOC
	 *
	 * @return Model
	 */
	function newInstance() ;

}
