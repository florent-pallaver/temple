<?php

namespace temple\data\persistence\db\query ;

/**
 * NULL value singleton implementation
 *
 * @author florent
 * @see NullValue::getInstance()
 */
final class NullValue extends AbstractQueryPart implements Value {

	use \temple\Singleton ;

	public function getOperator($comp = null) {
		return $comp && ($comp === '!=' || $comp === '<>') ? 'IS NOT': 'IS' ;
	}

	public function toString() {
		return 'NULL' ;
	}

}
