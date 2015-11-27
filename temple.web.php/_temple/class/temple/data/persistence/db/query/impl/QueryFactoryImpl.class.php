<?php

namespace temple\data\persistence\db\query\impl;

use temple\IllegalArgumentException;
use temple\Singleton;
use temple\data\persistence\db\query\QueryFactory;
use temple\data\persistence\db\query\Field;
use temple\data\persistence\db\query\Table;
use temple\data\persistence\model\Key;
use temple\data\persistence\db\query\ValueFactory ;

/**
 * TODOC
 *
 * @author florent
 */
final class QueryFactoryImpl extends QueryFactory {

	use Singleton;

	protected function __construct() {
		
	}

	public function newKeyComparison(Key $key, $values, Table $table = null, $comp = null) {
		$_values = is_array($values) ? $values : [$values] ;
		$fns = $key->getColumnNames();
		$cf = count($fns) ;
		$cv = count($_values) ;
//		if ($cf == $cv) {
		$and = $this->newAndCondition();
		for ($i = 0, $l = $cf > $cv ? $cv : $cf ; $i < $l; $i++) {
			$and->addComparison($this->newFieldComparison(new Field($fns[$i], $table), $_values[$i], $comp));
		}
		return $and;
//		}
//		throw new IllegalArgumentException("$cv values given to be compared with a key of $cf fields");
	}

	public function newFieldComparison(Field $field, $value, $comparator = null) {
		$v = ValueFactory::create($value) ;
		return new ComparisonImpl($field, $v, $v->getOperator($comparator));
	}

	public function newAndCondition() {
		return new ConditionImpl('AND');
	}

	public function newOrCondition() {
		return new ConditionImpl('OR');
	}

	public function newTable($name, $alias = null) {
		return new TableImpl($name, $alias);
	}

	public function newSelect(Table $table, array $fields, $maxCount = 0, $offset = 0) {
		return new SelectImpl($table, $fields, $maxCount, $offset);
	}

	public function newUpdate(Table $table, $maxCount = 0, $offset = 0) {
		return new UpdateImpl($table, $maxCount, $offset);
	}

	public function newDelete(Table $table, $maxCount = 0, $offset = 0) {
		return new DeleteImpl($table, $maxCount, $offset);
	}

	public function newInsert($tableName) {
		return new InsertImpl(new TableImpl($tableName));
	}

}
