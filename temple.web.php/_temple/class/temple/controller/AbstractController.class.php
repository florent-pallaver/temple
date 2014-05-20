<?php

namespace temple\controller;

use Exception ;
use temple\data\Messages ;

/**
 * Description of AbstractController
 *
 * @author florent
 */
abstract class AbstractController implements Controller {

	/** @var \mysqli */
//	private $connection;

	public function __construct() {
//		$this->connection = DB::getInstance()->getConnection();
	}

	protected final function success($msg) {
		Messages::getInstance()->success($msg) ;
	}

	protected final function info($msg) {
		Messages::getInstance()->info($msg) ;
	}

	protected final function warning($msg, Exception $e = null) {
		Messages::getInstance()->warning($msg, $e) ;
	}

	protected final function error($msg, Exception $e = null) {
		Messages::getInstance()->error($msg, $e) ;
	}

//	protected final function escapeSQL($str) {
//		$e = $this->connection->real_escape_string($str);
//		return $e;
//	}

	/**
	 * 
	 * @param string $sql
	 * @return \mysqli_result
	 * @throws \Exception
	 */
//	protected final function execute($sql) {
//		$result = $this->connection->query($sql);
//		if (!$result) {
//			$this->log('Query failed: ' . $sql) ;
//			throw new Exception($this->connection->error, $this->connection->errno);
//		}
//		return $result;
//	}

	/**
	 * 
	 * @param type $sql
	 * @return int
	 * @throws \Exception
	 */
//	protected final function executeWrite($sql) {
//		$this->execute($sql);
//		return $this->getAffectRows();
//	}

	/**
	 * 
	 * @param string $sql
	 * @return mixed - the inserted id
	 */
//	protected final function executeInsert($sql) {
//		$result = $this->execute($sql);
//        if (!$result) {
//            throw new \Exception($this->connection->error, $this->connection->errno);
//        }
//		return $this->connection->insert_id;
//	}

	/**
	 * 
	 * @param type $sql
	 * @return mixed
	 * @throws \Exception
	 */
//	protected final function getSingleValueResult($sql) {
//		$r = $this->execute($sql);
//		return $r->num_rows ? $r->fetch_array(MYSQLI_NUM)[0] : null;
//	}
//	protected final function getSingleValueResult($sql) {
//		$r = $this->execute($sql);
//		return $r->num_rows ? $r->fetch_array(MYSQLI_NUM)[0] : null;
//	}

	/**
	 * @return int
	 */
//	protected final function getAffectRows() {
//		return $this->connection->affected_rows;
//	}

}
