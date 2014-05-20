<?php

abstract class AbstractRequester implements Requester {

	private $rootURL ;

	private $queryFormat ;
	
	private $numberOfResults ;

	private $urlsXPath ;
	
	private $useCookies ;
	
	private $headers = array(
			'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8', 
			'Accept-Charset: utf-8;q=0.7,*;q=0.3', 
			'Accept-Language: en-US,en;q=0.5', 
			'Connection: keep-alive', 
			'Keep-Alive: 300',
			'Cache-Control: max-age=0'
		) ;
	
	public function __construct($headerHost, $queryFormat, $urlsXPath, $nr, $uc) {
		$this->headers[] = 'Host: '. $headerHost ;
		$this->rootURL = 'http://' . $headerHost . '/';
		$this->numberOfResults = $nr ;
		$this->queryFormat = $queryFormat ;
		$this->urlsXPath = $urlsXPath ;
		$this->useCookies = $uc ;
	}
	
	public final function getNumberOfResults() {
		return $this->numberOfResults ;
	}
	
	public function get($query, ProxyModel $proxy = null) {
		$curl = curl_init() ;

		curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1) ;
		curl_setopt($curl, CURLOPT_HEADER, false) ;
		curl_setopt($curl, CURLOPT_RETURNTRANSFER, true) ;

		curl_setopt($curl, CURLOPT_USERAGENT, 'User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0') ;
		curl_setopt($curl, CURLOPT_ENCODING, 'gzip,deflate') ;
		curl_setopt($curl, CURLOPT_HTTPHEADER, $this->headers) ;
		
		curl_setopt($curl, CURLOPT_FOLLOWLOCATION, true) ;
		curl_setopt($curl, CURLOPT_AUTOREFERER, true) ;
		
		if($proxy != null) {
			curl_setopt($curl, CURLOPT_PROXY, $proxy) ;
		}
		
		if($this->useCookies) {
			$cookie = dirname(__FILE__) . '/cookies/' . ($proxy!= null ? $proxy->getIPAsString().'_'.$proxy->port : 'local'). '.txt' ;
			curl_setopt($curl, CURLOPT_COOKIEJAR, $cookie) ;
			curl_setopt($curl, CURLOPT_COOKIEFILE, $cookie) ;
			curl_setopt($curl, CURLOPT_URL, $this->rootURL) ;

			curl_exec($curl) ;
		}
		
		curl_setopt($curl, CURLOPT_URL, $this->rootURL . $query) ;

		$data = curl_exec($curl) ;
		$httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE) ;
		switch($httpCode) {
			case 200:
				break ;
			case 403: case 502: case 503: case 999:
			default:
				$e = new Exception('HTTP Code ' . $httpCode) ;
				break ;
		}

		curl_close($curl) ;

		if(isset($e)) {
			throw $e ;
		} else {
			return $data ;
		}
	}
	
	public function scrapeURLs($query, ProxyModel $proxy = null) {
		$data = $this->get(sprintf($this->queryFormat, urlencode($query), $this->numberOfResults), $proxy) ;
		echo $data ;
		$doc = new DOMDocument() ;
		@$doc->loadHTML($data) ;
		$xpath = new DOMXPath($doc) ;
		$urls = array() ;
		foreach($xpath->query($this->urlsXPath) as $node) {
			$href = urldecode($node->nodeValue) ; 
			$urls[] = substr($href, strpos($href, 'http')) ;
		}
		return $urls ;
	}

}

?>