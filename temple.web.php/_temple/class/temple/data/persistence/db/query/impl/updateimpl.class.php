<?php

namespace temple\data\persistence\db\query\impl ;

use temple\data\persistence\db\query\AbstractConditionnedQuery;
use temple\data\persistence\db\query\Update;

/**
 * TODOC
 *
 * @author florent
 */
class UpdateImpl extends AbstractConditionnedQuery implements Update {

	private $assignments = array() ;

	public function getAssignments() {
		return $this->assignments ;
	}

	public function addAssignment(Field $field, Value $value) {
		$this->assignments[] = $field . ' = ' . $value ;
		return $this ;
	}

}
