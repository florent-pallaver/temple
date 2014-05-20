<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
abstract class AbstractConditionnedQuery extends AbstractQuery implements ConditionnedQuery {

	/**
	 * @var Condition
	 */
	private $condition ;

	/**
	 * @var array
	 */
	private $orders ;

	private $offset ;

	private $maxCount ;

	/**
	 * Constructor.
	 * TODOC
	 * @param Table $table
	 * @param number $length
	 * @param number $start
	 */
	public function __construct(Table $table, $maxCount, $offset) {
		parent::__construct($table) ;
		$this->maxCount = $maxCount ;
		$this->offset = $offset ;
		$this->condition = QueryFactory::getInstance()->newAndCondition() ;
		$this->orders = array() ;
	}

	public function getCondition() {
		return $this->condition ;
	}

	public function getOrders() {
		return $this->orders ;
	}

	public function addOrderedField(Field $field, $asc = false) {
		$this->orders[] = $field . ' ' . ($asc ? 'ASC' : 'DESC') ;
		return $this ;
	}

	public function addOrderedFields(array $fields, $asc = false) {
		foreach($fields as $f) {
			$this->addOrderedField($f, $asc) ;
		}
		return $this ;
	}

	// 	public final function addRandomOrder() {
	// 		$this->orders[] = 'RAND()' ;
	// 		return $this ;
	// 	}

	public function getOffset() {
		return $this->offset ;
	}

	public function getMaxCount() {
		return $this->maxCount ;
	}

}
