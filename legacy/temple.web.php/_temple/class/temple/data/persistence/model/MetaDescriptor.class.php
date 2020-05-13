<?php

namespace temple\data\persistence\model\impl;

use ReflectionClass;
use temple\data\persistence\model\Mapping;
use temple\data\persistence\model\RelationMapping;
use temple\data\persistence\model\BasicMapping ;

/**
 * TODOC
 *
 * @author florent
 */
class MetamodelImpl implements \temple\data\persistence\model\Metamodel {

	/** @var ReflectionClass */
	private $class;
	private $logger;
	private $table;
	private $primaryKey ;
	private $mappings;
	private $relations;

	public function __construct(ReflectionClass $class) {
		$this->class = $class;
		$this->logger = \temple\Logger::getInstance($class->getName());
		$this->table = self::getStaticProperty($class, 'TABLE', strtolower($class->getShortName()));
		$this->primaryKey = $class->getMethod('getPK')->invoke(null) ;
		$this->mappings = [];
		$this->relations = [];
		$this->logger->fine('loading metamodel');
		$this->loadFrom($class);
		foreach ($this->mappings as $fn => $fd) {
			if ($fd === null) {
				$pt = 'transient' ;
				unset($this->mappings[$fn]);
			} elseif ($fd instanceof RelationMapping) {
				$pt = 'relation' ;
				$this->relations[$fn] = $fd;
				unset($this->mappings[$fn]);
			} else {
				$pt = 'basic' ;
			}
			$this->logger->fine("field $fn: $pt");
		}
		$this->logger->fine('metamodel loaded') ;
	}

	private function loadFrom(ReflectionClass $class) {
		$parent = $class->getParentClass();
		if ($parent) {
			$this->logger->fine('loading from ' . $parent->getName());
			$this->loadFrom($parent);
		}
		foreach (self::getStaticProperty($class, 'mappings', []) as $fn => $fm) {
			$this->mappings[$fn] = $fm;
		}
		foreach ($class->getProperties() as $p) {
			$pn = $p->getName();
			if (!($p->isStatic() || array_key_exists($pn, $this->mappings))) {
				$this->logger->fine('Default descriptor for field ' . $pn);
				$this->mappings[$pn] = new BasicMapping($p);
			}
		}
	}

	private static function getStaticProperty(ReflectionClass $class, $property, $default = null) {
		$v = $default;
		if ($class->hasProperty($property)) {
			$p = $class->getProperty($property);
			if ($p->isStatic()) {
				$pr = $p->isPrivate() || $p->isProtected();
				if ($pr) {
					$p->setAccessible(true);
				}
				$v = $p->getValue(null);
				if ($pr) {
					$p->setAccessible(false);
				}
			}
		}
		return $v;
	}

	public function getClass() {
		return $this->class;
	}

	public function getTableName() {
		return $this->table;
	}

	public function getRelationMappings() {
		return $this->relations;
	}

	public function getMappings() {
		return $this->mappings;
	}

	public function getPrimaryKey() {
		return $this->primaryKey;
	}
	
	public function newInstance() {
		return $this->class->newInstance();
	}

}
