<?php

namespace temple\data\persistence\db\query ;


abstract class AbstractValue implements Value {

	/**
	 * TODOC
	 *
	 * @param string $comparator - always lowercase
	 */
	protected abstract function accept($comparator) ;



}