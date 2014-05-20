<?php

namespace temple\data\persistence\db\query\impl ;

use temple\data\persistence\db\query\AbstractQuery;
use temple\data\persistence\db\query\Insert ;

/**
 * TODOC
 *
 * @author florent
 */
class InsertImpl extends AbstractQuery implements Insert {

	private $fields = null ;

	private $tuples = array() ;

	public function getFields() {
		return $this->fields ;
	}

	public function setFields(array $fields = null) {
		// if fields are illegal, an SQL error should happen ...
		$this->fields = $fields ;
		return $this ;
	}

	public function getTuples() {
		return $this->tuples ;
	}

	public function addTuple(array $tuple) {
		$this->tuples[] = '(' . implode(', ', $tuple) . ')' ;
		return $this ;
	}

}