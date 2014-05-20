<?php

namespace temple\data\persistence\db\query ;

use temple\data\persistence\db\Driver ;
use temple\IllegalArgumentException ;

/**
 * Implementation of Value for scalars.
 *
 * @author florent
 */
final class ScalarValue implements Value {

	private $scalar ;

	/**
	 * Constructor.
	 *
	 * @param mixed $scalar - a scalar
	 * @throws IllegalArgumentException - if a non-scalar value has been given.
	 */
	public function __construct($scalar) {
		if(!is_scalar($scalar)) {
			throw new IllegalArgumentException($scalar .' is not a scalar!') ;
		}
		$this->scalar = $scalar ;
	}

	public function getOperator($cmp = null) {
		return _dif($cmp, '=') ;
	}

	public function __toString() {
		return is_string($this->scalar)
				? '\'' . Driver::getInstance()->escape($this->scalar) . '\''
				: (is_bool($this->scalar)
					? ($this->scalar ? 'TRUE' : 'FALSE')
					: $this->scalar . '');
	}

}
