<?php

namespace temple\controller;

use temple\controller\AbstractRequestControllerLocale as L;

/**
 * Description of AbstractController
 *
 * @author florent
 */
abstract class AbstractRequestController extends AbstractController {

	private $icon;
	private $name;

	public function __construct($icon = null, $name = null) {
		parent::__construct() ;
		$this->icon = _dif($icon, 'th-large');
		if ($name) {
			$this->name = $name;
		} else {
			$s = substr(static::class, strrpos(static::class, '\\') + 1);
			$this->name = substr($s, 0, strlen($s) - strlen(Config::CONTROLLER_CLASS_SUFFIX));
		}
	}

	public function getIcon() {
		return $this->icon;
	}

	public function getName() {
		return $this->name;
	}

	protected final function redirect($to = '/', $ensureSession = true) {
		$session = \temple\data\Session::getInstance();
		if ($ensureSession) {
//            session_start();
			$session->end();
		}
		header('location: ' . $to);
		exit();
	}

	/**
	 * 
	 * @param int $type one of INPUT_GET, INPUT_POST, INPUT_SESSION, INPUT_COOKIE etc
	 * @param string $key
	 * @param int $maxLength
	 * @param int $minLength
	 * @param boolean $autoCrop
	 * @param int $filter filter mask TODOC
	 * @return string
	 */
	protected final function requestString($type, $key, $maxLength, $minLength = 0, $autoCrop = false, $filter = FILTER_DEFAULT) {
		$str = $this->checkValue(trim(filter_input($type, $key, $filter)), $key, $minLength > 0);
		if ($str !== null) {
			if (strlen($str) < $minLength) {
				$this->invalidValue($key, sprintf(L::FAIL_MIN_LENGTH, $minLength));
				$str = null;
			} else {
				if ($maxLength > 0 && strlen($str) > $maxLength) {
					if ($autoCrop) {
						$str = substr($str, 0, $maxLength);
					} else {
						$this->invalidValue($key, sprintf(L::FAIL_MAX_LENGTH, $maxLength));
						$str = null;
					}
				}
			}
		}
		return $str;
	}

	/**
	 * 
	 * @param int $type one of INPUT_GET, INPUT_POST, INPUT_SESSION, INPUT_COOKIE etc
	 * @param string $key
	 * @param boolean $required
	 * @param int $min
	 * @param int $max
	 * @return int
	 */
	protected final function requestInt($type, $key, $required = false, $min = null, $max = null) {
        $options = [];
        if ($min !== null) {
            $options['min_range'] = $min;
        }
        if ($max !== null) {
            $options['max_range'] = $max;
        }
        $i = filter_input($type, $key, FILTER_VALIDATE_INT, $options);
        return $this->checkValue($i, $key, $required);
    }
	
	/**
	 * Check FALSE and NULL values only to return NULL only if value is one of those.
	 * <br>
	 * Also sets feedback 
	 * @param mixed $value
	 * @param string $key
	 * @param boolean $required
	 * @return mixed
	 */
	protected final function checkValue($value, $key, $required) {
		if (($value === FALSE || $value === NULL)) {
			if ($required) {
				// generic message here cause we don't know what might have cause the error
				$this->invalidValue($key);
			}
			$value = NULL;
		}
		return $value;
	}

    /**
     * 
     * @param type $key
     * @param type $msg
     */
	protected function invalidValue($key, $msg = L::FAIL_INCORRECT_FIELD) {
		$this->failure(L::FAIL_INVALID_REQUEST, $msg) ;
		$this->logger->warning($key . ' is invalid') ;
	}
	
//	 * @param int $maxLength
//	 * @param int $minLength
//	 * @param boolean $autoCrop
	/**
	 * 
	 * @param string $key
	 * @return string
	 */
	protected final function queryString($key) {
		return $this->requestString(INPUT_GET, $key, 0, 0, false);
	}

//	 * @param boolean $required
	/**
	 * 
	 * @param string $key
	 * @param int $min
	 * @param int $max
	 * @return int
	 */
	protected final function queryInt($key, $min = null, $max = null) {
		return $this->requestInt(INPUT_GET, $key, false, $min, $max) ;
	}
	
	/**
	 * Throws an ActionException causing this controller to fail.
	 * 
	 * @param string $reason the reason why this controller failed
	 * @param string $hint an hint in order to avoid the failure
	 * @param \Exception $e the exception which caused the failure
	 * @throws ActionException always
	 */
	protected final function failure($reason = null, $hint = '', \Exception $e = null) {
		throw new ActionException($this->getFailureMessage(), $reason, $hint, $e);
	}

	/**
	 * @return \temple\data\persistence\db\Driver
	 */
	protected final function getDbDriver() {
		return \temple\data\persistence\db\Driver::getInstance();
	}

	/**
	 * @return \temple\data\persistence\db\query\QueryFactory
	 */
	protected final function getQueryFactory() {
		return \temple\data\persistence\db\query\QueryFactory::getInstance();
	}

	/**
	 * @return \temple\data\persistence\model\ModelManager
	 */
	protected final function getModelManager() {
		return \temple\data\persistence\model\ModelManager::getInstance();
	}

	/**
	 * @return \temple\web\mail\Mailer
	 */
	protected final function getMailer() {
		return \temple\web\mail\Mailer::getInstance();
	}

}
