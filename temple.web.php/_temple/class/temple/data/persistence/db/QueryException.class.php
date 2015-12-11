<?php

namespace temple\data\persistence\db ;

/**
 * Base exception class used when an issue occurs with a Query.
 *
 * @author florent
 */
class QueryException extends DBException {

	/**
	 * Constructor.
	 *
	 * @param Query $query - the query which caused this exception
	 * @param string $reason - the reason why this exception is thrown
	 * @param number $code - the error code (optionnal, 0 by default)
	 */
	public function __construct($query, $reason, $code = 0) {
		parent::__construct(sprintf('Failure while executing query (code %s): ' . PHP_EOL .'%s'. PHP_EOL . 'Reason: %s', $code, $query, $reason), $code) ;
	}

}
