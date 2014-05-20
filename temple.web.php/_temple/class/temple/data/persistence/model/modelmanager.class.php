<?php

namespace temple\data\persistence\model ;

use temple\ToImplement ;

abstract class ModelManager {

	use ToImplement ;

	/**
	 * @return Metamodel 
	 */
	public abstract function getMetamodel(\ReflectionClass $class) ;
	
	/**
	 * TODOC
	 *
	 * @param unknown $modelClassName
	 * @param unknown $id
	 * @return Model
	 * @throws
	 */
	public abstract function findByKey(Key $key, $id) ;

	/**
	 * TODOC
	 *
	 * @param unknown $modelClassName
	 * @param Filter $pagination
	 * @return array
	 */
	public abstract function findByFilter(Filter $filter) ;

	/**
	 * @param Filter $filter Description
	 * @return boolean
	 */
	public abstract function exists(Filter $filter) ; 
	
	/**
	 * TODOC
	 *
	 * @param Model $model
	 * @return
	 */
	public abstract function persist(Model $model) ;

	/**
	 * TODOC
	 *
	 * @param array $models
	 * @return
	 */
	public abstract function persistAll(array $models) ;

	/**
	 * TODOC
	 *
	 * @param Model $model
	 * @return
	 */
	public abstract function update(Model $model) ;

	/**
	 * TODOC
	 *
	 * @param array $models
	 * @return
	 */
	public abstract function updateAll(array $models) ;

	/**
	 * TODOC
	 *
	 * @param unknown $modelClassName
	 * @param unknown $id
	 * @return
	 * @throws
	 */
	public abstract function deleteById(\ReflectionClass $modelClass, $id) ;

	/**
	 * TODOC
	 *
	 * @param Model $model
	 * @return
	 */
	public abstract function delete(Model $model) ;

	/**
	 * TODOC
	 *
	 * @param array $models
	 * @return
	 */
	public abstract function deleteAll(array $models) ;

}
