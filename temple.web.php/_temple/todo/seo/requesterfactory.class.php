<?php

final class RequesterFactory {

	private function __construct() {}

	public static function createRequester($engine, $nr = 100) {
		switch($engine) {
			case 'Yahoo':
				$r = new DefaultRequester('au.search.yahoo.com', 'search?p=%s&n=%s', '//h3/a[@id]/@href', $nr) ;
				break ;
			case 'Bing':
				$r = new DefaultRequester('www.bing.com', 'search?q=%s', '//h3/a/@href', $nr) ;
				break ;
			default:
				logThat('RequesterFactory: ' . $engine . ' given, Google used instead.') ;
			case 'Google':
				$r = new GoogleRequester($nr) ;
				break ;
		}
		return $r ;
	}
	
}

class DefaultRequester extends AbstractRequester {

	public function __construct($host, $urlFormat, $xpath, $nr, $uc = false) {
		parent::__construct($host, $urlFormat, $xpath, $nr, $uc) ;
	}

	public function setPreferences() {}
	
}

class GoogleRequester extends DefaultRequester {

	public function __construct($nr) {
		parent::__construct('www.google.com.au', 'search?q=%s&num=%s', '//h3[@class="r"]/a/@href', $nr, true) ;
	}

	public function setPreferences() {
		$data = $this->get('preferences') ;

		$doc = new DOMDocument();
		@$doc->loadHTML($data) ;
			
		$xpath = new DOMXPath($doc); 
		$params = array() ;
		foreach($xpath->query('//input', $doc->getElementById('ssform')) as $node) {
			if($node->getAttribute('type') != 'checkbox' || $node->hasAttribute('checked')) {
				switch($node->getAttribute('name')) {
					case 'suggon':
						$node->setAttribute('value', 2) ;
						break;
					case 'num':
						$node->setAttribute('value', 100) ;
						break;
					default:
				}
				$params[] = $node->getAttribute('name') . '=' . urlencode($node->getAttribute('value')) ; 
			}
		}
		sleep(5) ;
		return $this->get('setprefs?' . implode('&', $params)) ;
	}

}

?>