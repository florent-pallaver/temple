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
	 * @param Key $key
	 * @param mixed $id
	 * @return Model null if no model found
	 * @throws \temple\data\persistence\db\DBException
	 */
	public abstract function findByKey(Key $key, $id) ;

	/**
	 * TODOC
	 *
	 * @param Filter $filter
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
	 * @return boolean true if the given object has been updated
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
	 * @return int the count of deleted model
	 * @throws \temple\data\persistence\db\DBException
	 */
	public abstract function deleteByKey(Key $key, $value) ;

	/**
	 * TODOC
	 *
	 * @param Model $model
	 * @return boolean
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
