<?php

namespace temple\controller;

use temple\controller\AbstractRequestControllerLocale as L ;

/**
 * Description of AbstractController
 *
 * @author florent
 */
abstract class AbstractRequestController extends AbstractController {

	protected static $default0 = ['default' => 0];
	protected static $defaultEmpty = ['default' => ''];
	protected static $defaultEmptyArray = ['default' => []];

	/**
	 * Use when string must not be empty and HTML needs to be escaped.
	 * @var array
	 */
	protected static $_notEmptyHTML = ['notEmpty' => true];

	/**
	 * Use when HTML does not need to be escaped.
	 * @var array
	 */
	protected static $_NotHTML = ['escapeHTML' => false];

	/**
	 * Use when string must not be empty and HTML does not need to be escaped.
	 * @var array
	 */
	protected static $_notEmptyNotHTML = ['notEmpty' => true, 'escapeHTML' => false];

	protected final function redirect($to ='/', $ensureSession = true) {
		$session = \temple\data\Session::getInstance() ;
		if($ensureSession) {
			session_start() ;
			$session->end();
		}
		header('location: ' . $to) ;
	}
	
	protected final function getBooleanGetParam($name) {
		return $this->getBooleanParam(INPUT_GET, $name);
	}

	protected final function getIntGetParam($name, array $options = []) {
		return $this->getNumberParam(INPUT_GET, $name, $options);
	}

	protected final function getStringGetParam($name, array $options = []) {
		return $this->getStringParam(INPUT_GET, $name, $options);
	}

	protected final function getBooleanPostParam($name) {
		return $this->getBooleanParam(INPUT_POST, $name);
	}

	protected final function getIntPostParam($name, array $options = []) {
		return $this->getNumberParam(INPUT_POST, $name, $options);
	}

	protected final function getFloatPostParam($name, array $options = []) {
		return $this->getNumberParam(INPUT_POST, $name, $options, true);
	}

	protected final function getEnumPostParam($name, \ReflectionClass $enumClass, array $options = []) {
		return $this->getEnumParam(INPUT_POST, $name, $enumClass, $options);
	}

	protected final function getStringPostParam($name, array $options = []) {
		return $this->getStringParam(INPUT_POST, $name, $options);
	}

	protected final function getArrayPostParam($name, array $options = []) {
		return $this->getArrayParam(INPUT_POST, $name, $options);
	}

	protected final function getFilesParam($name, $max = 0) {
		if (!isset($_FILES[$name])) {
			$this->paramFailed($name);
		}
		if(is_array($_FILES[$name]['error'])) {
			$params = [];
			foreach ($_FILES[$name] as $k => $values) {
				foreach ($values as $i => $v) {
					if ($i < $max || $max == 0) {
						$params[$i][$k] = $v;
					}
				}
			}
			if ($max > 0 && count($_FILES[$name]['error']) > $max) {
				$this->info(sprintf(L::INFO_FILES_LIMIT, $max));
			}
		} else {
			$params = &$_FILES[$name];
		}
		return $params;
	}

	private final function getBooleanParam($type, $name) {
		$b = filter_input($type, $name, FILTER_VALIDATE_BOOLEAN, array('default' => false));
		$_POST[$name] = $b === true;
		return $b === true;
	}

	private final function getNumberParam($type, $name, array $options, $float = false) {
		$n = filter_input($type, $name, $float ? FILTER_VALIDATE_FLOAT : FILTER_VALIDATE_INT, $options);
		if ($n || $n === 0) {
			return $n;
		}
		if (array_key_exists('default', $options)) {
			return $options['default'];
		}
		$this->paramFailed($name);
	}

	private final function getEnumParam($type, $name, \ReflectionClass $enumClass, array $options) {
		$ordinal = $this->getNumberParam($type, $name, ['default'=>-1], false) ;
		$e = $enumClass->getMethod('getByOrdinal')->invoke(null, $ordinal) ;
		if($e) {
			return $e;
		}
		if (array_key_exists('default', $options)) {
			return $options['default'];
		}
		$this->paramFailed($name);
	}
	
