<?php

namespace temple\data\persistence\model ;

use ReflectionProperty ;

/**
 * Base implementation of Mapping.
 *
 * @author florent
 */
abstract class AbstractRelationMapping extends AbstractMapping implements RelationMapping {

	/**
	 * @var Key
	 */
	private $from ;
	
	/**
	 * @var Key
	 */
	private $to ;
	
//	private $uniqueAlias ;

	private $autoFetch ;

	private $optionnal ;

	public function __construct(ReflectionProperty $field, Key $from, Key $to, $autoFetch = false, $optionnal = false, $insertable = true, $updatable = true) {
		parent::__construct($field, $insertable, $updatable, ModelFieldConverter::getInstance($to->getClass()));
		if(count($to->getColumnNames()) != count($from->getColumnNames())) {
			throw new \temple\IllegalArgumentException('Given keys do not have the same number of columns') ;
		}
		$this->from = $from ;
		$this->to = $to ;
		$this->autoFetch = $autoFetch ;
		$this->optionnal = $optionnal ;
	}
	
	public final function getColumnNames() {
		return $this->from->getColumnNames();
	}
	
	public final function getMappedKey() {
		return $this->to ;
	}

	public final function autoFetch() {
		return $this->autoFetch ;
	}

	public final function isOptionnal() {
		return $this->optionnal ;
	}
	
}
