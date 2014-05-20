<?php

namespace temple\data\persistence\db\mysqli ;

use mysqli ;
use mysqli_result;

use temple\Singleton ;

use temple\data\persistence\db\Driver;
use temple\data\persistence\db\QueryException;
use temple\data\persistence\db\ConnectionException ;
use temple\data\persistence\db\query\ConditionnedQuery;
use temple\data\persistence\db\query\QueryResult;

/**
 * TODOC
 *
 * @author florent
 */
class DriverImpl extends Driver {

	use Singleton ;

	/** @var mysqli */
	private $connection = null ;

	public final function connect() {
		if($this->connection === null || !$this->connection->ping()) {
			$c = new mysqli(self::$HOST, self::$USER, self::$PASSWORD, self::$DATABASE, self::$PORT) ;
			if($c->connect_errno) {
				throw new ConnectionException($c->connect_error, $c->connect_errno) ;
			}
			$this->connection = $c ;
		}
	}

	public final function disconnect() {
		if($this->connection) {
			$this->connection->close() ;
			$this->connection = null ;
		}
	}

	protected function postCreateSQLString(&$sqlString, ConditionnedQuery $cQuery) {
		$maxCount = $cQuery->getMaxCount() ;
		if($maxCount > 0) {
			$offset = $cQuery->getOffset() ;
			$sqlString .= "\nLIMIT " ;
			if($offset > 0) {
				$sqlString .= $offset . ', ' ;
			}
			$sqlString .= $maxCount ;
		}
	}

	public final function sqlQuery($sqlQuery) {
		$this->connect() ;
		$result = $this->connection->query($sqlQuery) ;
		if($result instanceof mysqli_result) {
			$rowCount = $this->connection->affected_rows ;
			$insertId = $this->connection->insert_id ;
			$rows = [] ;
			while($rows[] = $result->fetch_assoc()) {}
			$result->free() ;
			return new QueryResult($rowCount, $rows, $insertId) ;
		}
		throw new QueryException($sqlQuery, $this->connection->error, $this->connection->errno) ;
	}

	public final function escape($string) {
		$this->connect();
		return $this->connection->escape_string($string) ;
	}
	
}
