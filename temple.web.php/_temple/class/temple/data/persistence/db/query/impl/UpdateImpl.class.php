<?php

namespace temple\data\persistence\db\query\impl ;

use temple\data\persistence\db\query\AbstractConditionnedQuery;
use temple\data\persistence\db\query\Update;
use temple\data\persistence\db\query\Field ;
use temple\data\persistence\db\query\Value ;

/**
 * TODOC
 *
 * @author florent
 */
class UpdateImpl extends AbstractConditionnedQuery implements Update {

	private $assignments = [] ;

	public function getAssignments() {
		return $this->assignments ;
	}

	public function addAssignment(Field $field, $value) {
		$this->assignments[] = $field . ' = ' . $value ;
		return $this ;
	}

}
