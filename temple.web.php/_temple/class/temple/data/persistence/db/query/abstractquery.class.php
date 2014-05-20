<?php

namespace temple\data\persistence\db\query ;

/**
 * Base implemention of Query
 *
 * @author florent
 */
abstract class AbstractQuery implements Query {

	/** @var Table */
	private $table ;

	/**
	 * Constructor.
	 *
	 * @param Table $table TODOC
	 */
	public function __construct(Table $table) {
		$this->table = $table ;
	}

	public final function getTable() {
		return $this->table ;
	}

}
