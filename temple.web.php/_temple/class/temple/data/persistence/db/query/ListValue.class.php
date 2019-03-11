<?php

namespace temple\data\persistence\db\query ;

/**
 * Implementation of Value for lists of values.
 *
 * @author florent
 */
final class ListValue extends AbstractQueryPart implements Value {

	private $listValues ;

	/**
	 * Constructor.
	 *
	 * @param array $listValues -  an array of values.
	 */
	public function __construct(array $listValues) {
		$this->listValues = $listValues ;
	}

	public function getOperator($comp = null) {
		return $comp && ($comp === '!=' || $comp === '<>') ? 'NOT IN' : 'IN' ;
	}

	public function toString() {
		return '(' . implode(', ', $this->listValues) . ')' ;
	}

}
