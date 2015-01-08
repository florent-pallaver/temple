<?php

namespace temple\data\persistence\model;

/**
 * Description of BasicMapping
 *
 * @author florent
 */
class BasicMapping extends AbstractMapping {

	private $colNames ;

	/**
	 * @var array 
	 */
	private $constraints ;
	
	/**
	 * @var FieldConverter
	 */
	private $converter ;
	
	public function __construct(\ReflectionProperty $field, $insertable = true, $updatable = true, FieldConverter $converter = null, array $constraints = [], $colName = null) {
		parent::__construct($field, $insertable, $updatable) ;
		$this->colNames = [_dif($colName, $field->getName())];
		$this->converter = _dif($converter, ScalarFieldConverter::getInstance()) ;
		$this->constraints = $constraints;
	}
	
	public final function getColumnNames() {
		return $this->colNames;
	}
			
	public final function getDBValue(Model $m) {
		$v = $this->getValue($m) ;
		foreach($this->constraints as $c) {
			$c->validate($v) ;
		}
		return _eia($this->converter->toDBValue($v)) ;
	}

	public final function setPHPValue(Model $m, $value) {
		$v = $this->converter->toPHPValue($value) ;
		$this->setValue($m, $v) ;
		return $v ;
	}

	/**
	 * 
	 * @param \ReflectionProperty $field
	 * @param type $insertable
	 * @param type $updatable
	 * @param array $constraints
	 * @param type $colName
	 * @return \temple\data\persistence\model\BasicMapping
	 */
	public static final function createDateTime(\ReflectionProperty $field, $insertable = true, $updatable = true, array $constraints = [], $colName = null) {
		return new BasicMapping($field, $insertable, $updatable, DateTimeFieldConverter::getInstance(), $constraints, $colName) ;
	}
	
}
