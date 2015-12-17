<?php

namespace temple\controller;

use temple\view\JSONResult;
use temple\data\Status;
use temple\controller\AbstractRequestControllerLocale as L;

/**
 * Description of AbstractActionController
 *
 * @author florent
 */
abstract class AbstractActionController extends AbstractRequestController {

    const ID_KEY = 'id';
    const ACTION_KEY = 'action';

	const _ACTION_NAME_KEY = '_action_name' ;
	
    private $includeMessages;
    private $data = [];
    private $feedbacks = [];
    private $reload;
    private $goTo;

    public function __construct($includeMessages = true, $reload = false, $goTo = '', $icon = 'ok') {
        parent::__construct($icon);
        $this->includeMessages = !$reload && $includeMessages;
        $this->reload = $reload;
        $this->goTo = $goTo;
    }

	public function getName() {
		return $this->getLocales()[self::_ACTION_NAME_KEY];
	}
	
    protected final function invalidValue($key, $msg = L::FAIL_INCORRECT_FIELD) {
        $this->feedbacks[$key] = $msg;
    }

    /**
     * 
     * @param type $key
     * @param type $value
     */
    protected final function setData($key, $value) {
        $this->data[$key] = $value;
    }

    /**
     * @param type $goTo
     */
    protected final function setGoTo($goTo) {
        $this->goTo = $goTo;
    }

    public final function createResponse() {
        try {
            if (!$this->isAccessAllowed()) {
                $this->failure(L::FAIL_ACCESS_DENIED, L::FAIL_ACCESS_DENIED_HINT);
            }
			$this->initialize() ;
			if ($this->feedbacks) {
				$this->failure(L::FAIL_PARAMETERS, L::FAIL_PARAMETERS_HINT) ;
			}
            $this->processRequest();
            $r = Status::$SUCCESS;
            $im = $this->includeMessages;
        } catch (\Exception $e) {
			$this->ignore($e) ;
			if($e instanceof \temple\data\persistence\db\DBException) {
				$this->error(L::FAIL_INTERNAL_ERROR) ;
			} else {
	            $this->error($e->getMessage(), $e->getPrevious());
			}
            $r = Status::$ERROR;
            $im = true;
        }
        return new JSONResult($r, $im, $this->data, $this->feedbacks, $this->reload, false, $this->goTo);
    }

    /**
	 * Tells whether access to the controller is allowed.
	 * <br>
	 * Typically, checks the user is signed in or has needed credentials.
	 * <br>
     * Default implementation returns <code>true</code>
     * @return boolean <code>true</code> if the access is allowed, <code>false</code> otherwise.
     */
    protected function isAccessAllowed() {
        return true;
    }

	/**
	 * Gets and sets values from request.<br>
	 * Use invalidValue() to mark invalid POST parameters.
	 */
	protected function initialize() {}
	
    /**
     * @throws \Exception in case of an error
     */
	protected abstract function processRequest() ;
	
	/**
	 * 
	 * @param string $key
	 * @param int $maxLength
	 * @param int $minLength
	 * @param boolean $autoCrop
	 * @return string
	 */
    protected final function postString($key, $maxLength = 0, $minLength = 0, $autoCrop = false) {
        return $this->requestString(INPUT_POST, $key, $maxLength, $minLength, $autoCrop);
    }

	/**
	 * 
	 * @param string $key
	 * @param int $maxLength
	 * @param boolean $required
	 * @param boolean $toLower
	 * @return string
	 */
    protected final function postEmail($key, $maxLength = 0, $required = true, $toLower = true) {
        $e = $this->requestString(INPUT_POST, $key, $maxLength, $required ? 5 : 0, false, FILTER_VALIDATE_EMAIL);
        if ($toLower && $e !== null) {
            $e = strtolower($e);
        }
        return $e;
    }

    /**
     * 
     * @param string $key
	 * @param boolean $required
	 * @param int $minAge
	 * @param int $maxAge
     * @param array $patterns
     * @return \DateTime
     */
    protected final function postDate($key, $required = true, $minAge = null, $maxAge = null, array $patterns = ['Y-m-d', 'd#m#y', 'd#m#Y']) {
        $ds = $this->requestString(INPUT_POST, $key, 0);
        $d = null ;
        // FIXME log if $patterns is empty
        foreach ($patterns as $p) {
            $d = \DateTime::createFromFormat($p, $ds);
            if ($d) {
                break;
            }
        }
        if ($d) {
			$age = \temple\util\DateUtil::age($d) ;
			if($minAge !== null && $d && (intval($minAge) > $age)) {
				$this->invalidValue($key, sprintf(L::FAIL_MIN_AGE, $minAge));
				$d = null ;
			}
			if($maxAge !== null && $d && (intval($maxAge) < $age)) {
				$this->invalidValue($key, sprintf(L::FAIL_MAX_AGE, $maxAge));
				$d = null ;
			}
        } else {
			if($required) {
	            $this->invalidValue($key);
			}
            $d = null;
		}
        return $d;
    }

