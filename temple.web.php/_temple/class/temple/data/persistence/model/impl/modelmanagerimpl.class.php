<?php

namespace temple\data\persistence\model\impl ;

use ReflectionClass ;

use temple\data\persistence\db\Driver;
use temple\data\persistence\db\query\QueryFactory;
use temple\data\persistence\model\Key ;
use temple\data\persistence\model\ModelManager;
use temple\data\persistence\model\Model;
use temple\data\persistence\model\Filter;
use temple\data\persistence\model\ProxyGenerator ;

class ModelManagerImpl extends ModelManager {

	use \temple\Singleton ;

	const LOGGER_NAME  = 'Temple.ModelManager' ;
	
	private static $metamodels = [];

	private static $proxies = [] ;
	
	/**
	 * @var \temple\Logger
	 */
	private $logger ;
	
	/**
	 * @var QueryFactory
	 */
	private $qf ;

	/**
	 * @var Driver
	 */
	private $driver ;

	protected function __construct() {
		$this->logger = \temple\Logger::getInstance(self::LOGGER_NAME) ;
		$this->driver = Driver::getInstance() ;
		$this->qf = QueryFactory::getInstance() ;
	}

	/**
	 * 
	 * @param ReflectionClass $class
	 * @return \temple\data\persistence\model\Metamodel
	 */
	public function getMetamodel(ReflectionClass $class) {
		$cn = $class->getName() ;
		if(!isset(self::$metamodels[$cn])){
			self::$metamodels[$cn] = new MetamodelImpl($class) ;
		}
		return self::$metamodels[$cn] ;
	}
	
	public function getProxy(\ReflectionClass $class, $id) {
		$cn = $class->getName() ;
		if(!isset(self::$proxies[$cn])) {
			self::$proxies[$cn] = [] ;
		}
		if(!isset(self::$proxies[$cn][$id])) {
			$proxyClass = new \ReflectionClass(ProxyGenerator::PROXY_NAMESPACE.$cn.'Proxy') ;
			$o = $proxyClass->newInstance() ;
			$this->getMetamodel($class)->getMappings()['id']->setPHPValue($o, $id) ;
			self::$proxies[$cn][$id] = $o ;
			$this->logger->debug("proxy for $cn"."[id=$id] created") ;
		} else {
			$this->logger->finer("proxy for $cn"."[id=$id] from cache") ;
		}
		return self::$proxies[$cn][$id] ;
	}
	
	public function findByKey(Key $key, $id) {
		$gn = GraphNode::getInstance($key->getClass()) ;
		$t = $gn->getEntryArrow()->getTable() ;
		$sq = $gn->newSelect($this->qf->newKeyComparison($key, $id, $t)) ;

		$rows = $this->driver->query($sq)->getRows() ;
		if($rows && isset($rows[0])) {
			$model = $gn->extractObject($rows[0]) ;
		} else {
			$model = null ;
		}
		// load relations
		return $model ;
	}

	public function findByFilter(Filter $filter) {
		$gn = GraphNode::getInstance($filter->getModelClass()) ;
		$ac = $this->qf->newAndCondition() ;
		foreach($filter->getKeyConditions() as $kv) {
			$ac->addComparison($this->qf->newKeyComparison($kv[0], $kv[1])) ;
		}
		$sq = $gn->newSelect($ac, $filter->getMaxCount(), $filter->getOffset()) ;
		foreach($filter->getOrders() as $o) {
			$sq->addOrderBy($o) ;
		}
		$result = $this->driver->query($sq) ;
		$rows = $result->getRows() ;
		$models = [] ;
		for($i = 0, $l = $result->getRowCount() ; $i < $l ; $i++) {
			$models[] = $gn->extractObject($rows[$i]) ;
		}
		// load relations
		return $models ;
	}

	public function findByRawQuery(ReflectionClass $class, $sql) {
		$result = $this->driver->sqlQuery($sql) ;
		$rows = $result->getRows() ;
		$gn = GraphNode::getInstance($class) ;
		$models = [] ;
		foreach($rows as $r) {
			$models[] = $gn->extractObject0($r) ;
		}
		return $models ;
	}
	
	public function exists(Filter $filter) {
		$gn = GraphNode::getInstance($filter->getModelClass()) ;
		$sq = $gn->newSelect($filter->getComparison(), 1, $filter->getOffset()) ;
		return $this->driver->query($sq)->getRowCount() ;
	}
	
	private function loadOneToManyRelations(array $objects) {
		$pks = array() ;
		foreach($objects as $o) {
			$pks[] = $o->getPrimaryKey() ;
		}
		foreach($this->descriptor->getRelations() as $key => $relation) {
			if($relation instanceof OneToMany && $relation->autoFetch()) {
				$relField = $relation->getField() ;
				$relMK = $relation->getMappingField() ;
				$relClass = $relation->getTargetClass() ;
				$query = (new SelectQuery($relClass))->addComparison($relMK, $pks) ;
				$relObjects = $query->execute() ;
				foreach($relObjects as $ro) {
					$o = $objects[$ro->$relMK] ;
					$array = &$o->$relField ;
					$array[$ro->getPrimaryKeyAsScalar()] = $ro ;
				}
				$query->loadOneToManyRelations($relObjects) ;
			}
			// 			if($relation instanceof ManyToMany && $relation->autoFetch()) {
			// select * from join table
			// 			}
		}
	}

	public function persist(Model $model) {
		$c = $model->getClass() ;
		$gn = GraphNode::getInstance($c) ;
		$iq = $gn->newInsert($model) ;
		$mid = $this->driver->query($iq)->getGeneratedId() ;
		if($mid) {
			$this->getMetamodel($c)->getMappings()['id']->setPHPValue($model, $mid) ;
		}
		return $mid ;
	}

	public function persistAll(array $models) {
	}

	public function update(Model $model) {
		$gn = GraphNode::getInstance($model->getClass()) ;
		$uq = $gn->newUpdate($model) ;
		return $this->driver->query($uq)->getRowCount() ;
	}

	public function updateAll(array $models) {

	}

	public function deleteByKey(Key $key, $value) {
		$gn = GraphNode::getInstance($key->getClass()) ;
		$t = $gn->getEntryArrow()->getTable() ;
		$dq = $gn->newDelete($this->qf->newKeyComparison($key, $value, $t)) ;
		return $this->driver->query($dq)->getRowCount() ;
	}

	public function delete(Model $model) {
		return $this->deleteByKey($model->getClass(), $model->getId()) ;
	}

	public function deleteAll(array $models) {

	}

}