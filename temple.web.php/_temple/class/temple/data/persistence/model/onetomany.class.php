<?php

namespace temple\data\persistence\model ;

/**
 * Class to define a one to many relation.
 *
 * @author florent
 * @see Relation
 */
final class OneToMany extends AbstractRelationMapping {

	public function setPHPValue(Model $m, $value) {
		$o = null ;
		if($value !== null) {
			if(is_array($value)) {
				$o = $value ;
			} else {
				$o = new proxy\ArrayProxy($this->to, $m->getId()) ;
			}
		}
		$this->setValue($m, $o) ;
		return $o ;
	}
	
}
