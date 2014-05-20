<?php

namespace temple\data\persistence\db\query\impl ;

use temple\data\persistence\db\query\AbstractConditionnedQuery;
use temple\data\persistence\db\query\Select;
use temple\data\persistence\db\query\Field;
use temple\data\persistence\db\query\Table;

/**
 * TODOC
 *
 * @author florent
 */
class SelectImpl extends AbstractConditionnedQuery implements Select {

	private $fields ;

	public function __construct(Table $table, array $fields, $maxCount, $offset) {
		parent::__construct($table, $maxCount, $offset) ;
		$this->fields = $fields ;
	}

	public function getFields() {
		return $this->fields ;
	}

	public function addField(Field $field) {
		$this->fields[] = $field ;
		return $this ;
	}

}