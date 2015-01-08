<?php

namespace temple\data\persistence\db\query;

/**
 * Description of OrderBy
 *
 * @author florent
 */
final class OrderBy implements QueryPart{
	
	const ASC = 'ASC' ;
	
	const DESC = 'DESC' ;
	
	private $field ;
	
	private $asc ;

	/**
	 * 
	 * @param \temple\data\persistence\db\query\Field $field
	 * @param boolean $asc
	 */
	public function __construct(Field $field, $asc = true) {
		$this->field = $field ;
		$this->asc = $asc ;
	}
	
	public function __toString() {
		return $this->field . ' ' . ($this->asc ? self::ASC : self::DESC) ;
	}

}
