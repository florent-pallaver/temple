<?php

namespace temple\data\persistence\db\query\impl;

use temple\data\persistence\db\query\Condition;
use temple\data\persistence\db\query\Comparison;

/**
 * TODOC
 *
 * @author florent
 */
class ConditionImpl extends \temple\data\persistence\db\query\AbstractQueryPart implements Condition {

	private $type;
	private $comparisons = [];

	/**
	 * TODOC
	 *
	 * @param unknown $type
	 */
	public function __construct($type) {
		$this->type = $type;
	}

	public function isEmpty() {
		return !$this->comparisons;
	}

	public function addComparison(Comparison $comp) {
		if (!($comp instanceof Condition && $comp->isEmpty())) {
			$this->comparisons[] = $comp;
		} else {
			\temple\Logger::getInstance()->debug('Empty Condition given');
		}
		return $this;
	}

	public function toString() {
		switch(count($this->comparisons)) {
			case 0:
				$s = '' ;
				break ;
			case 1:
				$s = $this->comparisons[0]->toString() ;
				break ;
			default :
				$s = '(' . implode(' ' . $this->type . ' ', $this->comparisons) . ')' ;
		}
		return $s;
	}

}
