<?php

namespace temple\data\persistence\model;

/**
 * Description of BasicMapping
 *
 * @author florent
 */
class BasicMapping extends AbstractMapping {

	private $colNames ;
	
	public function __construct(\ReflectionProperty $field, $insertable = true, $updatable = true, FieldConverter $converter = null, array $constraints = [], $colName = null) {
		parent::__construct($field, $insertable, $updatable, _dif($converter, ScalarFieldConverter::getInstance()), $constraints) ;
		$this->colNames = [_dif($colName, $field->getName())];
	}
	
	public final function getColumnNames() {
		return $this->colNames;
	}
	
}
