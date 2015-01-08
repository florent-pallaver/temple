<?php

namespace temple\data\persistence\model ;

/**
 * TODOC
 *
 * @author florent
 */
class Filter {

	private $modelClass ;

	private $conditions ;
	
	private $orders ;

	private $maxCount ;

	private $offset ;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param string $modelClassName
	 * @param Condition $condition
	 * @param array $orders
	 * @param number $maxCount
	 * @param number $offset
	 */
	public function __construct(\ReflectionClass $modelClass, $maxCount = 0, $offset = 0) {
		$this->modelClass = $modelClass ;
//		$this->comparison = $comp;
		$this->orders = [] ;
		$this->maxCount = $maxCount < 0 ? 0 : $maxCount ;
		$this->offset = $offset < 0 ? 0 : $offset ;
		$this->conditions = [] ;
	}

	/**
	 * @return \ReflectionClass TODOC
	 */
	public function getModelClass() {
		return $this->modelClass ;
	}

	/**
	 * 
	 * @return array
	 */
	public function getOrders() {
		return $this->orders ;
	}
	
	/**
	 * 
	 * @param Key $modelKey
	 * @param boolean $asc
	 * @return Filter
	 */
	public function addOrder(Key $modelKey, $asc = false) {
		$this->orders[] = [$modelKey, $asc] ;
		return $this ;
	}

	public function getMaxCount() {
		return $this->maxCount ;
	}

	public function getOffset() {
		return $this->offset ;
	}

	/**
	 * 
	 * @return array
	 */
	public function getKeyConditions() {
		return $this->conditions ;
	}
	
	/**
	 * 
	 * @param Key $modelKey
	 * @param mixed $value
	 * @return Filter
	 */
	public function addKeyCondition(Key $modelKey, $value) {
		$this->conditions[] = [$modelKey, _eia($value)] ;
		return $this ;
	}
	
	/**
	 * 
	 * @param \ReflectionClass $modelClass
	 * @param type $maxCount
	 * @param type $offset
	 * @return Filter
	 */
	public static function create(\ReflectionClass $modelClass, $maxCount = 0, $offset = 0) {
		return new Filter($modelClass, $maxCount, $offset) ;
	}
	
}

