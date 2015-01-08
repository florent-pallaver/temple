<?php

namespace temple\data;

use Exception;

/**
 * Description of Messages
 *
 * @author florent
 */
final class Messages implements SessionListener {

	use \temple\LazySingleton;

	private static $CAUSE_FORMAT = "%s.\nCause: %s";
	private static $sessionKey = '_messages|';
	private $registry;

	/**
	 * @var Session
	 */
	private $session;

	private function __construct() {
		$this->registry = [];
		$this->session = Session::getInstance();
		$this->session->addListener($this) ;
		if ($this->session->isActive() && $this->session->keyExists(self::$sessionKey)) {
			$this->registry = $this->session->get(self::$sessionKey);
			$this->session->set(self::$sessionKey, null);
		}
	}

	public function success($msg) {
		$this->register($msg, Status::$SUCCESS);
	}

	public function info($msg) {
		$this->register($msg, Status::$INFO);
	}

	public function warning($msg, Exception $e = null) {
		$this->register($msg, Status::$WARNING, $e);
	}

	public function error($msg, Exception $e = null) {
		$this->register($msg, Status::$ERROR, $e);
	}

	public function register($msg, Status $status, Exception $e = null) {
		$m = $e ? sprintf(self::$CAUSE_FORMAT, $msg, $e->getMessage()) : strval($msg);
		$this->registry[$status ? $status->getOrdinal() : 0][] = $m;
	}

	/**
	 * @return boolean 
	 */
	public function hasMessages() {
		return count($this->registry) > 0;
	}

	/**
	 * 
	 * @return array - all the registered messages and empty the internal array
	 */
	public function popAll() {
		$r = $this->registry;
		$this->registry = [];
		return $r;
	}

	public function sessionClosing() {
		// Saves every unpoped messages to the session
		if ($this->registry && $this->session->isActive()) {
			$this->session->set(self::$sessionKey, $this->popAll());
		}
	}
	
}
