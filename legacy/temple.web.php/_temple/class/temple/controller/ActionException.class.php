<?php

namespace temple\controller;

use temple\controller\ActionLocale as L ;

/**
 * Description of ActionException
 *
 * @author florent
 */
final class ActionException extends \temple\TempleException {

    /**
     * 
     * @param string $failureMessage
     * @param string $reason
     * @param string $hint
     */
    public function __construct($failureMessage, $reason = '', $hint = '', \Exception $cause = null) {
        parent::__construct(sprintf(L::MSG_FORMAT, $failureMessage, self::nl($reason), self::nl($hint)), 0, $cause);
    }
    
    // prepend a new line if string not empty
    private static function nl($str) {
        return $str ? PHP_EOL . $str : $str ;
    }
    
}
