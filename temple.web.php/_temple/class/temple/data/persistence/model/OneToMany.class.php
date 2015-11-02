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
				if($this->logger->isFineLoggable()) {
					$this->logger->fine(sprintf('Getting proxy %1$s[][%2$s=%4$s] for %3$s[id=%4$s]', $this->to->getClass()->getName(), implode(', ', $this->to->getColumnNames()), $m->getClass()->getName(), $m->getId())) ;
				}
				$o = new proxy\ArrayProxy($this->to, $m->getId()) ;
			}
		}
		$this->setValue($m, $o) ;
		return $o ;
	}
	
}
