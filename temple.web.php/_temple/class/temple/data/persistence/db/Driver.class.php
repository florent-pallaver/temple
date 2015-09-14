<?php

namespace temple\data\persistence\db ;

use temple\ToImplement ;
use temple\data\persistence\db\query\Query;
use temple\data\persistence\db\query\ConditionnedQuery;
use temple\data\persistence\db\query\Select;
use temple\data\persistence\db\query\Update;
use temple\data\persistence\db\query\Delete;
use temple\data\persistence\db\query\Insert;
use temple\data\persistence\db\query\QueryResult;

/**
 * TODOC
 *
 * Implementation must define a no arg constructor.
 *
 * @author florent
 */
abstract class Driver {

	use ToImplement ;

	/**
	 * @var string TODOC
	 */
	public static $HOST = 'localhost' ;

	/**
	 * @var int TODOC
	 */
	public static $PORT = 3306 ;

	/**
	 * @var string TODOC
	 */
	public static $USER = 'temple' ;

	/**
	 * @var string TODOC
	 */
	public static $PASSWORD = '' ;

	/**
	 * @var string TODOC
	 */
	public static $DATABASE = 'temple' ;

	/**
	 * @var string TODOC
	 */
	public static $CHARSET = 'utf8' ;
	
	const MySQLi_IMPL = 'mysqli' ;
	
	const SELECT_FORMAT = "SELECT %s \nFROM %s" ;

	const UPDATE_FORMAT = "UPDATE %s \nSET %s" ;

	const DELETE_FORMAT = 'DELETE %s FROM %s' ;

	const WHERE_FORMAT = "\nWHERE %s" ;

	const ORDER_FORMAT = "\nORDER BY %s" ;

	const INSERT_FORMAT = "INSERT INTO %s%s \nVALUES %s" ;

	public function __destruct() {
		$this->disconnect() ;
	}

	/**
	 * Checks if a connection to the database exists. If none, tries to connect to it.
	 * @throws ConnectionException if an error occurs while trying to connect to the database.
	 */
	public abstract function connect() ;

	/**
	 * Closes the connection to the database if any.
	*/
	public abstract function disconnect() ;

	/**
	 * Executes the given Query.
	 * The result returned is always viable, TODOC
	 * @param Query $query
	 * @return QueryResult the result of this Query.
	 * @throws DBException if the execution of this Query fails.
	 * @see Driver
	*/
	public final function query(Query $query) {
		$sql = $this->toSQLString($query) ;
		return $this->sqlQuery($sql) ;
	}

	/**
	 * Executes a SQL query
	 *
	 * @param string $sqlQuery
	 * @return QueryResult the result of this Query.
	 * @throws DBException only TODOC
	*/
	public abstract function sqlQuery($sqlQuery) ;

	/**
	 * 
	 * @param string $string
	 * @return string 
	 */
	public abstract function escape($string) ;
	
	/**
	 * TODOC
	 *
	 * @param Query $query - a query
	 * @return the query string corresponding to the given Query.
	 * @throws QueryException TODOC
	 */
	protected function toSQLString(Query $query) {
		$sqlString = null ;
		$format = '' ;
		$params = array() ;
		if($query instanceof ConditionnedQuery) {
			$this->preCreateSQLString($query) ;
			if($query instanceof Select) {
				$this->selectQuery($query, $format, $params) ;
			} else if($query instanceof Update) {
				$this->updateQuery($query, $format, $params) ;
			} else if($query instanceof Delete) {
				$this->deleteQuery($query, $format, $params) ;
			}
			if($format) {
				$condition = $query->getCondition()->__toString() ;
				if($condition) {
					$format .= self::WHERE_FORMAT ;
					$params[] = $condition ;
				}
				if($query->getOrders()) {
					$format .= self::ORDER_FORMAT ;
					$params[] = $this->implodeList($query->getOrders()) ;
				}
			}
		} else if($query instanceof Insert) {
			$this->insertQuery($query, $format, $params) ;
		}
		if($format) {
			$sqlString = vsprintf($format, $params) ;
			if($query instanceof ConditionnedQuery) {
				$this->postCreateSQLString($sqlString, $query) ;
			}
			return $sqlString ;
		}
		throw new \temple\IllegalArgumentException('The given query of class ' . get_class($query) . ' object is not supported by the driver') ;
	}

	protected function preCreateSQLString(ConditionnedQuery $cQuery) {}

	protected function selectQuery(Select $query, &$format, &$params) {
		$format = self::SELECT_FORMAT ;
		$fields = $query->getFields() ;
		$params = [$fields ? $this->implodeList($fields) : Select::ALL_FIELDS, $query->getTable()] ;
	}

	protected function updateQuery(Update $query, &$format, &$params) {
		$format = self::UPDATE_FORMAT ;
		$params = [$query->getTable(), $this->implodeList($query->getAssignments())] ;
	}

	protected function deleteQuery(Delete $query, &$format, &$params) {
		$format = self::DELETE_FORMAT ;
		$t = $query->getTable() ;
		$params = [$t->getAlias(), $t] ;
	}

	protected function insertQuery(Insert $q, &$format, &$params) {
		$format = self::INSERT_FORMAT ;
		$fs = $q->getFields() ;
		$params = [$q->getTable()->getName(), $fs ? '(' . $this->implodeList($fs) . ')' : '', $this->implodeList($q->getTuples())] ;
	}

	protected function postCreateSQLString(&$sqlString, ConditionnedQuery $cQuery) {}

	/**
	 * TODOC
	 *
	 * @param array $array
	 * @return string
	 */
	protected final function implodeList(array $array) {
		return implode(', ', $array) ;
	}

	/**
	 * 
	 * @param type $impl
	 * @param type $user
	 * @param type $password
	 * @param type $database
	 * @param type $host
	 */
	public static function configure($impl, $user, $password, $database = null, $host = null, $port = null, $charset = null) {
		self::$SUB_NAMESPACE = _dif($impl, self::$SUB_NAMESPACE) ;
		self::$USER = _dif($user, self::$USER) ;
		self::$PASSWORD = _dif($password, self::$PASSWORD) ;
		self::$DATABASE = _dif($database, self::$DATABASE) ;
		self::$HOST = _dif($host, self::$HOST) ;
		self::$PORT = _dif($port, self::$PORT) ;
		self::$CHARSET = _dif($charset, self::$CHARSET) ;
	}
	
}
