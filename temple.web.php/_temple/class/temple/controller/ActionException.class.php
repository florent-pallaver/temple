<?php

namespace temple\controller;

/**
 * Description of ActionException
 *
 * @author florent
 */
final class ActionException extends \temple\TempleException {

    const MSG_FORMAT = "<strong>Unable to %s.</strong>\n%s%s\n\n<em>Please contact us if this error unexpectedly persists</em>." ;
    
    /**
     * 
     * @param string $failedAction
     * @param string $reason
     * @param string $hint
     */
    public function __construct($failedAction, $reason = '', $hint = '', \Exception $cause = null) {
        parent::__construct(sprintf(self::MSG_FORMAT, $failedAction, self::nl($reason), self::nl($hint)), 0, $cause);
    }
    
    // prepend a new line if string not empty
    private static function nl($str) {
        return $str ? PHP_EOL . $str : $str ;
    }
    
}
