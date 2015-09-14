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
	 * 
	 * @param \ReflectionClass $class
	 * @param type $id
	 * @return Model
	 */
	public abstract function getProxy(\ReflectionClass $class, $id) ;
	
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
	 * 
	 * @param ReflectionClass $class
	 * @param type $sql
	 * @return array
	 */
	public abstract function findByRawQuery(\ReflectionClass $class, $sql) ;
	
	/**
	 * @param Filter $filter Description
	 * @return boolean
	 */
	public abstract function exists(Filter $filter) ; 
	
	/**
	 * TODOC
	 *
	 * @param Model $model
	 * @return scalar the generated id
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
	 * @return boolean true if the given object as been updated
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
	 * @param Key $key
	 * @param mixed $value
	 * @return int the count deleted model
	 * @throws \temple\data\persistence\db\DBException
	 */
	public abstract function deleteByKey(Key $key, $value) ;

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
