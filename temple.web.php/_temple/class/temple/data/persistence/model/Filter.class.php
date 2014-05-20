<?php

namespace temple\data\persistence\model ;

use temple\data\persistence\db\query\Comparison;

/**
 * TODOC
 *
 * @author florent
 */
class Filter {

	private $modelClass ;

	private $comparison ;

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
	public function __construct(\ReflectionClass $modelClass, Comparison $comp, array $orders = [], $maxCount = 0, $offset = 0) {
		$this->modelClass = $modelClass ;
		$this->comparison = $comp;
		$this->orders = $orders ;
		$this->maxCount = $maxCount < 0 ? 0 : $maxCount ;
		$this->offset = $offset < 0 ? 0 : $offset ;
	}

	/**
	 * @return \ReflectionClass TODOC
	 */
	public function getModelClass() {
		return $this->modelClass ;
	}

	/**
	 * TODOC
	 *
	 * @return Comparison :
	 */
	public function getComparison() {
		return $this->comparison ;
	}

	public function getOrders() {
		return $this->orders ;
	}

	public function getMaxCount() {
		return $this->maxCount ;
	}

	public function getOffset() {
		return $this->offset ;
	}

	public function addComparison(array $mappings, $value) {
		
	}
	
}


