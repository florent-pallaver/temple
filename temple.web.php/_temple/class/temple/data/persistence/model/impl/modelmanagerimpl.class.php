<?php

namespace temple\data\persistence\model\impl ;

use ReflectionClass ;

use temple\data\persistence\db\Driver;
use temple\data\persistence\db\query\QueryFactory;
use temple\data\persistence\db\query\Field ;
use temple\data\persistence\model\Key ;
use temple\data\persistence\model\ModelManager;
use temple\data\persistence\model\Model;
use temple\data\persistence\model\Filter;

class ModelManagerImpl extends ModelManager {

	use \temple\Singleton ;

	private static $metamodels = [];

	/**
	 * @var QueryFactory
	 */
	private $qf ;

	/**
	 * @var Driver
	 */
	private $driver ;

	protected function __construct() {
		$this->driver = Driver::getInstance() ;
		$this->qf = QueryFactory::getInstance() ;
	}

	public function getMetamodel(ReflectionClass $class) {
		$cn = $class->getName() ;
		if(!isset(self::$metamodels[$cn])){
			self::$metamodels[$cn] = new MetamodelImpl($class) ;
		}
		return self::$metamodels[$cn] ;;
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
		$sq = $gn->newSelect($filter->getComparison(), $filter->getMaxCount(), $filter->getOffset()) ;

		// TODO $filter->getOrders()

		$result = $this->driver->query($sq) ;
		$rows = $result->getRows() ;
		$models = [] ;
		for($i = 0, $l = $result->getRowCount() ; $i < $l ; $i++) {
			$models[] = $gn->extractObject($rows[$i]) ;
		}
		// load relations
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
		$gn = GraphNode::getInstance($model->getClass()) ;
		$iq = $gn->newInsert($model) ;
		return $this->driver->query($iq)->getGeneratedId() ;
	}

	public function persistAll(array $models) {
	}

	public function update(Model $model) {

	}

	public function updateAll(array $models) {

	}

	public function deleteById(\ReflectionClass $modelClass, $id) {
		$md = $this->getMetamodel($modelClass) ;
		$dq = $this->qf->newDelete($this->qf->newTable($md->getTable()), 1) ;
		$dq->getCondition()->addComparison(Comparison::create($md->getPrimaryKeyField(), $id)) ;
		$result = $this->driver->query($dq) ;
		return $result->getRowCount() === 1 ;
	}

	public function delete(Model $model) {
		return $this->deleteById($model->getClass(), $model->getId()) ;
	}

	public function deleteAll(array $models) {

	}

}
