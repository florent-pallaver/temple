<?php

namespace temple\data;

use SessionHandler;
use SessionHandlerInterface;

/**
 * Description of Session
 *
 * @author florent
 */
final class Session extends SessionHandler implements SessionHandlerInterface {

    use \temple\LazySingleton;

    /**
     * @var \temple\Logger
     */
    private $logger;
    private $listeners;

    private function __construct() {
        $this->logger = \temple\Logger::getInstance('SESSION');
        $this->listeners = [];
    }

    public function addListener(SessionListener $listener) {
        $this->listeners[] = $listener;
    }

    public function end() {
        if ($this->isActive()) {
            foreach ($this->listeners as $l) {
                $l->sessionClosing();
            }
            session_write_close();
        }
    }

    /**
     * @return boolean true if the session is active, false otherwise.
     */
    public function isActive() {
        return session_status() === PHP_SESSION_ACTIVE;
    }

    public function keyExists($key) {
        $this->lazyStart();
        return isset($_SESSION[$key]);
    }

    public function set($key, $value) {
        $this->lazyStart();
        if ($value === null) {
            unset($_SESSION[$key]);
        } else {
            $_SESSION[$key] = $value;
        }
    }

    /**
     * @param scalar $key
     * @return mixed the value associated to the given key, null if none exists.
     */
    public function get($key) {
        $this->lazyStart();
        return _iod($_SESSION, $key);
    }

    /**
     * Resets the current session (i.e. empties $_SESSION)
     */
    public function reset() {
        $this->lazyStart();
        $_SESSION = [];
    }

    private function lazyStart() {
        if (!$this->isActive()) {
            $this->logger->debug('Starting Session');
            session_set_save_handler($this, true);
            if (session_start()) {
                $this->logger->debug('Session started');
            } else {
                $this->logger->severe('Unable to start session');
            }
        }
    }

    public function read($session_id) {
        $s = parent::read($session_id);
        $a = [];
        if ($s) {
            $a = unserialize($s);
            if (!is_array($a)) {
                $a = [];
            }
        }
        $_SESSION = $a;
        return '';
    }

    public function write($session_id, $session_data) {
        return parent::write($session_id, serialize($_SESSION));
    }

    public function gc($maxlifetime) {
        return parent::gc($maxlifetime);
    }

}
