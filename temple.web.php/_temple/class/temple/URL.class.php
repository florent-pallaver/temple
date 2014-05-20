<?php

namespace temple;

/**
 * Description of URL
 *
 * @author florent
 */
final class URL {

	const VIEW_PARAMETER = '_VIEW' ;
	
	const PATH_PARAMETER = '_PATH' ;
	
	const ACTION_PARAMETER = '_ACTION' ;
	
    private $script ;
    
    private $parameters = [] ;
    
	private $fragment ;
	
    private function __construct($script) {
        $this->script = $script === null ? explode('?', $_SERVER['REQUEST_URI'])[0] : $script;
    }

    /**
     * 
     * @param type $name
     * @param type $value
     * @return \temple\URL
     */
    public function setParam($name, $value = null) {
        $this->parameters[$name] = $value ;
        return $this ;
    }
    
	/**
	 * 
	 * @param array $params
	 * @return \temple\URL
	 */
	public function setParams(array $params) {
        foreach($params as $k => $v) {
            $this->setParam($k, $v) ;
        }
		return $this ;
	}
	
	/**
	 * 
	 * @param type $anchor
	 * @return \temple\URL
	 */
	public function setAnchor($anchor) {
		$this->fragment = $anchor ;
		return $this ;
	}
	
    /**
     * 
     * @param type $name
     * @return \temple\URL
     */
    public function unsetParam($name) {
        unset($this->parameters[$name]) ;
        return $this ;
    }

    public function __toString() {
        $str = $this->script ;
        if($this->parameters) {
            $params = array() ;
            foreach ($this->parameters as $key => $value) {
                $params[] = $key . '=' . urlencode($value) ;
            }
            $str .= '?' . implode('&', $params) ;
        }
		if($this->fragment) {
			$str .= '#' . $this->fragment ;
		}
        return $str ;
    }
    
    /**
     * 
     * @return \temple\URL
     */
    public static function createFromCurrentURL(array $params = null) {
        return self::create(null, $params === null ? $_GET : $params) ;
    }

    /**
     * 
     * @param type $script
     * @param array $parameters
     * @return \temple\URL
     */
    public static function create($script, array $parameters = [], $anchor = '') {
        $url = new URL($script) ;
		$url->setParams($parameters) ;
		$url->unsetParam(self::VIEW_PARAMETER) ;
		$url->unsetParam(self::PATH_PARAMETER) ;
		$url->fragment = $anchor ;
        return $url ;
    }

}