<?php

namespace temple\data\persistence\model;

/**
 * Description of FieldPath
 *
 * @author florent
 */
class FieldPath {
	
	/**
	 * @var FieldPath ;
	 */
	private $parent ;

	/**
	 * @var $mapping
	 */
	private $mapping ;

	public function __construct(Mapping $mapping, FieldPath $parent = null) {
		$this->parent = $parent ;
		$this->mapping = $mapping ;
	}
	
}