	/**
	 * 
	 * @param string $key
	 * @param boolean $required
	 * @param int $min
	 * @param int $max
	 * @return int
	 */
    protected final function postInt($key, $required = true, $min = null, $max = null) {
	return $this->postNumber($key, $required, $min, $max) ;
    }

	/**
	 * 
	 * @param string $key
	 * @param boolean $required
	 * @param float $min
	 * @param float $max
	 * @return float
	 */
    protected final function postFloat($key, $required = true, $min = null, $max = null) {
	return $this->postNumber($key, $required, $min, $max, true) ;
    }

    private function postNumber($key, $required = true, $min = null, $max = null, $float = false) {
        $options = [];
        if ($min !== null) {
            $options['min_range'] = $min;
        }
        if ($max !== null) {
            $options['max_range'] = $max;
        }
        $i = filter_input(INPUT_POST, $key, $float ? FILTER_VALIDATE_FLOAT : FILTER_VALIDATE_INT, ['options' => $options]);
        return $this->checkValue($i, $key, $required);
    }

    protected final function postBoolean($key) {
        return $this->requestBoolean(INPUT_POST, $key) ;
    }

	/**
	 * 
	 * @param string $key
	 * @param \ReflectionClass $enumClass
	 * @param boolean $required
	 * @return \temple\Enumeration
	 */
    protected final function postEnum($key, \ReflectionClass $enumClass, $required = true) {
        $i = $this->postInt($key, $required);
        return $i !== null ? $enumClass->getMethod('getByOrdinal')->invoke(null, $i) : null;
    }

	/**
	 * 
	 * @param string $key
	 * @param \ReflectionClass $enumClass
	 * @return array 
	 */
    protected final function postEnums($key, \ReflectionClass $enumClass) {
        $enums = [];
        $ordinals = filter_input(INPUT_POST, $key, FILTER_VALIDATE_INT, ['flags' => FILTER_REQUIRE_ARRAY, 'options' => ['min_range' => 0]]);
		if($ordinals) {
			foreach ($ordinals as $ord) {
				if ($ord === false || $ord === null) {
					// TODO send warning ?
				} else {
					$e = $enumClass->getMethod('getByOrdinal')->invoke(null, $ord);
					if ($e) {
						$enums[] = $e;
					}
				}
			}
		}
        return $enums;
    }

    protected final function postPass($key1, $key2, $maxLength, $minLength) {
        $p = null;
        $p1 = $this->postString($key1, $maxLength, $minLength);
        $p2 = $this->postString($key2, $maxLength, $minLength);
        if ($p1 !== null && $p2 !== null) {
            if ($p1 !== $p2) {
                $this->invalidValue($key1, L::FAIL_PASSES);
                $this->invalidValue($key2, L::FAIL_PASSES);
            } else {
                $p = $p1;
            }
        }
        return $p;
    }

	/**
	 * 
     * @param string $key
     * @param int $maxSize > 0
	 * @param string $dstFile
     * @param boolean $required whether or not the file must exist
	 * @return File
	 */
    protected final function postFile($key, $maxSize, $dstFile, $required = false) {
		$f = null ;
        if (isset($_FILES[$key])) {
            $file = &$_FILES[$key];
            switch ($file['error']) {
                case UPLOAD_ERR_OK :
                    if (!is_uploaded_file($file['tmp_name'])) {
                        throw new \RuntimeException('The file is not been an uploaded file.');
                    }
					if ($file['size'] <= $maxSize) {
						if(move_uploaded_file($file['tmp_name'], $dstFile)) {
							$f = new \temple\util\io\File($dstFile) ;
						} else if($required) {
							$this->invalidValue($key, L::FAIL_UPLOAD_ERROR) ;
						} else {
							$this->warning(L::FAIL_UPLOAD_ERROR) ;
						}
					} else {
						$this->invalidValue($key, sprintf(L::FAIL_FILE_TOO_BIG, $maxSize . ' bytes'));
					}
                    break;
                case UPLOAD_ERR_NO_FILE :
                    if (!$required) {
                        break;
                    }
                default :
                    $this->invalidValue($key, L::FAIL_UPLOAD_ERROR);
            }
        } else {
            if ($required) {
                $this->invalidValue($key);
            }
        }
        return $f;
    }

	/**
	 * 
	 * @param string $key
	 * @param \temple\data\persistence\model\Key $pk
	 * @param boolean $required
	 * @return type
	 */
	protected final function postModel($key, \temple\data\persistence\model\Key $pk, $required = true) {
		$id = $this->postInt($key, false) ;
		$m = $this->getModelManager()->findByKey($pk, $id) ;
		if($required && !$m) {
			$this->failure('Model not found!') ;
		}
		return $m ;
	}
	
}
