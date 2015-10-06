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
    const MIN_PASS_LENGTH = 8;
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
	
    /**
     * 
     * @param type $key
     * @param type $value
     */
    protected final function setData($key, $value) {
        $this->data[$key] = $value;
    }

    /**
     * 
     * @param type $key
     * @param type $value
     */
    protected final function setFeedback($key, $value = L::FAIL_INCORRECT_FIELD) {
        $this->feedbacks[$key] = $value;
    }

    /**
     * @param type $goTo
     */
    protected final function setGoTo($goTo) {
        $this->goTo = $goTo;
    }

    /**
     * Default implementation returns <code>false</code>
     * @return boolean <code>true</code> if the access is denied, <code>false</code> otherwise.
     */
    protected function isDenied() {
        return false;
    }

    /**
     * @throws \Exception in case of an error
     */
    protected abstract function processRequest();

    public final function createResponse() {
        try {
            if ($this->isDenied()) {
                $this->failure(L::FAIL_ACCESS_DENIED, L::FAIL_ACCESS_DENIED_HINT);
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

    protected final function checkFeedback() {
        if ($this->feedbacks) {
            $this->failure();
        }
    }

    protected final function postString($key, $maxLength = 0, $minLength = 0, $autoCrop = false) {
        return $this->postString0($key, $maxLength, $minLength, $autoCrop);
    }

    protected final function postEmail($key, $maxLength = 0, $required = true, $toLower = true) {
        $e = $this->postString0($key, $maxLength, $required ? 5 : 0, false, FILTER_VALIDATE_EMAIL);
        if ($toLower && $e !== null) {
            $e = strtolower($e);
        }
        return $e;
    }

    private function postString0($key, $maxLength, $minLength = 0, $autoCrop = false, $filter = FILTER_DEFAULT) {
        $str = $this->checkValue(filter_input(INPUT_POST, $key, $filter), $key, $minLength > 0);
        if ($str !== null) {
            if (strlen($str) < $minLength) {
                $this->setFeedback($key, sprintf(L::FAIL_MIN_LENGTH, $minLength));
                $str = null;
            } else {
                if ($maxLength > 0 && strlen($str) > $maxLength) {
                    if ($autoCrop) {
                        $str = substr($str, 0, $maxLength);
                    } else {
                        $this->setFeedback($key, sprintf(L::FAIL_MAX_LENGTH, $maxLength));
                        $str = null;
                    }
                }
            }
        }
        return $str;
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
        $ds = $this->postString0($key, 0);
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
				$this->setFeedback($key, sprintf(L::FAIL_MIN_AGE, $minAge));
				$d = null ;
			}
			if($maxAge !== null && $d && (intval($maxAge) < $age)) {
				$this->setFeedback($key, sprintf(L::FAIL_MAX_AGE, $maxAge));
				$d = null ;
			}
        } else {
			if($required) {
	            $this->setFeedback($key);
			}
            $d = null;
		}
        return $d;
    }

    protected final function postInt($key, $required = true, $min = null, $max = null) {
        $options = [];
        if ($min !== null) {
            $options['min_range'] = $min;
        }
        if ($max !== null) {
            $options['max_range'] = $max;
        }
        $i = filter_input(INPUT_POST, $key, FILTER_VALIDATE_INT, $options);
        return $this->checkValue($i, $key, $required);
    }

    protected final function postBoolean($key) {
        return filter_input(INPUT_POST, $key, FILTER_VALIDATE_BOOLEAN) === true;
    }

    protected final function postEnum($key, \ReflectionClass $enumClass, $required = true) {
        $i = $this->postInt($key, $required);
        return $i !== null ? $enumClass->getMethod('getByOrdinal')->invoke(null, $i) : null;
    }

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

    protected final function postPass($key1, $key2, $maxLength, $minLength = self::MIN_PASS_LENGTH) {
        $p = null;
        $p1 = $this->postString($key1, $maxLength, $minLength);
        $p2 = $this->postString($key2, $maxLength, $minLength);
        if ($p1 !== null && $p2 !== null) {
            if ($p1 !== $p2) {
                $this->setFeedback($key1, L::FAIL_PASSES);
                $this->setFeedback($key2, L::FAIL_PASSES);
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
     * @param boolean $required whether or not the file must exist
     * @return string the path to the valid uploaded file
     */
    protected final function checkUpload($key, $maxSize, $required = false) {
        $fn = null;
        if (isset($_FILES[$key])) {
            $file = &$_FILES[$key];
            switch ($file['error']) {
                case UPLOAD_ERR_OK :
                    if (is_uploaded_file($file['tmp_name'])) {
                        if ($file['size'] <= $maxSize) {
                            $fn = $file['tmp_name'];
                        } else {
                            $this->setFeedback($key, sprintf(L::FAIL_FILE_TOO_BIG, $maxSize . ' bytes'));
                        }
                    } else {
                        throw new \RuntimeException('The file is not been an uploaded file.');
                    }
                    break;
                case UPLOAD_ERR_NO_FILE :
                    if (!$required) {
                        break;
                    }
                default :
                    $this->setFeedback($key, L::FAIL_UPLOAD_ERROR);
            }
        } else {
            if ($required) {
                $this->setFeedback($key);
            }
        }
        return $fn;
    }

    protected final function postFile($key, $maxSize, $dstFile, $required = false) {
        $fn = $this->checkUpload($key, $maxSize, $required);
        if ($fn) {
            move_uploaded_file($fn, $dstFile);
			if($required) {
				$this->setFeedback($key, L::FAIL_UPLOAD_ERROR) ;
			}
        }
        return $fn;
    }

    // check FALSE and NULL only to return NULL only if value is one of those
    private function checkValue($value, $key, $required) {
        if (($value === FALSE || $value === NULL)) {
            if ($required) {
                // generic message here cause we don't know what might have cause the error
                $this->setFeedback($key);
            }
            $value = NULL;
        }
        return $value;
    }

}
