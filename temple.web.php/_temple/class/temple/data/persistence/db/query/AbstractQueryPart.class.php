<?php

namespace temple\data\persistence\db\query;

/**
 * Description of AbstractQueryPart
 *
 * @author flominou
 */
abstract class AbstractQueryPart implements QueryPart {

	public function __toString() {
		return $this->toString() ;
	}
	
}
