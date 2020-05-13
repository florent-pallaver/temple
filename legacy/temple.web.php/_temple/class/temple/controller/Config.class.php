<?php

namespace temple\controller;

/**
 * Description of Config
 *
 * @author flominou
 */
final class Config {

    public static $DEFAULT_LOCALE = 'en';
    public static $LOCALE = 'en';

    /**
     * @var string starts and ends without \
     */
    public static $CONTROLLERS_BASE_NAMESPACE = 'controller';

    const CONTROLLER_CLASS_SUFFIX = 'Controller';

    const CONTROLLER_LOCALE_CLASS_SUFFIX = 'Locale';

	public static $VIEW_ACTION = '_view' ;
	
    private function __construct() {
        
    }

    /**
     * 
     * @param string $class
	 * @param string $objectPath does not start with <code>/</code>
     * @return \temple\URL
     */
    public static function getURL($class, $objectPath = '') {
        $params = [];
        $script = '/';
        try {
            $controllerClass = new \ReflectionClass($class);
            if ($controllerClass->isSubclassOf(AbstractController::class)) {
                $class = $controllerClass->getName();
                $offset = strlen(Config::$CONTROLLERS_BASE_NAMESPACE) + 1;
                $script = '/' . _fctlc(str_replace('\\', '/', substr($class, $offset, strlen($class) - $offset - strlen(Config::CONTROLLER_CLASS_SUFFIX))));
                if ($controllerClass->isSubclassOf(AbstractActionController::class)) {
                    $pos = strrpos($script, '/');
                    $params = [_fctlc(substr($script, $pos + 1)) => null];
                    $script = substr($script, 0, $pos);
                } else if($objectPath) {
					$script .= '/' .$objectPath ;
				}
            }
        } catch (\Exception $ex) {
            \temple\ExceptionHandler::log($ex);
        }
        return \temple\URL::create($script, $params);
    }

}
