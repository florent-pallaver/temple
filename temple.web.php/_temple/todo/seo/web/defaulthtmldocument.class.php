<?php

namespace web ;

use \Util ;
use \DOMDocument ;
use \DOMXPath ;

use \data\model\ProxyModel ;

class DefaultHTMLDocument implements HTMLDocument {

	private $url ;

	private $xpath ;
	
	// throws HTTPGetException
	public function __construct($url, ProxyModel $proxy = null) {
		$this->url = $url ;
		$document = new DOMDocument() ;
		$document->loadHTML((new DefaultHTTPGetter(Util::extractDomain($url), false))->get($url, $proxy)) ;
		$this->xpath = new DOMXPath($document) ;
	}
	
	// return string
	public function getURL() {
		return $this->url ;
	}
	
	// return boolean
	public function xpathExist($xpath) {
		$xps = is_array($xpath) ? $xpath : array($xpath) ;
		$b = true ;
		foreach($xps as $xp) {
			if($this->xpathCount($xp) == 0) {
				$b = false ;
				break ;
			}
		}
		return $b ;
	}
	
	// return integer
	public function xpathCount($xpath) {
		return $this->xpathQuery($xpath)->length ;
	}
	
	// return DOMNodeList
	public function xpathQuery($xpath) {
		return $this->xpath->query($xpath) ;
	}
	
	public function save($path) {
		return $this->xpath->document->saveHTMLFile($path) ;
	}

	public static function _init() {
		libxml_use_internal_errors(true) ;
	}
	
}

?>