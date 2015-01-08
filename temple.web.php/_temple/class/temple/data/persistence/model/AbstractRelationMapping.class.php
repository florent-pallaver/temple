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
	protected $from ;
	
	/**
	 * @var Key
	 */
	protected $to ;
	
//	private $uniqueAlias ;

	private $autoFetch ;

	private $optionnal ;

	/**
	 * 
	 * @param ReflectionProperty $field
	 * @param \temple\data\persistence\model\Key $from
	 * @param \temple\data\persistence\model\Key $to
	 * @param boolean $autoFetch
	 * @param boolean $optionnal
	 * @param boolean $insertable
	 * @param boolean $updatable
	 * @throws \temple\IllegalArgumentException
	 */
	public function __construct(ReflectionProperty $field, Key $from, Key $to, $autoFetch = false, $optionnal = false, $insertable = true, $updatable = true) {
		parent::__construct($field, $insertable, $updatable);
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
	
	public function getDBValue(Model $m) {
		$o = $this->getValue($m) ;
		return _eia($o ? $o->getId() : null) ;
 	}
	
	public function setPHPValue(Model $m, $value) {
		$o = null ;
		if($value !== null) {
			if(is_object($value) && $this->to->getClass()->isInstance($value)) {
				$o = $value ;
			} else {
				$o = ModelManager::getInstance()->getProxy($this->to->getClass(), $value) ;
				$o->proxyInit($this->field, $m) ;
			}
		}
		$this->setValue($m, $o) ;
		return $o ;
	}
	
}
