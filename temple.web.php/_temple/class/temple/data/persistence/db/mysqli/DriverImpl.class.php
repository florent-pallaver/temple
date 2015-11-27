<?php

namespace temple\data\persistence\db\mysqli ;

use mysqli ;
use mysqli_result;

use temple\Singleton ;

use temple\data\persistence\db\Driver;
use temple\data\persistence\db\QueryException;
use temple\data\persistence\db\ConnectionException ;
use temple\data\persistence\db\UniqueConstraintException ;
use temple\data\persistence\db\query\ConditionnedQuery;
use temple\data\persistence\db\query\QueryResult;

/**
 * TODOC
 *
 * @author florent
 */
class DriverImpl extends Driver {

	use Singleton ;

	const LOGGER_NAME = 'MySQLi_DRIVER' ;
	
	/** @var mysqli */
	private $connection = null ;

	protected $logger ;
	
	protected function __construct() {
		parent::__construct();
		$this->logger = \temple\Logger::getInstance(self::LOGGER_NAME) ;
	}

	public final function connect() {
		if($this->connection === null || !$this->connection->ping()) {
			$c = new mysqli(self::$HOST, self::$USER, self::$PASSWORD, self::$DATABASE, self::$PORT) ;
			if($c->connect_error) {
				throw new ConnectionException($c->connect_error, $c->connect_errno) ;
			}
			$this->connection = $c ;
			$this->connection->set_charset(self::$CHARSET) ;
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
		$this->logger->debug($sqlQuery) ;
		$result = $this->connection->query($sqlQuery) ;
		if(!$result) {
			switch($this->connection->errno) {
				case 1062: 
					throw new UniqueConstraintException($sqlQuery, $this->connection->error, $this->connection->errno) ;
				case 1451:
				case 1452:
					throw new \temple\data\persistence\db\ForeignKeyConstraintException($sqlQuery, $this->connection->error, $this->connection->errno) ;
				default:
					throw new QueryException($sqlQuery, $this->connection->error, $this->connection->errno) ;
			}
		}
		// before checking warnings as it changes affected_rows !
		$rowCount = $this->connection->affected_rows ;
		$rows = [] ;
		$insertId = $this->connection->insert_id ;
		if($this->connection->warning_count) {
			$this->logger->warning($sqlQuery) ;
			$ws = $this->connection->get_warnings() ;
			do {
				$this->logger->warning(sprintf('[%s] %s', $ws->errno, $ws->message)) ;
			} while ($ws->next()) ;
		}
		if($result instanceof mysqli_result) {
			while($r = $result->fetch_assoc()) {
				$rows[] = $r ;
			}
			$result->free() ;
		}
		return new QueryResult($rowCount, $rows, $insertId) ;
	}

	public final function escape($string) {
		$this->connect();
		return $this->connection->real_escape_string($string) ;
	}
	
}