	private final function getStringParam($type, $name, array $options) {
		$str = filter_input($type, $name);
		if ($str !== null && is_string($str)) {
			return $str;
		}
		if (array_key_exists('default', $options)) {
//			if($type == INPUT_POST) {
//				$_POST[$name] = $options['default'];
//			}
			return $options['default'];
		}
		$this->paramFailed($name);
	}

	private final function getArrayParam($type, $name, array $options) {
		$arr = filter_input($type, $name, FILTER_DEFAULT, FILTER_REQUIRE_ARRAY);
		if ($arr !== null && is_array($arr)) {
			return $arr;
		}
		if (array_key_exists('default', $options)) {
			return $options['default'];
		}
		$this->paramFailed($name);
	}

	private final function paramFailed($name) {
		$this->failure(sprintf(L::FAIL_INCORRECT_FIELD, $name));
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
		throw new ActionException($this->getActionDescription(), $reason, $hint, $e);
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
		return \temple\web\mail\Mailer::getInstance() ;
	}
	
	protected final function getCheckedDate($key, $pattern, $minAge = 0) {
		$birthdate = trim($this->getStringPostParam($key));
		$bdate = null;
		if (strlen($birthdate) > 0) {
			// $ as delimiter cause / is in the pattern
			if (preg_match('$' . $pattern . '$', $birthdate, $date)) {
				$bdate = $date[9] . '-' . $this->trailZero($date[5]) . '-' . $this->trailZero($date[1]);
				$age = \paris\model\ModelHelper::getAge($bdate);
				if ($minAge > 0 && $age < $minAge) {
					$bdate = null;
					$this->warning(sprintf(L::WARN_AGE, $birthdate, $minAge));
				}
			} else {
				$this->warning(sprintf(L::WARN_INVALID_DATE, $birthdate));
			}
		}
		return $bdate;
	}

	private function trailZero($str) {
		if (strlen($str) == 1) {
			$s = '0' . $str;
		} else {
			$s = $str;
		}
		return $s;
	}

	// FIXME revoir les fonctions pour qu'elles incluent les noms des paramètres
	// FIXME revoir les fonctions pour que les feedbacks soient sur les champs
	
	/**
	 * TODOC 
	 * 
	 * @param type $key
	 * @param type $maxLength
	 * @param type $paramName
	 * @return string
	 * @throws ActionException if $notEmpty is true and the checked string is empty
	 */
	protected final function getCheckedString($key, $maxLength, $paramName = '', $options = []) {
		$str = trim($this->getStringPostParam($key));
		$pattern = _iod($options, 'pattern', '');
		if ($pattern) {
			$c = 0;
			$r = _iod($options, 'replacement', '');
			$str = preg_replace($pattern, $r, $str, -1, $c);
			if ($c && $paramName) {
				$this->info(sprintf(L::INFO_INVALID_CHARS, $paramName, $str));
			}
		}
		if (_iod($options, 'escapeHTML', true)) {
			$str = htmlspecialchars($str, ENT_QUOTES);
		}
		if (_iod($options, 'notEmpty', false) && strlen($str) == 0) {
			$this->failure(sprintf(L::FAIL_EMPTY_FIELD, $paramName));
		}
//		$e = $this->escapeSQL($str) ;
		if ($maxLength > 0 && strlen($str) > $maxLength) {
			$str = substr($str, 0, $maxLength);
			if ($paramName) {
				$this->info(sprintf(L::INFO_MAX_LENGTH, $paramName, $maxLength));
			}
		}
		return $str;
//		$str = $this->ensureString($post, $maxLength, $paramName);
//		if ($notEmpty && strlen($str) == 0) {
//			throw new ActionException('perform action', "The field '$paramName' cannot be empty.");
//		}
//		return $str;
	}
	
}
