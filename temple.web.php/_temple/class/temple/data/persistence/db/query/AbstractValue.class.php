<?php

namespace temple\data\persistence\db\query ;

abstract class AbstractValue extends AbstractQueryPart implements Value {

	/**
	 * TODOC
	 *
	 * @param string $comparator - always lowercase
	 */
	protected abstract function accept($comparator) ;

	
}