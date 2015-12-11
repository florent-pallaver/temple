<?php

namespace temple\data\persistence\db\query ;

/**
 * Implementation of Value for fields.
 *
 * @author florent
 */
final class Field extends AbstractQueryPart implements Value {

// 	const ALIAS_PREFIX = 'f' ;

// 	private static $aliasIndex = 0 ;

	private $name ;

	private $table ;

 	private $alias ;

	/**
	 * Constructor.
	 *
	 * @param string $name - the field name.
	 * @param \temple\data\persistence\db\query\Table $table
	 * @param string $alias
	 */
	public function __construct($name, Table $table = null, $alias = null) {
		$this->name = $name ;
		$this->table = $table ;
		$this->alias = $alias ;
	}

	public function getOperator($comparison = null) {
		return _dif($comparison, '=') ;
	}
	
	public function toString() {
		return ($this->table ? $this->table->getAlias() . '.' : '') . $this->name .
				($this->alias ? " AS $this->alias" : '');
	}

}
