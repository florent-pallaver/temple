<?php

namespace temple\data\persistence\model ;

/**
 * TODOC
 *
 * @author florent
 */
interface RelationMapping extends Mapping {

	/**
	 * TODOC
	 * @var string
	 */
	const DEFAULT_MAPPING_FIELD_SUFFIX = 'Id' ;

	/**
	 * TODOC
	 * @return Key
	 */
	function getMappedKey() ;

	/**
	 * @return boolean true if this association should be load eagerly, <code>false</code> otherwise.
	 */
	function autoFetch() ;

	/**
	 * @return boolean <code>true</code> if this relationship is optionnal (may not exist), <code>false</code> if not (one must exist).
	 */
	function isOptionnal() ;

	// CASCADE ?

}
