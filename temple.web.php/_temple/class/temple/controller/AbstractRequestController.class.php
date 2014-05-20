<?php

namespace temple\controller;

/**
 * Description of AbstractController
 *
 * @author florent
 */
abstract class AbstractRequestController extends AbstractController {

	protected static $default0 = ['default' => 0];
	
	protected static $defaultEmptyArray = ['default' => []] ;
	
	/**
	 * Use when string must not be empty.
	 * @var array
	 */
	protected static $_notEmptyHTML = ['notEmpty' => true] ;

	/**
	 * Use when HTML does not need to be escaped.
	 * @var array
	 */
	protected static $_NotHTML = ['escapeHTML' => false] ;

	/**
	 * Use when string must not be empty and HTML does not need to be escaped.
	 * @var array
	 */
	protected static $_notEmptyNotHTML = ['notEmpty' => true, 'escapeHTML' => false] ;
	
	protected final function getBooleanGetParam($name) {
		return $this->getBooleanParam(INPUT_GET, $name);
	}

	protected final function getIntGetParam($name, array $options = []) {
		return $this->getIntParam(INPUT_GET, $name, $options);
	}

	protected final function getStringGetParam($name, array $options = []) {
		return $this->getStringParam(INPUT_GET, $name, $options);
	}

	protected final function getBooleanPostParam($name) {
		return $this->getBooleanParam(INPUT_POST, $name);
	}

	protected final function getIntPostParam($name, array $options = []) {
		return $this->getIntParam(INPUT_POST, $name, $options);
	}

	protected final function getStringPostParam($name, array $options = []) {
		return $this->getStringParam(INPUT_POST, $name, $options);
	}

	protected final function getArrayPostParam($name, array $options = []) {
		return $this->getArrayParam(INPUT_POST, $name, $options) ;
	}
	
	protected final function getFilesParam($name, $max = 0) {
		if (!isset($_FILES[$name])) {
			$this->paramFailed($name);
		}
		$params = [];
		foreach ($_FILES[$name] as $k => $values) {
			foreach ($values as $i => $v) {
				if ($i < $max || $max == 0) {
					$params[$i][$k] = $v;
				}
			}
		}
		if (count($_FILES[$name]['error']) > $max) {
			$this->info("Only $max files can be uploaded at a time, the extra ones have been ignored.");
		}
		return $params;
	}

	private final function getBooleanParam($type, $name) {
		$b = filter_input($type, $name, FILTER_VALIDATE_BOOLEAN, array('default' => false));
		$_POST[$name] = $b === true;
		return $b === true;
	}

	private final function getIntParam($type, $name, array $options) {
		$i = filter_input($type, $name, FILTER_VALIDATE_INT, $options);
		if ($i || $i === 0) {
			return $i;
		}
		if (array_key_exists('default', $options)) {
//			$_POST[$name] = $options['default'];
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
		$this->failure("Parameter $name is incorrect.");
	}

	protected final function failure($reason = null) {
		throw new ActionException($this->getActionDescription(), $reason) ;
	}
	
	/**
	 * @return \temple\data\persistence\db\Driver
	 */
	protected final function getDbDriver() {
		return \temple\data\persistence\db\Driver::getInstance() ;
	}

	/**
	 * @return \temple\data\persistence\db\query\QueryFactory
	 */
	protected final function getQueryFactory() {
		return \temple\data\persistence\db\query\QueryFactory::getInstance() ;
	}
	
	/**
	 * @return \temple\data\persistence\model\ModelManager
	 */
	protected final function getModelManager() {
		return \temple\data\persistence\model\ModelManager::getInstance() ;
	}
	
	// MIGHT BE OBSOLETE
	
	protected final function getCheckedDate($key) {
		$birthdate = trim($this->getStringPostParam($key));
		$bdate = null;
		if (strlen($birthdate) > 0) {
			// $ as delimiter cause / is in the pattern
			if (preg_match('$' . \paris\model\Profile::BIRTHDATE_PATTERN . '$', $birthdate, $date)) {
				$bdate = '19' . $date[9] . '-' . $this->trailZero($date[5]) . '-' . $this->trailZero($date[1]);
				$age = \paris\model\ModelHelper::getAge($bdate);
				if ($age < 18) {
					$bdate = null;
					$this->warning("'$birthdate' is not a valid birthdate.\nIf you are not at least 18 you are breaking the terms and conditions of use of this website.");
				}
			} else {
				$this->warning("'$birthdate' is not a valid birthdate.\nPlease remember that the format is DAY/MONTH/YEAR or DAY-MONTH-YEAR. For instance 15-5-84 if you were born the 15th of May in 1984.");
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

	/**
	 * TODOC 
	 * 
	 * @param type $key
	 * @param type $maxLength
	 * @param type $paramName
	 * @param type $notEmpty
	 * @return string
	 * @throws ActionException if $notEmpty is true and the checked string is empty
	 */
	protected final function getCheckedString($key, $maxLength, $paramName = '', $options = []) {
		$str = trim($this->getStringPostParam($key)) ;
		$pattern = _iod($options, 'pattern', '') ;
		if($pattern) {
			$c = 0 ;
			$r = _iod($options, 'replacement', '') ;
			$str = preg_replace($pattern, $r, $str, -1, $c) ;
			if($c && $paramName) {
				$this->info("$paramName '$str' contained invalid characters, they have been replaced.") ;
			}
		}
		if(_iod($options, 'escapeHTML', true)) {
			$str = htmlspecialchars($str, ENT_QUOTES) ;
		}
		if(_iod($options, 'notEmpty', false) && strlen($str) == 0) {
			throw new ActionException('perform action', "The field '$paramName' cannot be empty.");
		}
//		$e = $this->escapeSQL($str) ;
		if ($maxLength > 0 && strlen($e) > $maxLength) {
			$e = substr($e, 0, $maxLength);
			if ($paramName) {
				$this->info("The field '$paramName' exceeded the $maxLength characters length limit and has been truncated.");
			}
		}
		return $e;

		
		
		$str = $this->ensureString($post, $maxLength, $paramName);
		if ($notEmpty && strlen($str) == 0) {
			throw new ActionException('perform action', "The field '$paramName' cannot be empty.");
		}
		return $str;
	}
	
}
