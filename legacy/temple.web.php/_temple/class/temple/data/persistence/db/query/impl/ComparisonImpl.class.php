<?php

namespace temple\data\persistence\db\query\impl ;

use temple\data\persistence\db\query\Comparison;
use temple\data\persistence\db\query\Field ;
use temple\data\persistence\db\query\Value ;

/**
 * TODOC
 *
 * @author florent
 */
class ComparisonImpl extends \temple\data\persistence\db\query\AbstractQueryPart implements Comparison {

	/** @var Field */
	private $field ;

	/** @var string */
	private $comparator ;

	/** @var Value */
	private $value ;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param Field $field
	 * @param Value $value
	 * @param string $comparator
	 */
	public function __construct(Field $field, Value $value, $comparator) {
		$this->field = $field ;
		$this->comparator = $comparator ;
		$this->value = $value ;
	}

	public function toString() {
		return $this->field->toString() . ' ' . $this->comparator . ' ' . $this->value->toString() ;
	}

}

